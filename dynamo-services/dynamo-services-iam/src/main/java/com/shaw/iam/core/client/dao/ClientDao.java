package com.shaw.iam.core.client.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaw.iam.core.client.entity.Client;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Repository
public interface ClientDao extends JpaRepository<Client, String> {

	//    /**
	//     * 分页
	//     */
	//    public Page<Client> page(PageParam pageParam, ClientParam param) {
	//        Page<Client> mpPage = MpUtil.getMpPage(pageParam, Client.class);
	//        return lambdaQuery().like(StrUtil.isNotBlank(param.getCode()), Client::getCode, param.getCode())
	//            .like(StrUtil.isNotBlank(param.getName()), Client::getName, param.getName())
	//            .orderByDesc(MpIdEntity::getId)
	//            .page(mpPage);
	//    }

	public Optional<Client> findByCode(String code);

	public boolean existsByCode(String code);

	public boolean existsByCodeAndId(String code, String id);

}
