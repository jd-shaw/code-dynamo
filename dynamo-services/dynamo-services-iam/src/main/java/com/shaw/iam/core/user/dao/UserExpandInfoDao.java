package com.shaw.iam.core.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaw.iam.core.user.entity.UserExpandInfo;

/**
 * @author shaw
 * @date 2023/06/20
 */
public interface UserExpandInfoDao extends JpaRepository<UserExpandInfo, String> {

}
