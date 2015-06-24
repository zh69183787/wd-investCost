package com.wonders.ic.meetEvent.entity.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public class FixContractVo implements Serializable {
    public String company;
    public Double price = 0.0;
    public Long count = 0L;
    public Double fixPrice = 0.0;
    public Long fixCount = 0L;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getFixPrice() {
        return fixPrice;
    }

    public void setFixPrice(Double fixPrice) {
        this.fixPrice = fixPrice;
    }

    public Long getFixCount() {
        return fixCount;
    }

    public void setFixCount(Long fixCount) {
        this.fixCount = fixCount;
    }
}
