/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.ic.dwProject.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.ic.dwProject.entity.bo.DwProjectInvestPay;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public interface DwProjectInvestPayService {
	/**
	 * 删除实体对象
	 * 
	 * @param dwProjectInvestPay
	 */
	public void deleteDwProjectInvestPay(DwProjectInvestPay dwProjectInvestPay);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public DwProjectInvestPay findDwProjectInvestPayById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param dwProjectInvestPay
	 */
	public void addDwProjectInvestPay(DwProjectInvestPay dwProjectInvestPay);

	/**
	 * 更新数据到数据库
	 * 
	 * @param dwProjectInvestPay
	 *            实体
	 */
	public void updateDwProjectInvestPay(DwProjectInvestPay dwProjectInvestPay);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findDwProjectInvestPayByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findDwProjectInvestPayByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public List<DwProjectInvestPay> findByName8(String projectNo,String projectName,String sDate,String eDate);
	
	
	/**
	 * 计算所有项目（被合同绑定过的）下的合同实际支付金额,
	 * 并存放在DW_PROJECT_INVEST_PAY表中,作为报表8的数据
	 */
	public void updateCountAllPaysForProject();
	
	
}
