package com.shaw.iam.core.scope.service.impl;

import com.shaw.commons.function.CollectorsFunction;
import com.shaw.iam.code.CachingCode;
import com.shaw.iam.core.scope.dao.DataScopeUserDao;
import com.shaw.iam.core.scope.entity.DataScope;
import com.shaw.iam.core.scope.entity.DataScopeUser;
import com.shaw.iam.core.scope.service.DataScopeUserService;
import com.shaw.iam.core.user.service.UserInfoService;
import com.shaw.iam.dto.scope.DataScopeUserInfoDto;
import com.shaw.iam.dto.user.UserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author shaw
 * @date 2023/6/28
 */
@Getter
@Slf4j
@Service
@RequiredArgsConstructor
public class DataScopeUserServiceImpl implements DataScopeUserService {

    private final DataScopeUserDao dataScopeUserDao;

    private final UserInfoService userInfoService;

    @Override
    public void deleteByDataScopeId(String dataScopeId) {
        getDataScopeUserDao().deleteByDataScopeId(dataScopeId);
    }

    /**
     * 关联用户列表
     */
    @Override
    public List<DataScopeUserInfoDto> findUsersByDataScopeId(String dataScopeId) {
        Map<String, DataScopeUser> dataScopeUserMap = getDataScopeUserDao().findByDataScopeId(dataScopeId).stream()
                .collect(Collectors.toMap(DataScopeUser::getUserId, Function.identity(),
                        CollectorsFunction::retainLatest));
        // 查询出用户id
        List<String> userIds = dataScopeUserMap.values().stream().map(DataScopeUser::getUserId)
                .collect(Collectors.toList());
        // 查询出用户
        List<UserInfoDto> userInfos = getUserInfoService().findByIds(userIds);

        return userInfos.stream()
                .map(userInfo -> new DataScopeUserInfoDto().setId(dataScopeUserMap.get(userInfo.getId()).getId())
                        .setUserId(userInfo.getId()).setUsername(userInfo.getUsername()).setName(userInfo.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 添加用户范围权限关联关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {CachingCode.USER_DATA_SCOPE}, allEntries = true)
    public void saveUserAssign(String dataScopeId, List<String> userIds) {
        //		DataScopeDto dataScope = getDataScopeService().findById(dataScopeId);
        // TODO
        //		if (!Objects.equals(dataScope.getType(), DataScopeEnum.USER_SCOPE.getCode())
        //				&& Objects.equals(dataScope.getType(), DataScopeEnum.DEPT_AND_USER_SCOPE.getCode())) {
        //			throw new BizException("非法操作");
        //		}
        List<String> dataScopeUserIds = getDataScopeUserDao().findByDataScopeId(dataScopeId).stream()
                .map(DataScopeUser::getUserId).collect(Collectors.toList());

        List<DataScopeUser> dataScopeUsers = userIds.stream().filter(userId -> !dataScopeUserIds.contains(userId))
                .map(userId -> new DataScopeUser(DataScope.builder().id(dataScopeId).build(), userId)).collect(Collectors.toList());
        getDataScopeUserDao().saveAll(dataScopeUsers);
    }

    /**
     * 批量删除
     */
    @Override
    @CacheEvict(value = {CachingCode.USER_DATA_SCOPE}, allEntries = true)
    public void deleteBatch(List<String> ids) {
        getDataScopeUserDao().deleteAllById(ids);
    }

}
