package com.wonders.ic;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{
	protected final String P_IGNORE_URLS = "ignoreUrls";
	protected final String URL_SPLITER = ",";
	private String[] ignoreUrl = null;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
	    HttpServletResponse response = (HttpServletResponse) servletResponse;


	    String url = getCurrUrl(request);

        if(url.startsWith("/api/")){
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

	    if (ignoreUrl != null && ignoreUrl.length > 0) {
		    for (int i=0; i<ignoreUrl.length; i++) {

			    if (url.equals(ignoreUrl[i].trim())) {
				    chain.doFilter(servletRequest, servletResponse);
				    return ;
			    }
		    }
	    }

		String returnUrl=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getRequestURI();
		if(request.getQueryString()!=null){
			returnUrl+="?"+request.getQueryString();

		}

	    String token=getCookieByName(request,"token");
	    if(token==null){
	    	returnUrl = returnUrl.replace("&","%26").replace("?","%3F").replace("/","%2F");
	    	response.sendRedirect(request.getContextPath()+"/caClient.jsp?returnUrl="+returnUrl);
	    	return;
	    }else{
	    	chain.doFilter(servletRequest, servletResponse);
	    	return;
	    }
//	    chain.doFilter(servletRequest, servletResponse);			//不经过ca
	}

	public void init(FilterConfig config) throws ServletException {
		String ignoreUrls = config.getInitParameter(P_IGNORE_URLS);
		if (ignoreUrls != null) {
			ignoreUrl = ignoreUrls.split(URL_SPLITER); 
		}
	}
	
	
	private static String getCurrUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String ctxpath = request.getContextPath();
		if (! "".equals(ctxpath)) {
			return uri.substring(ctxpath.length());
		}else{
			return uri;
		}
	}
	
	private static String getCookieByName(HttpServletRequest request, String name) {
		String cookieValue=null;
		Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
            	Cookie cookie = cookies[i];
        		
        		if(name.equals(cookie.getName())){
        			try{
        				cookieValue = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
        			} catch (UnsupportedEncodingException e) {
        				e.printStackTrace();
        			}
        			break;
        		}
            }
        }
        
        return cookieValue;
	}
  
}
