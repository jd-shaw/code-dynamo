package com.shaw.iam.core.client.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.client.entity.LoginType;

/**
 * 终端
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface LoginTypeDao extends JpaRepositoryImplementation<LoginType, String> {

	Optional<LoginType> findByCode(String code);

	boolean existsByCode(String code);

	boolean existsByCodeAndIdNot(String code, String id);

}
