package cn.com.app.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  Hunter
 * @Date 2019-01-14 
 */

public class DataDictionary  implements Serializable {

	private static final long serialVersionUID =  1194110229856083102L;
	private Long id;	 // 主键ID
	private String typeCode;	 // 类型编码
	private String typeName;	 // 类型名称
	private Long valueId;	 // 类型值ID
	private String valueName;	 // 类型值Name
	private Long createdBy;	 // 创建者（来源于backend_user用户表的用户id）
	private Date creationDate;	 // 创建时间
	private Long modifyBy;	 // 更新者（来源于backend_user用户表的用户id）
	private Date modifyDate;	 // 最新更新时间

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getValueId() {
		return this.valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public String getValueName() {
		return this.valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
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

}
