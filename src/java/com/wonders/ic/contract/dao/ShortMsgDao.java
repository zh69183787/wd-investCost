package com.wonders.ic.contract.dao;

import java.util.Map;

public interface ShortMsgDao{
	
	public Map<String,String> getUserInfoByName(String loginName);
	
	public void sendMessage(String mobile, String content);
}
