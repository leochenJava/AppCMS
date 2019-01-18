package cn.com.app.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2019-01-17 
 */

public class BackendUser  implements Serializable {

	private static final long serialVersionUID =  536911946893314526L;
	private Long id;	 // 主键id
	private String userCode;	 // 用户编码
	private String userName;	 // 用户名称
	private Long userType;	 // 用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）
	private Long createdBy;	 // 创建者（来源于backend_user用户表的用户id）
	private Date creationDate;	 // 创建时间
	private Long modifyBy;	 // 更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;	 // 最新更新时间
	private String userPassword;	 // 用户密码

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserType() {
		return this.userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
