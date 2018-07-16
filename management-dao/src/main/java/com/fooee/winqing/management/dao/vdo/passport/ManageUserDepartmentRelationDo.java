package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageUserDepartmentRelation查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:28:38
 */
public class ManageUserDepartmentRelationDo {
	
	/**
	 * 组织机构id
	 */
	private Integer departmentId;
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 用户id
	 */
	private Integer userId;
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