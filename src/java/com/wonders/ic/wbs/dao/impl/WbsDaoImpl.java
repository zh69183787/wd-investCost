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

package com.wonders.ic.wbs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.ic.wbs.dao.WbsDao;
import com.wonders.ic.wbs.entity.bo.Wbs;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * Wbsʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-11-14
 * @author modify by $Author$
 * @since 1.0
 */

public class WbsDaoImpl extends AbstractHibernateDaoImpl<Wbs> implements WbsDao {
	public Page findWbsByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Wbs t ";
		String countHql = "select count(*) from Wbs t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				filterPart += "t." + paramName + "=:" + paramName;
				args.add(new HqlParameter(paramName, filter.get(paramName)));
				filterCounter++;
			}
		}
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

	private void addNodeByOrder(List<Wbs> listBeforeSorting,List<Wbs> listAfterSorting,String parentId,Long order,Long level,String wbsId,Map<String,List<Object>> map){
		Wbs bo = new Wbs();
		String getId = "";
		String getParentId = "";
		long getOrder = 0;
		long getLevel = level;
		boolean flag = false;
		boolean flag1 = false;
		List<Object> list = new ArrayList<Object>();
		
		for(int i=0;i<listBeforeSorting.size();i++){//遍历判断有无此节点
			bo = listBeforeSorting.get(i);	
			getId = bo.getId();
			getParentId = bo.getParentId();
			getOrder = bo.getWbsOrder();
			if(getParentId.equals(parentId)&&order==getOrder){
				bo.setWbsId(wbsId);
				listAfterSorting.add(bo);
				listBeforeSorting.remove(i);
				if(listBeforeSorting.size()>0){
					for(int j=0;j<listBeforeSorting.size();j++){//遍历判断有无子节点
						if(getId.equals(listBeforeSorting.get(j).getParentId())){
							list.add(getParentId);
							list.add(getOrder);
							list.add(level);
							list.add(wbsId);
							map.put(getId, list);
							addNodeByOrder(listBeforeSorting,listAfterSorting,getId,(long)1,level+1,wbsId+".1",map);						
							flag = true;
							break;
						}
					}
					if(!flag){
						for(int n=0;n<getLevel;n++){
							for(int m=0;m<listBeforeSorting.size();m++){//遍历判断同级节点中有无order更大的节点
								if(getParentId.equals(listBeforeSorting.get(m).getParentId())){
									String[] str = wbsId.split("\\.");
									int lastNum = Integer.parseInt(str[str.length-1]);
									lastNum += 1;
									str[str.length-1] = String.valueOf(lastNum);
									wbsId = "";
									for(int k=0;k<str.length;k++){
										if(k!=0){
											wbsId += ".";
										}
										wbsId += str[k];
									}
									addNodeByOrder(listBeforeSorting,listAfterSorting,getParentId,order+1,level,wbsId,map);
									flag1 = true;
									break;
								}
							}
							if(!flag1&&n!=getLevel-1){//若没有，回到上级节点
								list = map.get(getParentId);
								getParentId = (String)list.get(0);	
								order = (Long)list.get(1);
								level = (Long)list.get(2);	
								wbsId = (String)list.get(3);
							}
							flag1 = false;
						}
					}
					break;
				}				
			}
		}
	}
	
	public List<Wbs> sortByWBS(String wbsObject,String projectId){
		List<Wbs> listAfterSorting = new ArrayList<Wbs>();
		String hql = "select t from Wbs t where t.removed = '0' and t.type = '1' and t.wbsObject = '"+wbsObject+"' and t.wbsObjectId = '"+projectId+"' order by t.wbsId";
		List<Wbs> listBeforeSorting = this.getHibernateTemplate().find(hql);
		if(listBeforeSorting!=null && listBeforeSorting.size()>0){
			Map<String,List<Object>> map = new HashMap<String,List<Object>>();
			addNodeByOrder(listBeforeSorting,listAfterSorting,"0",(long)1,(long)1,"1",map);
		}
		return listAfterSorting;
	}


	@Override
	public void updateWbsOrderByIncrease(String object,String objectId, String parentId, long level,long order,String ifHasSpecial,String specialOrder) {
		String hql = "";
		if("yes".equals(ifHasSpecial)){
			hql = "update Wbs t set  t.wbsOrder = (to_number(t.wbsOrder)+1) where t.wbsObjectId = '"+objectId+"' " +
			"and t.parentId ='"+parentId+"' and t.wbsLevel="+level+" and (t.wbsOrder >="+order+" or (t.wbsOrder = "+(order-1)+" and t.type = '2' and t.specialOrder >"+specialOrder+")) and t.removed='0' and t.wbsObject='"+object+"'";
		}else{
			hql = "update Wbs t set  t.wbsOrder = (to_number(t.wbsOrder)+1) where t.wbsObjectId = '"+objectId+"' " +
			"and t.parentId ='"+parentId+"' and t.wbsLevel="+level+" and (t.wbsOrder >="+order+" or (t.wbsOrder ="+(order-1)+" and t.type = '2')) and t.removed='0' and t.wbsObject='"+object+"'";
		}
		
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	@Override
	public void updateWbsOrderByDecrease(String object,String objectId, String parentId,long level, long order) {
		String hql = "update Wbs t set  t.wbsOrder = (to_number(t.wbsOrder)-1) where t.wbsObjectId = '"+objectId+"' " +
			"and t.parentId ='"+parentId+"' and t.wbsLevel="+level+" and t.wbsOrder >="+order+" and t.removed='0' and t.wbsObject='"+object+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}


	@Override
	public Wbs findByObjectAndObjectIdAndNodeId(String object, String objectId,String nodeId) {
		String hql ="from Wbs t where t.wbsObject='"+object+"' and t.wbsObjectId='"+objectId+"' and t.nodeId='"+nodeId+"' and t.removed='0'";
		List<Wbs> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public void deleteByIdLogical(String id) {
		String hql = "update Wbs t set t.removed='1' where t.id='"+id+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Wbs> findAllFirstLevelIdByObjectAndId(String object,String objectId) {
		String hql = "from Wbs t where t.wbsObject='"+object+"' and t.wbsObjectId= '"+objectId+"' and t.removed='0' and t.wbsLevel=1 order by t.wbsOrder ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Wbs> findAllWbsHierarchyByDown(String object, String objectId,String id) {
		String hql ="from Wbs t where t.wbsObject='"+object+"' and t.wbsObjectId='"+objectId+"' and t.parentId='"+id+"' and t.removed='0' order by t.wbsOrder asc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteAllOnLogically(List<Wbs> wbsList) {
		if(wbsList==null || wbsList.size()<1) return;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<wbsList.size(); i++){
			sb.append("'"+wbsList.get(i).getId()+"',");
		}
		String idsFilter = sb.toString().substring(0,sb.length()-1);
		String hql = "update Wbs t set t.removed='1' where t.id in("+idsFilter+") and t.removed='0' ";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}


	//找出特殊行
	public List<Wbs> findSpecialRows(String wbsObject,String projectId){
		List<Wbs> list = new ArrayList<Wbs>();
		String hql = "select t from Wbs t where t.removed = '0' and t.type = '2' and t.wbsObject = '"+wbsObject+"' and t.wbsObjectId = '"+projectId+"' order by t.wbsId,t.specialOrder desc";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public Wbs findLastLevelByObjectAndObjectId(String object,String objectId) {
		String hql = "from Wbs t where t.wbsObject='"+object+"' and t.wbsObjectId='"+objectId+"' and t.removed='0' and t.type='1' order by t.wbsLevel DESC"; 
		List<Wbs> wbsList = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(wbsList==null || wbsList.size()<1) return null;
		return wbsList.get(0);
	}

	@Override
	public Wbs findById(String id) {
		
		return (Wbs) getHibernateTemplate().load(Wbs.class, id);
	}

	@Override
	public List<Wbs> findAllByNodeIds(String object, String objectId,List<String> nodeIdList) {
		if(nodeIdList==null || nodeIdList.size()<1) return null;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<nodeIdList.size(); i++){
			sb.append("'").append(nodeIdList.get(i)).append("',");
		}
		String filter = sb.substring(0, sb.length()-1);
		String hql ="from Wbs t where t.wbsObject='"+object+"' and t.wbsObjectId='"+objectId+"' and t.nodeId in ("+filter+") and t.removed='0'" +
				" and t.id not in (select parentId from Wbs t2 where t2.wbsObject='"+object+"' and t2.wbsObjectId='"+objectId+"' and t2.removed='0') order by t.wbsLevel,wbsOrder ";
		
		return getHibernateTemplate().find(hql);
	}


	@Override
	public void updateWbsSpecialOrder(String object,String objectId, String parentId,long level, long order,long specialOrder) {
		String hql = "update Wbs t set  t.specialOrder = (to_number(t.specialOrder)+1) where t.wbsObjectId = '"+objectId+"' " +
			"and t.parentId ='"+parentId+"' and t.wbsLevel="+level+" and t.wbsOrder ="+order+" and t.specialOrder > "+specialOrder+" and t.removed='0' and t.type = '2' and t.wbsObject='"+object+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Wbs> findAllByObjectAndObjectIdAndLevel(String object,String objectId, long level) {
		String hql ="FROM Wbs t WHERE t.wbsObject='"+object+"' AND t.wbsObjectId='"+objectId+"' AND t.wbsLevel='"+level+"' AND t.removed='0' AND t.type='1' ORDER BY t.parentId ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteAllByObjectIdOnLogical(String object, String objectId) {
		String hql ="update Wbs t set t.removed='1' where t.wbsObject='"+object+"' and t.wbsObjectId='"+objectId+"' and t.removed='0'"; 
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	


	

}
