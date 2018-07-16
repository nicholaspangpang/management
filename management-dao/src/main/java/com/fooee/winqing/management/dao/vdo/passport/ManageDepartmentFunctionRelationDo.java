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
	private Integer departmentId;
	/**
	 * 功能id
	 */
	private Integer functionId;
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 组织机构id
	 * @param departmentId
	 */
	
	public void setDepartmentId(Integer departmentId){
		this.departmentId=departmentId;
	}
	/**
	 * 组织机构id
	 * @return
	 */
	public Integer getDepartmentId(){
		return this.departmentId;
	}
		
	/**
	 * 功能id
	 * @param functionId
	 */
	
	public void setFunctionId(Integer functionId){
		this.functionId=functionId;
	}
	/**
	 * 功能id
	 * @return
	 */
	public Integer getFunctionId(){
		return this.functionId;
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
		
}