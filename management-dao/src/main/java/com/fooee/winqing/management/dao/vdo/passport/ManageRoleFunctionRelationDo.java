package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageRoleFunctionRelation查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:29:12
 */
public class ManageRoleFunctionRelationDo {
	
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
	 * 角色id
	 */
	private Integer roleId;
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
		
	/**
	 * 角色id
	 * @param roleId
	 */
	
	public void setRoleId(Integer roleId){
		this.roleId=roleId;
	}
	/**
	 * 角色id
	 * @return
	 */
	public Integer getRoleId(){
		return this.roleId;
	}
		
}
