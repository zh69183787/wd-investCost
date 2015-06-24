package com.wonders.api;

import com.wondersgroup.framework.core.bo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2014/10/31.
 */
public class BaseAction implements ServletResponseAware {

    private Integer pageSize;
    private Integer currentPageNo;
    private String callback;
    private HttpServletResponse httpServletResponse;
    public void outputJson(Map map) throws Exception {
        outputJson(map,null);
    }

    public void outputJson(Map map, String callbackName) throws Exception {
        httpServletResponse.setContentType("text/html;charset=utf-8");

        JSONObject root = new JSONObject();
        for (Object key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Page)
                addPageJsonObject(root, (Page) map.get(key));
            else if(value instanceof List)
                root.put((String) key, JSONArray.fromObject(map.get(key)));
            else
                root.put((String) key,value);
        }


        System.out.println(root.toString());
        outputJson(root,callbackName);
    }

    private void outputJson(JSONObject root,String callbackName) throws Exception{
        if (StringUtils.isNotBlank(callbackName))
            httpServletResponse.getWriter().print(callbackName+"("+root.toString(1,1)+")");
        else
            httpServletResponse.getWriter().print(root.toString());
    }

    private void addPageJsonObject(JSONObject root, Page page) {
        JSONObject pageObject = new JSONObject();
        pageObject.put("pageSize", page.getPageSize());
        pageObject.put("currentPageNo", page.getCurrentPageNo());
        pageObject.put("totalSize", page.getTotalSize());
        pageObject.put("totalPageCount", page.getTotalPageCount());
        root.put("page", pageObject);
    }

    public Integer getPageSize() {
        return (pageSize == null || pageSize == 0) ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPageNo() {
        return (currentPageNo == null || currentPageNo == 0) ? 1 : currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
    
    public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
