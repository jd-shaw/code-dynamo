package com.shaw.iam.core.dept.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.code.OrgCategoryCode;
import com.shaw.iam.core.dept.convert.DeptConvert;
import com.shaw.iam.dto.dept.DeptDto;
import com.shaw.iam.param.dept.DeptParam;
import com.shaw.mysql.jpa.po.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 部门表
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Accessors(chain = true)
@Table(name = "iam_dept")
public class Dept extends BaseDomain implements EntityBaseFunction<DeptDto> {

	/** 父机构ID */
	private String parentId;

	/** 机构/部门名称 */
	private String deptName;

	/** 排序 */
	private Double sortNo;

	/**
	 * 机构类别
	 * @see OrgCategoryCode
	 */
	private Integer orgCategory;

	/** 机构编码 */
	private String orgCode;

	/** 手机号 */
	private String mobile;

	/** 传真 */
	private String fax;

	/** 地址 */
	private String address;

	/** 备注 */
	private String remark;

	public static Dept init(DeptParam in) {
		return DeptConvert.CONVERT.convert(in);
	}

	@Override
	public DeptDto toDto() {
		return DeptConvert.CONVERT.convert(this);
	}

}
