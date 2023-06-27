package com.shaw.iam.core.auth.handler;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.shaw.auth.authentication.GetAuthClientService;
import com.shaw.auth.entity.AuthClient;
import com.shaw.auth.exception.ApplicationNotFoundException;
import com.shaw.iam.core.client.dao.ClientDao;
import com.shaw.iam.core.client.entity.Client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GetAuthClientServiceImpl implements GetAuthClientService {

	private final ClientDao clientDao;

	@Override
	public AuthClient getAuthApplication(String authClientCode) {
		Client client = clientDao.findByCode(authClientCode).orElseThrow(ApplicationNotFoundException::new);
		AuthClient authClient = new AuthClient();
		BeanUtils.copyProperties(client, authClient);
		return authClient;
	}

}
