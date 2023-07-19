package com.shaw.iam.core.permission.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.shaw.commons.function.EntityBaseFunction;
import com.shaw.iam.code.PermissionCode;
import com.shaw.iam.core.permission.convert.PermConvert;
import com.shaw.iam.dto.permission.PermMenuDto;
import com.shaw.iam.param.permission.PermMenuParam;
import com.shaw.mysql.jpa.entity.BaseDomain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 权限配置
 *
 * @author shaw
 * @date 2023/06/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "iam_perm_menu")
public class PermMenu extends BaseDomain implements EntityBaseFunction<PermMenuDto> {

	/** 父id */
	private String parentId;

	/** 关联终端code */
	private String clientCode;

	/** 菜单标题 */
	private String title;

	/** 路由名称，建议唯一 */
	private String name;

	/** 资源编码(权限码) */
	private String permCode;

	/** 是否有效 */
	private boolean effect;

	/** 菜单图标 */
	private String icon;

	/** 是否隐藏 */
	private boolean hidden;

	/** 是否隐藏子菜单 */
	private boolean hideChildrenInMenu;

	/** 组件 */
	private String component;

	/** 路径 */
	private String path;

	/**
	 * 菜单跳转地址(重定向)
	 */
	private String redirect;

	/**
	 * 菜单排序
	 */
	private Double sortNo;

	/**
	 * 类型（0：一级菜单；1：子菜单 ；2：资源）
	 * @see PermissionCode
	 */
	private Integer menuType;

	/* meta */
	/**
	 * 是否缓存页面
	 */
	private boolean keepAlive;

	/** 打开方式是否为外部打开 */
	private boolean targetOutside;

	/** 隐藏的标题内容 */
	private boolean hiddenHeaderContent;

	/** 系统菜单 */
	private boolean isAdmin;

	/** 描述 */
	private String remark;

	public static PermMenu init(PermMenuParam in) {
		return PermConvert.CONVERT.convert(in);
	}

	@Override
	public PermMenuDto toDto() {
		return PermConvert.CONVERT.convert(this);
	}

}
