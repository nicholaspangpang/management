package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageDepartmentFunctionRelation查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:29:55
 */
public class ManageDepartmentFunctionRelationDo {
	
	/**
	 * 组织机构id
	 */
	private Long departmentId;
	/**
	 * 功能id
	 */
	private Long functionId;
	/**
	 * 
	 */
	private Long id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 组织机构id
	 * @param departmentId
	 */
	
	public void setDepartmentId(Long departmentId){
		this.departmentId=departmentId;
	}
	/**
	 * 组织机构id
	 * @return
	 */
	public Long getDepartmentId(){
		return this.departmentId;
	}
		
	/**
	 * 功能id
	 * @param functionId
	 */
	
	public void setFunctionId(Long functionId){
		this.functionId=functionId;
	}
	/**
	 * 功能id
	 * @return
	 */
	public Long getFunctionId(){
		return this.functionId;
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
		
}
