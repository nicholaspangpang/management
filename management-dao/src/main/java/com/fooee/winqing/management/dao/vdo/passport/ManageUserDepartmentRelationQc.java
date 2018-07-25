package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageUserDepartmentRelation查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:28:38
 */
public class ManageUserDepartmentRelationQc{
	
	/**
	 * 组织机构id
	 */
	private Long departmentId;
	/**
	 * 
	 */
	private Long id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 用户id
	 */
	private Long userId;
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
