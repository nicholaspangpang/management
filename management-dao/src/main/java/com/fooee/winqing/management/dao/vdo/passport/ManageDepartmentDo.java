package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageDepartment查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:30:09
 */
public class ManageDepartmentDo {
	
	/**
	 * 组织机构名称
	 */
	private String departmentName;
	/**
	 * 组织机构类型代码(0-部门，1-岗位)
	 */
	private Boolean departmentTypeCode;
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 父级id
	 */
	private Integer parentId;
	/**
	 * 组织机构名称
	 * @param departmentName
	 */
	
	public void setDepartmentName(String departmentName){
		this.departmentName=departmentName;
	}
	/**
	 * 组织机构名称
	 * @return
	 */
	public String getDepartmentName(){
		return this.departmentName;
	}
		
	/**
	 * 组织机构类型代码(0-部门，1-岗位)
	 * @param departmentTypeCode
	 */
	
	public void setDepartmentTypeCode(Boolean departmentTypeCode){
		this.departmentTypeCode=departmentTypeCode;
	}
	/**
	 * 组织机构类型代码(0-部门，1-岗位)
	 * @return
	 */
	public Boolean getDepartmentTypeCode(){
		return this.departmentTypeCode;
	}
		
	/**
	 * 
	 * @param id
	 */
	
	public void setId(Integer id){
		this.id=id;
	}
	/**
	 * 
	 * @return
	 */
	public Integer getId(){
		return this.id;
	}
		
	/**
	 * 是否启用
	 * @param isEnable
	 */
	
	public void setIsEnable(Boolean isEnable){
		this.isEnable=isEnable;
	}
	/**
	 * 是否启用
	 * @return
	 */
	public Boolean getIsEnable(){
		return this.isEnable;
	}
		
	/**
	 * 父级id
	 * @param parentId
	 */
	
	public void setParentId(Integer parentId){
		this.parentId=parentId;
	}
	/**
	 * 父级id
	 * @return
	 */
	public Integer getParentId(){
		return this.parentId;
	}
		
}
