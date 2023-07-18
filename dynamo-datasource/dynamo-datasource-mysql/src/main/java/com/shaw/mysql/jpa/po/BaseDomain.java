package com.shaw.mysql.jpa.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

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
	protected int version;

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
	public void setUpdateByAndDate(String id, String createBy) {
		this.id = id;
		this.updateBy = createBy;
		this.updateDate = new Date();
	}

	@Transient
	public void setUpdateByAndDate(String createBy) {
		this.updateBy = createBy;
		this.updateDate = new Date();
	}

	@Id
	@Column(name = "id", length = 36, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "create_date", insertable = true)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date", insertable = true, updatable = true)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "create_by", length = 36, insertable = true)
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "update_by", length = 36, insertable = true, updatable = true)
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "version", columnDefinition = "int default 0")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
