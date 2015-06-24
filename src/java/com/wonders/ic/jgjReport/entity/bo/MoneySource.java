package com.wonders.ic.jgjReport.entity.bo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 项目资金来源
 * 
 * @author Kai Yao
 * @date 2014-04-11
 */
@SuppressWarnings("serial")
public class MoneySource implements Serializable {
	private String unitId;
	private String unitName;
	private String lineName;
	private BigDecimal money;

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
