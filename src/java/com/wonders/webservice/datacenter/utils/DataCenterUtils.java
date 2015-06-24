package com.wonders.webservice.datacenter.utils;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wonders.webservice.datacenter.DataImportAPIDelegate;
import com.wonders.webservice.datacenter.DataImportAPIService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DataCenterUtils {
	private String userName="wonder";
	private String pwd="wonder2013!";

	public List<String> setDataInfo(List<Map<String,String>> list){
		DataImportAPIService service = new DataImportAPIService();
		DataImportAPIDelegate delegate = service.getDataImportAPIHandler();
		String result = "";
		List<String> resultList = new ArrayList<String>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				result = delegate.setDataInfo(userName, getMD5(pwd), list.get(i).get("date"), list.get(i).get("content"));
				resultList.add(result);
			}
		}
		return resultList;
	}

	private String getMD5(String value) {
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

    public List<String> setDataInfo(List<Map<String,String>> list,String userName,String pwd){
		DataImportAPIService service = new DataImportAPIService();
		DataImportAPIDelegate delegate = service.getDataImportAPIHandler();
		String result = "";
		List<String> resultList = new ArrayList<String>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				result = delegate.setDataInfo(userName, getMD5(pwd), list.get(i).get("date"), list.get(i).get("content"));
				resultList.add(result);
			}
		}
		return resultList;
	}

    public Map<String,String> getDataInfo(String username,String password,String dataType){
       String result =  callGetDataInfoApi(username, password, dataType);
        HashMap<String,String>  resultMap = new HashMap<String, String>();
        try {
            Document resultDoc =  DocumentHelper.parseText(result);

            Node node  = resultDoc.selectSingleNode("//code");
            if(node!=null&&"1001".equals(node.getText())){
                List<Node> paramNodes = resultDoc.selectNodes("//param");
                for (Node paramNode : paramNodes) {
                    String content = paramNode.selectSingleNode("content").getText();
                    String id = paramNode.selectSingleNode("id").getText();
                    resultMap.put(id, content);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return resultMap;
    }


    public String callGetDataInfoApi(String username,String password,String dataType){
    	if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)){
    		this.userName = username;
    		this.pwd = password;
    	}

    	DataImportAPIService service = new DataImportAPIService();
		DataImportAPIDelegate delegate = service.getDataImportAPIHandler();
		return delegate.getDataInfo(userName, getMD5(pwd), dataType);
    }

  public String confirmGetDataInfo(String username, String password, String dataType,String id){
	  if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(pwd)){
  		this.userName = username;
  		this.pwd = password;
  	}
    	DataImportAPIService service = new DataImportAPIService();
		DataImportAPIDelegate delegate = service.getDataImportAPIHandler();
		return delegate.confirmGetDataInfo(userName, getMD5(pwd), dataType, id);
    }

    public static void main(String[] args) {
        DataCenterUtils utils = new DataCenterUtils();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File("e:\\test.xml"));
            ArrayList<Map<String,String>> list = new ArrayList<Map<String, String>>();
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("date",document.getRootElement().attribute("date").getValue());
            map.put("content",document.asXML());
            list.add(map);
            utils.setDataInfo(list, "mscp", "mscp2013!");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
