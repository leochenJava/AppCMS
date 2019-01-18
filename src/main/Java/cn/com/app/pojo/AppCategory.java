package cn.com.app.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2019-01-14 
 */

public class AppCategory  implements Serializable {

	private static final long serialVersionUID =  426819987561010269L;
	private Long id;	 // 主键ID
	private String categoryCode;	 // 分类编码
	private String categoryName;	 // 分类名称
	private Long parentId;	 // 父级节点id
	private Long createdBy;	 // 创建者（来源于backend_user用户表的用户id）
	private Date creationTime;	 // 创建时间
	private Long modifyBy;	 // 更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;	 // 最新更新时间

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
