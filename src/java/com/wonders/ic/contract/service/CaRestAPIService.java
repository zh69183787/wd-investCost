package com.wonders.ic.contract.service;



//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wonders.frame.core.utils.EncryptUtil;
import com.wonders.frame.core.utils.JacksonMapper;
import com.wonders.frame.core.utils.RestAPIUtil;
import com.wonders.frame.core.utils.SpringBeanUtil;
import com.wonders.ic.contract.entity.bo.CurrentLogin;


//CA验证
public class CaRestAPIService {
	private static Logger logger=LoggerFactory.getLogger(CaRestAPIService.class);
	private static HashMap<String,String> cookies=new HashMap<String,String>();
	private static void init() {
		
	}
	
	static {
		init();
	}		

	
	public static String getDataFromCa(HttpServletRequest request,HttpServletResponse response,String method,String paramsXml) {
		
		String apiUrl="";
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("method", method);
		hm.put("dataType", "json");

		try {
			Properties properties = new Properties();
			InputStream path = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			properties.load(path);
			String token=cookies.get("token");	
			String sign=EncryptUtil.getMD5(properties.getProperty("appName")+token+method+properties.getProperty("secret"));
			hm.put("appName", properties.getProperty("appName"));
			hm.put("sign", sign);
			hm.put("token", token);
			hm.put("dataParams", "<?xml version=\"1.0\" encoding=\"utf-8\"?><params>"+paramsXml+"</params>");
			apiUrl=properties.getProperty("urlCa")+properties.getProperty("serverPath")+"/"+properties.getProperty("apiName");

		}catch (Exception e) {
			logger.error("Exception Throwable", e);
		}

		return RestAPIUtil.getData(apiUrl, hm);
	
	}
	
	public static CurrentLogin getCurrentLoginInfoFromCa(HttpServletRequest request,HttpServletResponse response){
		String[] cookieNames={"token","deptId","deptName","userId","userName","loginName"};
		cookies=getCookieValue(request,Arrays.asList(cookieNames));
		CurrentLogin currentLogin=new CurrentLogin();
		currentLogin.setCookies(cookies);
		currentLogin.setUserName(cookies.get("userName"));
		currentLogin.setLoginName(cookies.get("loginName"));
		String params="<id>"+cookies.get("deptId")+"</id>";

		//get dept leaders
		String deptLeaderOptions= getDataFromCa(request,response,"getDeptLeaders",params);
		logger.debug("the return deptLeaderOptions from ca Api:"+deptLeaderOptions);
		HashMap<String,Object> hm=JacksonMapper.readValue(deptLeaderOptions, HashMap.class);
		if(hm!=null && hm.containsKey("params")){
			HashMap<String,String> deptLeaders=new HashMap<String,String>();
			if(hm.get("params") instanceof LinkedHashMap){
				HashMap<String,String> tmpMap = (HashMap<String, String>) ((LinkedHashMap)hm.get("params")).get("param");
				deptLeaders.put(tmpMap.get("loginName").toString(), tmpMap.get("name").toString());
			}else{
				List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm.get("params");
				for(HashMap<String,String> hmParam:paramList){
					deptLeaders.put(hmParam.get("loginName").toString(), hmParam.get("name").toString());
				}
			}
			
			currentLogin.setDeptLeaders(deptLeaders);
		}
		
		String deptUserOptions= getDataFromCa(request,response,"getDeptUsers",params);
		logger.debug("the return deptLeaderOptions from ca Api:"+deptLeaderOptions);
		HashMap<String,Object> hm3=JacksonMapper.readValue(deptUserOptions, HashMap.class);
		if(hm3!=null && hm3.containsKey("params")){
			List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm3.get("params");
			HashMap<String,String> deptUsers=new HashMap<String,String>();
			for(HashMap<String,String> hmParam:paramList){
				deptUsers.put(hmParam.get("loginName").toString(), hmParam.get("name").toString());
			}
			
			currentLogin.setDeptUsers(deptUsers);
		}
		
		//get company id
		String deptOptions= getDataFromCa(request,response,"getRelatedNodes",params);
		logger.debug("the return deptOptions from ca Api:"+ deptOptions);
		HashMap<String,Object> hm1=JacksonMapper.readValue(deptOptions, HashMap.class);
		
		if(hm1!=null && hm1.containsKey("params")){
			List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm1.get("params");
			for(HashMap<String,String> hmParam:paramList){
				if(hmParam.get("pid")!=null &&hmParam.get("pid").toString().equals("2500")){
					currentLogin.setCompanyId(hmParam.get("id").toString());
					currentLogin.setCompanyName(hmParam.get("description").toString());
					break;
				}else{
					continue;
				}
			}
		}
		//get company leaders
		params="<id>"+currentLogin.getCompanyId()+"</id>";
		String leaderOptions= getDataFromCa(request,response,"getDeptLeaders",params);
		logger.debug("the return leaderOptions from ca Api:"+leaderOptions);
		HashMap<String,Object> hm2=JacksonMapper.readValue(leaderOptions, HashMap.class);
		if(hm2!=null && hm2.containsKey("params")){
			List<HashMap<String,String>> paramList=(List<HashMap<String,String>>)hm2.get("params");
			HashMap<String,String> leaders=new HashMap<String,String>();
			for(HashMap<String,String> hmParam:paramList){
				leaders.put(hmParam.get("loginName").toString(), hmParam.get("name").toString());
			}
			
			currentLogin.setLeaders(leaders);
		}
		return currentLogin;
	}
	
	


	private static HashMap<String,String> getCookieValue(HttpServletRequest request,List<String> cookieName){
		Cookie[] cookies = request.getCookies();
		HashMap<String,String> hmCookies=new HashMap<String,String>();
		if(cookies !=null){
			int j=0;
			for(int i=0;i<cookies.length;i++){
				if(j>=cookieName.size()){
					break;
				}
				Cookie cookie = cookies[i];				
				if(cookieName.contains(cookie.getName())){
					j++;
					try{
						hmCookies.put(cookie.getName().replace("kpi_", ""), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
					} catch (UnsupportedEncodingException e) {
						logger.error("Exception Throwable", e);
					}
					
					
				}						
			}
		}
		
		return hmCookies;
	}
	
}


