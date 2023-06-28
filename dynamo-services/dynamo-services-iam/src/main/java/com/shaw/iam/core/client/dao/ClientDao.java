package com.shaw.iam.core.client.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.client.entity.Client;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface ClientDao extends JpaRepositoryImplementation<Client, String> {

	public Optional<Client> findByCode(String code);

	public boolean existsByCode(String code);

	public boolean existsByCodeAndIdNot(String code, String id);

}
