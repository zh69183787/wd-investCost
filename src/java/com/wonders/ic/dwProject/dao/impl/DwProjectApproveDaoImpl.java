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

package com.wonders.ic.dwProject.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.dwProject.entity.bo.DwProjectApprove;
import com.wonders.ic.dwProject.dao.DwProjectApproveDao;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * DwProjectApprove实体定义
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2013-1-9
 * @author modify by $Author$
 * @since 1.0
 */

public class DwProjectApproveDaoImpl extends
		AbstractHibernateDaoImpl<DwProjectApprove> implements
		DwProjectApproveDao {

	@Override
	public List<DwProjectApprove> findByDate(Date sDate) {
		String hql="from DwProjectApprove t ";
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
			hql += "where to_char(t.createDate,'yyyymm') = " + calendar.get(Calendar.YEAR) + month;
			
		}
		hql += " ORDER BY t.acceptTime ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwProjectApprove> findByName(String projectNo,
			String projectName, Date sDate) {
		String hql="from DwProjectApprove t ";
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
		hql += " ORDER BY t.acceptTime ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwProjectApprove> findAllByNameAndNo(String projectName,String projectNo) {
		String hql = "from DwProjectApprove t ";
		if(projectName!=null && !"".equals(projectName)){
			hql += " where t.projectName = '"+projectName+"'";
			if(projectNo!=null && !"".equals(projectNo)){
				hql += " and t.projectNo='"+projectNo+"'";
			}
		}else{
			if(projectNo!=null && !"".equals(projectNo)){
				hql += " where t.projectNo='"+projectNo+"'";
			}
		}
		hql += " order by t.id ASC";
		return getHibernateTemplate().find(hql);
	}
	
	
}
