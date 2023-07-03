package com.shaw.iam.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.shaw.commons.annotation.OperateLog;
import com.shaw.commons.rest.PageResult;
import com.shaw.commons.rest.Res;
import com.shaw.commons.rest.ResResult;
import com.shaw.commons.rest.param.PageParam;
import com.shaw.iam.core.user.service.UserAdminService;
import com.shaw.iam.core.user.service.UserInfoService;
import com.shaw.iam.dto.user.UserInfoDto;
import com.shaw.iam.dto.user.UserInfoWhole;
import com.shaw.iam.param.user.UserInfoParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Validated
@Tag(name = "管理用户(管理员级别)")
@RestController
@RequestMapping("/user/admin")
@RequiredArgsConstructor
public class UserAdminController {

	private final UserAdminService userAdminService;
	private final UserInfoService userInfoService;

	@Operation(summary = "根据用户id查询用户")
	@GetMapping("/find-by-id")
	public ResResult<UserInfoDto> findById(String id) {
		return Res.ok(getUserInfoService().findById(id));
	}

	@Operation(summary = "查询用户详情")
	@GetMapping("/get-user-info-whole")
	public ResResult<UserInfoWhole> getUserInfoWhole(String id) {
		return Res.ok(userAdminService.getUserInfoWhole(id));
	}

	@Operation(summary = "根据邮箱查询用户")
	@GetMapping("/get-by-email")
	public ResResult<UserInfoDto> getByEmail(String email) {
		return Res.ok(getUserInfoService().findByEmail(email));
	}

	@Operation(summary = "根据手机号查询用户")
	@GetMapping("/get-by-phone")
	public ResResult<UserInfoDto> getByPhone(String phone) {
		return Res.ok(getUserInfoService().findByPhone(phone));
	}

	@Operation(summary = "添加用户")
	@PostMapping("/add")
	public ResResult<Void> add(@RequestBody UserInfoParam userInfoParam) {
		getUserInfoService().save(userInfoParam);
		return Res.ok();
	}

	@Operation(summary = "修改用户")
	@PostMapping("/update")
	public ResResult<Void> update(@RequestBody UserInfoParam userInfoParam) {
		getUserInfoService().update(userInfoParam);
		return Res.ok();
	}

	@Operation(summary = "重置密码")
	@OperateLog(title = "重置密码", businessType = OperateLog.BusinessType.UPDATE, saveParam = true)
	@PostMapping("/restart-password")
	public ResResult<Void> restartPassword(@NotNull(message = "用户不可为空") String userId,
			@NotBlank(message = "新密码不能为空") String newPassword) {
		getUserInfoService().updatePasswordById(userId, newPassword);
		return Res.ok();
	}

	@OperateLog(title = "锁定用户", businessType = OperateLog.BusinessType.UPDATE, saveParam = true)
	@Operation(summary = "锁定用户")
	@PostMapping("/lock")
	public ResResult<Void> lock(String userId) {
		getUserAdminService().lock(userId);
		return Res.ok();
	}

	@OperateLog(title = "批量锁定用户", businessType = OperateLog.BusinessType.UPDATE, saveParam = true)
	@Operation(summary = "批量锁定用户")
	@PostMapping("/lock-batch")
	public ResResult<Void> lockBatch(@RequestBody @NotEmpty(message = "用户集合不可为空") List<String> userIds) {
		getUserAdminService().lockBatch(userIds);
		return Res.ok();
	}

	@OperateLog(title = "解锁用户", businessType = OperateLog.BusinessType.UPDATE, saveParam = true)
	@Operation(summary = "解锁用户")
	@PostMapping("/unlock")
	public ResResult<Void> unlock(@NotNull(message = "用户不可为空") String userId) {
		getUserAdminService().unlock(userId);
		return Res.ok();
	}

	@OperateLog(title = "批量解锁用户", businessType = OperateLog.BusinessType.UPDATE, saveParam = true)
	@Operation(summary = "批量解锁用户")
	@PostMapping("/unlock-batch")
	public ResResult<Void> unlockBatch(@RequestBody @NotEmpty(message = "用户集合不可为空") List<String> userIds) {
		getUserAdminService().unlockBatch(userIds);
		return Res.ok();
	}

	@Operation(summary = "分页")
	@GetMapping("/page")
	public ResResult<PageResult<UserInfoDto>> page(PageParam pageParam, UserInfoParam userInfoParam) {
		return Res.ok(getUserAdminService().page(pageParam, userInfoParam));
	}

}
