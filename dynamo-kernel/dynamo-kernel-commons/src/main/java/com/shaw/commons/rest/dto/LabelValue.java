package com.shaw.commons.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * LabelValue
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Schema(title = "LabelValue键值对象")
public class LabelValue {

	@Schema(description = "label")
	private String label;

	@Schema(description = "值")
	private String value;

	public LabelValue(String label, Number value) {
		this.label = label;
		this.value = String.valueOf(value);
	}

	public LabelValue(String label, String value) {
		this.label = label;
		this.value = value;
	}

}
