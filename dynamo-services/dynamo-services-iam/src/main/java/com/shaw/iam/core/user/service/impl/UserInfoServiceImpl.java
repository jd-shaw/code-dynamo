package com.shaw.iam.core.user.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shaw.auth.util.PasswordEncoder;
import com.shaw.auth.util.SecurityUtil;
import com.shaw.commons.annotation.TimeConsuming;
import com.shaw.commons.exception.BaseException;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.commons.utils.ResultConvertUtil;
import com.shaw.iam.code.UserStatusCode;
import com.shaw.iam.core.client.service.ClientService;
import com.shaw.iam.core.user.dao.UserInfoDao;
import com.shaw.iam.core.user.entity.UserExpandInfo;
import com.shaw.iam.core.user.entity.UserInfo;
import com.shaw.iam.core.user.service.UserExpandInfoService;
import com.shaw.iam.core.user.service.UserInfoService;
import com.shaw.iam.dto.client.ClientDto;
import com.shaw.iam.dto.user.LoginAfterUserInfo;
import com.shaw.iam.dto.user.UserExpandInfoDto;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.exception.user.UserInfoNotExistsException;
import com.shaw.iam.param.user.UserInfoParam;
import com.shaw.iam.param.user.UserRegisterParam;
import com.shaw.sys.core.service.CaptchaService;
import com.shaw.utils.RandomUIDUtils;
import com.shaw.utils.bean.BeanUtilsBean;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

	private final UserInfoDao userInfoDao;
	private final UserExpandInfoService userExpandInfoService;
	private final PasswordEncoder passwordEncoder;
	private final ClientService clientService;
	private final CaptchaService captchaService;

	/**
	 * 注册新用户
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void register(UserRegisterParam param) {
		// 验证
		if (!getCaptchaService().validateImgCaptcha(param.getCaptchaKey(), param.getCaptcha())) {
			throw new BaseException("验证码错误");
		}
		UserInfoParam userInfoParam = new UserInfoParam();
		BeanUtils.copyProperties(param, userInfoParam);
		userInfoParam.setName(param.getUsername());
		// TODO 默认注册就有所有终端的权限, 后期优化
		List<String> ids = getClientService().findAll().stream().map(ClientDto::getId).map(String::valueOf)
				.collect(Collectors.toList());
		userInfoParam.setClientIdList(ids);
		save(userInfoParam);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(UserInfoParam userInfoParam) {
		if (existsByUsername(userInfoParam.getUsername())) {
			throw new BaseException("账号已存在");
		}
		if (existsByEmail(userInfoParam.getEmail())) {
			throw new BaseException("邮箱已存在");
		}
		if (existsByPhone(userInfoParam.getPhone())) {
			throw new BaseException("手机号已存在");
		}
		// 注册时间
		UserInfo userInfo = UserInfo.init(userInfoParam);
		userInfo.setAdmin(false).setStatus(UserStatusCode.NORMAL)
				.setPassword(getPasswordEncoder().encode(userInfo.getUsername(), userInfo.getPassword()))
				.setRegisterTime(LocalDateTime.now()).setId(RandomUIDUtils.getUID());
		getUserInfoDao().save(userInfo);
		// 扩展信息
		UserExpandInfo userExpandInfo = new UserExpandInfo();
		userExpandInfo.setInitialPassword(true).setId(userInfo.getId());
		getUserExpandInfoService().save(userExpandInfo);
		//TODO  发送用户注册事件
	}

	@Override
	public UserInfoDto update(UserInfoParam param) {
		UserInfo userInfo = getUserInfoDao().findById(param.getId()).orElseThrow(UserInfoNotExistsException::new);
		param.setPassword(null);
		BeanUtils.copyProperties(param, userInfo, BeanUtilsBean.getNullPropertyNames(param));
		if (CollectionUtils.isNotEmpty(param.getClientIdList())) {
			userInfo.setClientIds(String.join(",", param.getClientIdList()));
		} else {
			userInfo.setClientIds("");
		}
		return getUserInfoDao().save(userInfo).toDto();
	}

	@Override
	@Transactional
	public void updatePassword(String password, String newPassword) {
		UserInfo userInfo = getUserInfoDao().findById(SecurityUtil.getUserId())
				.orElseThrow(UserInfoNotExistsException::new);
		// 新密码进行加密
		newPassword = passwordEncoder.encode(userInfo.getUsername(), newPassword);

		// 判断原有密码是否相同
		if (!passwordEncoder.matches(password, userInfo.getPassword())) {
			throw new BaseException("旧密码错误");
		}
		userInfo.setPassword(newPassword);
		getUserInfoDao().save(userInfo);
		getUserExpandInfoService().updateChangePasswordTime(SecurityUtil.getUserId(), LocalDateTime.now());
	}

	@Override
	public void updatePasswordById(String userId, String newPassword) {
		UserInfo userInfo = getUserInfoDao().findById(userId).orElseThrow(UserInfoNotExistsException::new);
		// 新密码进行加密
		newPassword = passwordEncoder.encode(userInfo.getUsername(), newPassword);
		userInfo.setPassword(newPassword);
		getUserInfoDao().save(userInfo);
		getUserExpandInfoService().updateChangePasswordTime(SecurityUtil.getUserId(), LocalDateTime.now());
	}

	@Override
	public UserInfoDto findByEmail(String email) {
		return getUserInfoDao().findByEmail(email).toDto();
	}

	@Override
	public UserInfoDto findByPhone(String phone) {
		return getUserInfoDao().findByPhone(phone).toDto();
	}

	@Override
	public UserInfoDto findByAccount(String account) {
		return getUserInfoDao().findByUsername(account).toDto();
	}

	@Override
	@TimeConsuming
	public LoginAfterUserInfo getLoginAfterUserInfo() {
		UserInfo userInfo = getUserInfoDao().findById(SecurityUtil.getUserId())
				.orElseThrow(UserInfoNotExistsException::new);
		UserExpandInfoDto userExpandInfo = getUserExpandInfoService().findById(SecurityUtil.getUserId());
		return new LoginAfterUserInfo().setAvatar(userExpandInfo.getAvatar()).setUserId(userInfo.getId())
				.setUsername(userInfo.getUsername()).setName(userInfo.getName());
	}

	@Override
	public boolean existsByUsername(String username) {
		return getUserInfoDao().existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return getUserInfoDao().existsByEmail(email);
	}

	@Override
	public boolean existsByPhone(String phone) {
		return getUserInfoDao().existsByPhone(phone);
	}

	@Override
	public boolean existsByUsernameAndIdNot(String username, String id) {
		return getUserInfoDao().existsByUsernameAndIdNot(username, id);
	}

	@Override
	public boolean existsByEmailAndIdNot(String email, String id) {
		return getUserInfoDao().existsByEmailAndIdNot(email, id);
	}

	@Override
	public boolean existsByPhoneAndIdNot(String phone, String id) {
		return getUserInfoDao().existsByPhoneAndIdNot(phone, id);
	}

	@Override
	public List<UserInfoDto> findByIds(List<String> ids) {
		return ResultConvertUtil.dtoListConvert(getUserInfoDao().findAllById(ids));
	}

	@Override
	public UserInfoDto findById(String id) {
		return getUserInfoDao().findById(id).orElseThrow(UserInfoNotExistsException::new).toDto();
	}

	@Override
	public PageResult<UserInfoDto> page(PageParam pageParam, UserInfoParam param) {
		Specification<UserInfo> specification = new Specification<UserInfo>() {
			@Override
			public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (StringUtils.hasLength(param.getName())) {
					predicateList
							.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + param.getName() + "%"));
				}
				return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
			}
		};
		Pageable pageable = PageRequest.of(pageParam.start(), pageParam.getSize());
		Page<UserInfo> page = getUserInfoDao().findAll(specification, pageable);
		return new PageResult<UserInfoDto>().setSize(page.getSize()).setCurrent(page.getNumber())
				.setTotal(page.getTotalPages()).setRecords(ResultConvertUtil.dtoListConvert(page.getContent()));
	}

	@Override
	public void updateStatus(String id, int statusCode) {
		getUserInfoDao().updateStatusById(id, statusCode);
	}
}
