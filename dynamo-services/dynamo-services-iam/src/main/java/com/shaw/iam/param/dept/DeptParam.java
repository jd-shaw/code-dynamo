package com.shaw.iam.param.dept;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Data
@Accessors(chain = true)
@Schema(title = "部门参数")
public class DeptParam implements Serializable {

	private static final long serialVersionUID = -3523887187454705868L;

	@Schema(description = "主键")
	private String id;

	@Schema(description = "父机构ID")
	private String parentId;

	@Schema(description = "名称")
	private String deptName;

	@Schema(description = "排序")
	private Double sortNo;

	@Schema(description = "机构类别 1组织机构，2岗位")
	private Integer orgCategory;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "传真")
	private String fax;

	@Schema(description = "地址")
	private String address;

	@Schema(description = "备注")
	private String remark;

}
