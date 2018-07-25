package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageDepartment查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:30:09
 */
public class ManageDepartmentQc{
	
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
	private Long id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 父级id
	 */
	private Long parentId;
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
	
	public void setId(Long id){
		this.id=id;
	}
	/**
	 * 
	 * @return
	 */
	public Long getId(){
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
	
	public void setParentId(Long parentId){
		this.parentId=parentId;
	}
	/**
	 * 父级id
	 * @return
	 */
	public Long getParentId(){
		return this.parentId;
	}
		
}
