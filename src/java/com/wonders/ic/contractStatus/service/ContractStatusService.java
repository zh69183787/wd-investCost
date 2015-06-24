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

package com.wonders.ic.contractStatus.service;

import java.util.List;
import java.util.Map;

import com.wonders.ic.contractStatus.entity.bo.ContractStatus;
import com.wondersgroup.framework.core.bo.Page;

/**
 * ҵ�����
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-28
 * @author modify by $Author$
 * @since 1.0
 */

public interface ContractStatusService {
	/**
	 * ɾ��ʵ�����
	 * 
	 * @param contractStatus
	 */
	public void deleteContractStatus(ContractStatus contractStatus);

	/**
	 * 
	 * ͨ��IDװ����Ӧ�Ķ���ʵ������Ӧ��ʵ�岻���ڣ�����null
	 * 
	 * @param id
	 *            ����
	 * @return
	 */
	public ContractStatus findContractStatusById(String id);

	/**
	 * �־û�һ��ʵ�����
	 * 
	 * @param contractStatus
	 */
	public void addContractStatus(ContractStatus contractStatus);

	/**
	 * ������ݵ���ݿ�
	 * 
	 * @param contractStatus
	 *            ʵ��
	 */
	public void updateContractStatus(ContractStatus contractStatus);

	/**
	 * ��ݷ�ҳ������з�ҳ��ѯ.
	 * 
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��¼��.
	 * @return
	 */
	public Page findContractStatusByPage(int pageNo, int pageSize);

	/**
	 * ���Map�й��������ͷ�ҳ������з�ҳ��ѯ.
	 * 
	 * @param filter
	 *            ��������<propertyName,properyValue>
	 * @param pageNo
	 *            ��ǰҳ��
	 * @param pageSize
	 *            ÿҳ��ʾ��¼��.
	 * @return
	 */
	public Page findContractStatusByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public List<ContractStatus> findStatusList(String id,String type);
	
	public List<Object[]> findByTypeAndDateRange(String type,String startDate,String endDate);
	
	/**
	 * 查询总价
	 * @param contractId
	 * @param tyupe
	 * @return
	 */
	public double findSumOfContractStatusByContractIdAndType(String contractId,String tyupe);


	public void saveAll(List<ContractStatus> list);

	public boolean isResultExist(String year,String contractId,String type);

}
