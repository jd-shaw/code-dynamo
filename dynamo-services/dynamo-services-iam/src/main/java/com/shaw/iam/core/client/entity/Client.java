package com.shaw.iam.core.client.entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.core.client.convert.ClientConvert;
import com.shaw.iam.dto.client.ClientDto;
import com.shaw.iam.param.client.ClientParam;
import com.shaw.mysql.jpa.entity.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 认证终端
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "iam_client")
@Accessors(chain = true)
public class Client extends BaseDomain implements EntityBaseFunction<ClientDto> {

	/** 编码 */
	private String code;

	/** 名称 */
	private String name;

	/** 是否系统内置 */
	@Column(columnDefinition = "int default 0")
	private int isSystem;

	/** 是否可用 */
	@Column(columnDefinition = "int default 0")
	private int enable;

	/** 关联登录方式 */
	private String loginTypeIds;

	/** 描述 */
	private String description;

	/** 创建对象 */
	public static Client init(ClientParam in) {
		Client client = ClientConvert.CONVERT.convert(in);
		if (CollectionUtils.isNotEmpty(in.getLoginTypeIdList())) {
			String loginTypeIds = String.join(",", in.getLoginTypeIdList());
			client.setLoginTypeIds(loginTypeIds);
		}
		return client;
	}

	/** 转换成dto */
	@Override
	public ClientDto toDto() {
		ClientDto client = ClientConvert.CONVERT.convert(this);
		if (StringUtils.isNotBlank(this.getLoginTypeIds())) {
			List<String> collect = Arrays.stream(this.getLoginTypeIds().split(",")).collect(Collectors.toList());
			client.setLoginTypeIdList(collect);
		}
		return client;
	}

}
