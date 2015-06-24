package com.wonders.ic;

import com.wondersgroup.framework.config.SystemParameter;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.vo.JsonDateValueProcessor;
import com.wondersgroup.framework.core.web.vo.VOUtils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015-03-12.
 */
public class JsonUtils {

    public static String getJsonDataFromPage(Page page,Class clz,String[] excludeProps){
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludeProps);
        return clz != null?getJsonData(VOUtils.transformPage(page, clz),jsonConfig,excludeProps).toString():getJsonData(page,jsonConfig,excludeProps);
    }

    public static String getJsonData(Object bean,JsonConfig jsonConfig,String[] excludeProps) {
        jsonConfig = getJsonConfig(null);
        jsonConfig.setExcludes(excludeProps);
        return JSONObject.fromObject(bean, jsonConfig).toString();
    }

    public static JsonConfig getJsonConfig(String dateFormat) {
        JsonDateValueProcessor beanProcessor = new JsonDateValueProcessor();
        if(dateFormat != null) {
            SimpleDateFormat jsonConfig = new SimpleDateFormat(dateFormat);
            beanProcessor.setDateFormat(jsonConfig);
        }

        JsonConfig jsonConfig1 = new JsonConfig();
        jsonConfig1.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig1.registerJsonValueProcessor(Date.class, beanProcessor);
        String defaultValue = SystemParameter.getString("json_number_defaultvalue");
        if(StringUtils.isNotBlank(defaultValue) && defaultValue.equals("null")) {
            jsonConfig1.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
                public Object getDefaultValue(Class type) {
                    return null;
                }
            });
            jsonConfig1.registerDefaultValueProcessor(Long.class, new DefaultValueProcessor() {
                public Object getDefaultValue(Class type) {
                    return null;
                }
            });
            jsonConfig1.registerDefaultValueProcessor(Float.class, new DefaultValueProcessor() {
                public Object getDefaultValue(Class type) {
                    return null;
                }
            });
            jsonConfig1.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor() {
                public Object getDefaultValue(Class type) {
                    return null;
                }
            });
            jsonConfig1.registerDefaultValueProcessor(BigInteger.class, new DefaultValueProcessor() {
                public Object getDefaultValue(Class type) {
                    return null;
                }
            });
            jsonConfig1.registerDefaultValueProcessor(BigDecimal.class, new DefaultValueProcessor() {
                public Object getDefaultValue(Class type) {
                    return null;
                }
            });
        }

        return jsonConfig1;
    }
}
