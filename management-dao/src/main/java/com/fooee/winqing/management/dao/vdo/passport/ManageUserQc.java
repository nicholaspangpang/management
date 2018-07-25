package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageUser查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:28:57
 */
public class ManageUserQc{
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 用户登录名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPass;
	/**
	 * 用户真实名字
	 */
	private String userRealname;
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
	 * 用户登录名
	 * @param userName
	 */
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	/**
	 * 用户登录名
	 * @return
	 */
	public String getUserName(){
		return this.userName;
	}
		
	/**
	 * 用户密码
	 * @param userPass
	 */
	
	public void setUserPass(String userPass){
		this.userPass=userPass;
	}
	/**
	 * 用户密码
	 * @return
	 */
	public String getUserPass(){
		return this.userPass;
	}
		
	/**
	 * 用户真实名字
	 * @param userRealname
	 */
	
	public void setUserRealname(String userRealname){
		this.userRealname=userRealname;
	}
	/**
	 * 用户真实名字
	 * @return
	 */
	public String getUserRealname(){
		return this.userRealname;
	}
		
}
