<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.security.MessageDigest"%>
<%@ page import="java.security.NoSuchAlgorithmException"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="org.dom4j.DocumentHelper"%>
<%@ page import="org.dom4j.Document"%>
<%@ page import="org.dom4j.Element"%>
<%@ page import="org.dom4j.Node"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.io.DataOutputStream"%>
<%@ page import="java.io.OutputStreamWriter"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setHeader("Cache-Control", "no-store");// Public
response.setHeader("Pragma", "no-cache");
response.setHeader("P3P","CP=CAO PSA OUR");
response.setDateHeader("Expires", 0);
String action=request.getParameter("action");
String sessionKey=request.getParameter("sessionKey");
String returnUrl=request.getParameter("returnUrl");
String appName="";//="stptm"; 
String secret="";//="124a77748fcb48a7a0863f30970a2a04";       
String urlCa="";//="http://10.1.43.32:8088/ca";
String serverPath="";//="/services/api";
String apiName="";//="dataExchange";

Properties properties = new Properties();
String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
try {
	FileInputStream fis = new FileInputStream(configPath);
	properties.load(fis);
	appName = properties.getProperty("appName");
	secret = properties.getProperty("secret");
	urlCa = properties.getProperty("urlCa");
	serverPath = properties.getProperty("serverPath");
	apiName = properties.getProperty("apiName");			
} catch (Exception e) {
	//e.printStackTrace();
	appName="stptm"; 
	secret="124a77748fcb48a7a0863f30970a2a04";       
	urlCa="http://10.1.14.20:7001/ca";
	serverPath="/services/api";
	apiName="dataExchange";
}
String thisPageUrl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getRequestURI();
if(returnUrl!=null && thisPageUrl.indexOf(returnUrl)!=-1){//若本页面地址被作为returnUrl参数传入,则默认回到首页面
  	returnUrl=path;
}
if(returnUrl!=null){
	request.getSession().setAttribute("returnUrl",returnUrl);	
}
%>
<%! 
	public String getMD5(String value) {  
        String result = null;  
        try{  
            byte[] valueByte = value.getBytes();  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(valueByte);  
            result = toHex(md.digest());  
        }catch(NoSuchAlgorithmException e1){  
            e1.printStackTrace();  
        }  
        return result;  
    }  

    // 将传递进来的字节数组转换成十六进制的字符串形式并返回  
    private String toHex(byte[] buffer){  
        StringBuffer sb = new StringBuffer(buffer.length * 2);  
        for (int i = 0; i < buffer.length; i++){  
            sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));  
            sb.append(Character.forDigit(buffer[i] & 0x0f, 16));  
        }  
        return sb.toString();  
    } 
    

     private void saveCookie(HttpServletResponse response,String Name,String value) throws UnsupportedEncodingException{
    		Cookie cookie = new Cookie(Name, java.net.URLEncoder.encode(value,"utf-8"));
			cookie.setPath("/");		
			cookie.setMaxAge(60*60);
			response.addCookie(cookie);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>stptm中转页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body> 
  stptm中转页面 <br>
  <%
  if(action!=null&&action.equalsIgnoreCase("logout")){
  		Cookie cookie = new Cookie("token", null);  
        cookie.setMaxAge(0);  
     	cookie.setPath("/");	
        response.addCookie(cookie);	 
		response.sendRedirect(urlCa+"/logout.jsp");	
  }else if(sessionKey==null){  	
  	if(returnUrl!=null){
			returnUrl = returnUrl.replace("&","%26").replace("?","%3F").replace("/","%2F"); 
		}
		response.sendRedirect(urlCa+"/login.jsp?appName="+appName+"&returnUrl="+returnUrl);

}else{  
		URL url = null;
		HttpURLConnection http = null;
		String textEntity="";
		try {
			url = new URL(urlCa+serverPath+"/"+apiName);
			http = (HttpURLConnection) url.openConnection();
			http.setDoInput(true);
			http.setDoOutput(true);
			http.setUseCaches(false);
			http.setConnectTimeout(50000);
			http.setReadTimeout(50000);
			http.setRequestMethod("POST");
			//http.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");			
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.connect();
			String param = "&appName=" + appName + "&token=" + sessionKey + "&method=SESSIONINFO.GET&dataType=xml&sign=" + getMD5(appName+sessionKey+"SESSIONINFO.GET"+secret);

         	OutputStreamWriter osw=new OutputStreamWriter(http.getOutputStream(),"utf-8");
          	osw.write(param);
          	osw.flush();
          	osw.close();

			if (http.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(),"utf-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					textEntity += inputLine;
				}
				in.close();
				try {
					Document doc = DocumentHelper.parseText(textEntity);
		
					Element root = doc.getRootElement();
					if(root.element("code").getTextTrim().equalsIgnoreCase("100")){
						Element params=root.element("params");
						List<Element> list=params.elements();
						Element eParam=(Element)list.get(0);
						
						Element eToken = eParam.element("token");
						Element eTuser = eParam.element("tuser");
						Element eCsuser = eParam.element("csuser");
						
						Element eUserId = eCsuser.element("userId");
						Element eLoginName = eCsuser.element("loginName");
						Element eUserName = eCsuser.element("userName");
						Element eDeptId = eCsuser.element("deptId");
						Element eDeptName = eCsuser.element("deptName");
															
	       /*具体cookie的存储模式需要客户端自行处理*/
						saveCookie(response,"userId",eUserId.getTextTrim());			
						saveCookie(response,"loginName",eLoginName.getTextTrim());				
						saveCookie(response,"userName",eUserName.getTextTrim());				
						saveCookie(response,"deptId",eDeptId.getTextTrim());				
						saveCookie(response,"deptName",eDeptName.getTextTrim());
						saveCookie(response,"token",eToken.getTextTrim());	
						//把登陆人的中文名称和id存放到session中
						session.setAttribute("userName",eUserName.getTextTrim());
						session.setAttribute("loginName",eLoginName.getTextTrim());
						
						response.sendRedirect(returnUrl);
						
					}else{
	%>
				无法取得相关认证用户信息:<%=root.element("description").getTextTrim() %><br><br>
				请重新<a href="<%=urlCa %>/logout.jsp?appName=<%=appName%>&returnUrl=<%=returnUrl%>" target="_self">登录认证中心！</a>
				
	<%				
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	%>
					CA认证中心服务端返回数据格式错误，请联系管理员！
				
	<%					
				}    	       			
						
			}else{
			
%>
			无法取得相关认证用户信息<br>
			请重新<a href="<%=urlCa%>/logout.jsp?appName=<%=appName%>&returnUrl=<%=returnUrl%>" target="_self">登录认证中心！</a>
			
<%			
			
			}
		} catch (Exception e) {
	%>
					链接CA服务请求出错，请联系管理员！
				
	<%	
		} finally {
			if (http != null) http.disconnect();
		}
		
}
	
	
%>
   </body>
</html>
