package com.shaw.iam.core.dept.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

/**
 * 部门删除事件
 *
 * @author shaw
 * @date 2023/06/20
 */
@Getter
public class DeptDeleteEvent extends ApplicationEvent {

	private final List<String> deptIds;

	public DeptDeleteEvent(Object source, List<String> deptIds) {
		super(source);
		this.deptIds = deptIds;
	}

}
