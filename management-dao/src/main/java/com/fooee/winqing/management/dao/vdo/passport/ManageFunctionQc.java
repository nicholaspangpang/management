package com.fooee.winqing.management.dao.vdo.passport;

/**
 * ManageFunction查询条件
 * @author pangzhenhua
 * @version 2018-7-10 10:29:42
 */
public class ManageFunctionQc{

	/**
	 * 功能描述
	 */
	private String functionDescription;
	/**
	 * 功能名称
	 */
	private String functionName;
	/**
	 * 功能链接
	 */
	private String functionUrl;
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 是否启用
	 */
	private Boolean isEnable;
	/**
	 * 父级id
	 */
	private Integer parentId;
	/**
	 * 功能描述
	 * @param functionDescription
	 */
	
	public void setFunctionDescription(String functionDescription){
		this.functionDescription=functionDescription;
	}
	/**
	 * 功能描述
	 * @return
	 */
	public String getFunctionDescription(){
		return this.functionDescription;
	}
		
	/**
	 * 功能名称
	 * @param functionName
	 */
	
	public void setFunctionName(String functionName){
		this.functionName=functionName;
	}
	/**
	 * 功能名称
	 * @return
	 */
	public String getFunctionName(){
		return this.functionName;
	}
		
	/**
	 * 功能链接
	 * @param functionUrl
	 */
	
	public void setFunctionUrl(String functionUrl){
		this.functionUrl=functionUrl;
	}
	/**
	 * 功能链接
	 * @return
	 */
	public String getFunctionUrl(){
		return this.functionUrl;
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
	 * 父级id
	 * @param parentId
	 */
	
	public void setParentId(Integer parentId){
		this.parentId=parentId;
	}
	/**
	 * 父级id
	 * @return
	 */
	public Integer getParentId(){
		return this.parentId;
	}
		
}
