package com.shaw.iam.core.client.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.client.entity.LonginType;

/**
 * 终端
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository(value = "loginTypeDao")
public interface LoginTypeDao extends JpaRepository<LonginType, String>, JpaSpecificationExecutor<LonginType> {

	Optional<LonginType> findByCode(String code);

	boolean existsByCode(String code);

	boolean existsByCodeAndId(String code, String id);

}
