package com.shaw.iam.core.upms.service;

import com.shaw.iam.dto.upms.MenuAndResourceDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 角色权限菜单关系
 *
 * @author shaw
 * @date 2023/06/20
 */
public interface RolePermService {

    MenuAndResourceDto getPermissions(String clientCode);

}
