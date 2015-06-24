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

package com.wonders.ic.dwContract.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.wonders.ic.dwContract.dao.DwContractChangeDao;
import com.wonders.ic.dwContract.entity.bo.DwContractChange;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwContractChangeʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2013-1-6
 * @author modify by $Author$
 * @since 1.0
 */

public class DwContractChangeDaoImpl extends
		AbstractHibernateDaoImpl<DwContractChange> implements
		DwContractChangeDao {
	
	@Override
	public List<DwContractChange> findByDate7(String projectNo,String projectName,Date sDate) {
		String hql="from DwContractChange t ";
		if(projectNo!=null && !projectNo.equals("")){
			hql += "where t.projectNo like '%" +projectNo+"%'";
			if(projectName!=null){
				hql += "and t.projectName like '%" +projectName+"%'";
				if(sDate!=null){
					Calendar calendar = GregorianCalendar.getInstance(); 
					calendar.setTime(sDate); 
					String month;
					if (calendar.get(Calendar.MONTH) + 1 < 10) {
						month = "" + 0 + (calendar.get(Calendar.MONTH) + 1);
					}
					else{
						month = ""  + (calendar.get(Calendar.MONTH) + 1);
					}

					hql += "and to_char(t.createDate,'yyyymm')= "+ calendar.get(Calendar.YEAR) + month;
					
				}
			}
		}else{
			if(projectName!=null && !projectName.equals("")){
				hql += "where t.projectName like '%" +projectName+"%'";
				if(sDate!=null){
					Calendar calendar = GregorianCalendar.getInstance(); 
					calendar.setTime(sDate); 
					String month;
					if (calendar.get(Calendar.MONTH) + 1 < 10) {
						month = "" + 0 + (calendar.get(Calendar.MONTH) + 1);
					}
					else{
						month = ""  + (calendar.get(Calendar.MONTH) + 1);
					}

					hql += "and to_char(t.createDate,'yyyymm')= "+ calendar.get(Calendar.YEAR) + month;
					
				}
			}else{
				if(sDate!=null){
					Calendar calendar = GregorianCalendar.getInstance(); 
					calendar.setTime(sDate); 
					String month;
					if (calendar.get(Calendar.MONTH) + 1 < 10) {
						month = "" + 0 + (calendar.get(Calendar.MONTH) + 1);
					}
					else{
						month = ""  + (calendar.get(Calendar.MONTH) + 1);
					}
					hql += "where to_char(t.createDate,'yyyymm')= "+ calendar.get(Calendar.YEAR) + month;		
				}
			}	
		}
		hql += " ORDER BY t.createDate DESC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwContractChange> findByDate(String projectNo,
			String projectName, String sDate, String eDate, String projectType) {
		String hql ="from DwContractChange t";
		String filter="";
		int count=0;
		if(projectNo!=null && !"".equals(projectNo)){
			filter += " t.projectNo like '%"+projectNo+"%' ";
			count++;
		}
		if(projectName!=null && !"".equals(projectName)){
			if(count>0) filter +=" and ";
			filter += " t.projectName like '%"+projectName+"%' ";
			count++;
		}
		if(sDate!=null && !"".equals(sDate)){
			if(count>0) filter +=" and ";
			filter += " t.signedDate >= '"+sDate+"'";
			count++;
		}
		if(eDate!=null && !"".equals(eDate)){
			if(count>0) filter +=" and ";
			filter += " t.signedDate <='"+eDate+"'";
			count++;
		}
		if(projectType!=null && !"".equals(projectType)){
			if(count>0) filter +=" and ";
			filter += " t.projectType ='"+projectType+"'";
			count++;
		}
		if(count>0){
			hql += " where ";
		}
		hql += filter;
		hql += " order by t.signedDate DESC";
		return getHibernateTemplate().find(hql);
	}
	
	
	
	
}
