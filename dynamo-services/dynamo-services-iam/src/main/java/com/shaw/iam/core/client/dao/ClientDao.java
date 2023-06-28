package com.shaw.iam.core.client.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.client.entity.Client;
import com.shaw.iam.dto.client.ClientDto;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface ClientDao extends JpaRepository<Client, String>, JpaSpecificationExecutor<ClientDto> {

	public Optional<Client> findByCode(String code);

	public boolean existsByCode(String code);

	public boolean existsByCodeAndId(String code, String id);

}
