/** 
* @Title: CurrentLogin.java 
* @Package com.wonders.frame.iims.model.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author lushuaifeng
* @version V1.0 
*/
package com.wonders.ic.contract.entity.bo;

import java.util.HashMap;

/** 
 * @ClassName: CurrentLogin 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 */
//当前登陆的人的身份，领导、部门呢领导、部门使用者、员工号、员工姓名等

public class CurrentLogin {
	private HashMap<String,String> cookies;
	private String companyId;
	private String companyName;
	private String userName;
	private String loginName;
	private HashMap<String,String> leaders;
	private HashMap<String,String> deptLeaders;
	private HashMap<String,String> deptUsers;	
	
	public HashMap<String, String> getDeptUsers() {
		return deptUsers;
	}
	public void setDeptUsers(HashMap<String, String> deptUsers) {
		this.deptUsers = deptUsers;
	}
	
	public HashMap<String, String> getDeptLeaders() {
		return deptLeaders;
	}
	public void setDeptLeaders(HashMap<String, String> deptLeaders) {
		this.deptLeaders = deptLeaders;
	}
	
	public HashMap<String, String> getLeaders() {
		return leaders;
	}
	public void setLeaders(HashMap<String, String> leaders) {
		this.leaders = leaders;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public HashMap<String, String> getCookies() {
		return cookies;
	}
	public void setCookies(HashMap<String, String> cookies) {
		this.cookies = cookies;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
