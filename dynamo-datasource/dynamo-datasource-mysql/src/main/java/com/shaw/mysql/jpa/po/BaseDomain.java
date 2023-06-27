package com.shaw.mysql.jpa.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.experimental.Accessors;

/**
 * @author shaw
 * @date 2023/06/20
 */
@Accessors(chain = true)
@MappedSuperclass
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	protected Date createDate;
	protected Date updateDate;
	protected String createBy;
	protected String updateBy;

	public static interface BaseView {
	}

	public static interface BaseDomainView extends BaseView {
	}

	public BaseDomain() {
		Date nowTime = new Date();
		this.createDate = nowTime;
		this.updateDate = nowTime;
	}

	public BaseDomain(String id, String createBy) {
		this.id = id;
		this.createBy = createBy;
		this.updateBy = createBy;
		Date nowTime = new Date();
		this.createDate = nowTime;
		this.updateDate = nowTime;
	}

	@Transient
	public void set(String id, String createBy) {
		this.id = id;
		this.createBy = createBy;
		this.updateBy = createBy;
		Date nowTime = new Date();
		this.createDate = nowTime;
		this.updateDate = nowTime;
	}

	@Transient
	public void setUpdateByandDate(String id, String createBy) {
		this.id = id;
		this.updateBy = createBy;
		Date nowTime = new Date();
		this.updateDate = nowTime;
	}

	public void setUpdateByandDate(String createBy) {
		this.updateBy = createBy;
		Date nowTime = new Date();
		this.updateDate = nowTime;
	}

	@Id
	@JsonView(BaseView.class)
	@Column(name = "id", length = 36, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonView(BaseDomainView.class)
	@Column(name = "create_date", insertable = true)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonView(BaseDomainView.class)
	@Column(name = "update_date", insertable = true, updatable = true)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonView(BaseDomainView.class)
	@Column(name = "create_by", length = 36, insertable = true)
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@JsonView(BaseDomainView.class)
	@Column(name = "update_by", length = 36, insertable = true, updatable = true)
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
