package com.wonders.ic.contract.entity.bo;

import java.io.Serializable;

public class CompanyRoutePK implements Serializable{

	private String id ;
	private String type;
	//private String pid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/*public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}*/
	
}
