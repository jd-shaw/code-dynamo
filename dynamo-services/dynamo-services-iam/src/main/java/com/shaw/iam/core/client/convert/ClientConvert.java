package com.shaw.iam.core.client.convert;

import com.shaw.iam.param.client.ClientParam;
import com.shaw.iam.core.client.entity.Client;
import com.shaw.iam.dto.client.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 认证应用
 *
 * @author shaw
 * @date 2023/06/20
 */
@Mapper
public interface ClientConvert {

    ClientConvert CONVERT = Mappers.getMapper(ClientConvert.class);

    Client convert(ClientParam in);

    ClientDto convert(Client in);

}
