package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageRole查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:29:27
 */
public class ManageRoleQc{
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 角色描述
	 */
	private String roleDescription;
	/**
	 * 角色名称
	 */
	private String roleName;
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
	 * 角色描述
	 * @param roleDescription
	 */
	
	public void setRoleDescription(String roleDescription){
		this.roleDescription=roleDescription;
	}
	/**
	 * 角色描述
	 * @return
	 */
	public String getRoleDescription(){
		return this.roleDescription;
	}
		
	/**
	 * 角色名称
	 * @param roleName
	 */
	
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	/**
	 * 角色名称
	 * @return
	 */
	public String getRoleName(){
		return this.roleName;
	}
		
}
