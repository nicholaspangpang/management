package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageUserRoleRelation查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:28:19
 */
public class ManageUserRoleRelationQc{
	
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
	 * 用户id
	 */
	private Integer userId;
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
		
	/**
	 * 用户id
	 * @param userId
	 */
	
	public void setUserId(Integer userId){
		this.userId=userId;
	}
	/**
	 * 用户id
	 * @return
	 */
	public Integer getUserId(){
		return this.userId;
	}
		
}
