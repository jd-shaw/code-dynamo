package com.shaw.commons.rest.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * kv键值对象
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@Schema(title = "kv键值对象")
public class KeyValue implements Serializable {

	private static final long serialVersionUID = 3427649251589010105L;

	@Schema(description = "键")
	private String key;

	@Schema(description = "值")
	private String value;

}
