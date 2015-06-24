package com.wonders.ic.meetEvent.entity.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class SignedContractVo implements Serializable{
    public String company;
    public Double price = 0.0;
    public Map<String,Long> count = new HashMap<String, Long>();//[{"2,1":10},]

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<String, Long> getCount() {
        return count;
    }

    public void setCount(Map<String, Long> count) {
        this.count = count;
    }
}
