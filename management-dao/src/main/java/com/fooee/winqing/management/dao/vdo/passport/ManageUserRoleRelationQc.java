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
	private Long id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 角色id
	 */
	private Long roleId;
	/**
	 * 用户id
	 */
	private Long userId;
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
	 * 角色id
	 * @param roleId
	 */
	
	public void setRoleId(Long roleId){
		this.roleId=roleId;
	}
	/**
	 * 角色id
	 * @return
	 */
	public Long getRoleId(){
		return this.roleId;
	}
		
	/**
	 * 用户id
	 * @param userId
	 */
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	/**
	 * 用户id
	 * @return
	 */
	public Long getUserId(){
		return this.userId;
	}
		
}
