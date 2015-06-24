package com.wonders.ic.meetEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */
public class DictConstants {
    public static Map<String,String> dict = new HashMap<String, String>();
    static {
        dict.put("2,1","工程");
        dict.put("2,2","服务");
        dict.put("2,3","货物");
        contractType.A.getS();
    }

    public enum contractType{
        A,B,C;
        public void getS(){}
    }
}
