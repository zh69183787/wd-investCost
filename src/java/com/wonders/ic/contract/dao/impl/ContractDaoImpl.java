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

package com.wonders.ic.contract.dao.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wonders.api.dto.ContractApiDto;
import com.wonders.api.dto.ContractChangeItemDto;
import com.wonders.ic.contract.dao.ContractDao;
import com.wonders.ic.contract.entity.bo.CompanyRoute;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wonders.ic.contract.entity.bo.HtBa;
import com.wonders.ic.contract.entity.bo.HtXx;
import com.wonders.ic.contract.entity.bo.KpiClear;
import com.wonders.ic.contract.entity.bo.KpiClearStatus;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * Contractʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-10-9
 * @author modify by $Author$
 * @since 1.0
 */

public class ContractDaoImpl extends AbstractHibernateDaoImpl<Contract>implements ContractDao {
	
	private JdbcTemplate jdbcTemplate;  
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Page findContractByPage(Map<String, Object> filter, int pageNo,int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Contract t ";
		String countHql = "select count(*) from Contract t ";
		String filterPart = "";
		int filterCounter = 0;
		
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("selfNo") || paramName.equals("contractNo") || paramName.equals("projectName") 
						|| paramName.equals("projectNo") || paramName.equals("contractOwnerName") || paramName.equals("contractOwnerExecuteName") ){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("contractType")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)+"%"));
				}else if(paramName.equals("contractSignedDate")){
					filterPart += "t." + paramName + " >=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("contractPrice")){
					filterPart += "t.contractPrice is not null and to_number(t." + paramName + ") >=to_number(" + filter.get(paramName)+")";
				}else if(paramName.equals("contractSignedEndDate")){
					filterPart += "t.contractSignedDate" + " <=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("contractName")){
					String contractName = filter.get("contractName").toString();
					if(contractName.indexOf(" ")>0){			//回车符号
						String[] nameArray = contractName.split("\\s");
						if(nameArray!=null && nameArray.length>0){
							for(int m=0; m<nameArray.length; m++){
								if(m!=nameArray.length-1){
									filterPart += "t.contractName like '%"+nameArray[m]+"%' and ";
								}else{
									filterPart += "t.contractName like '%"+nameArray[m]+"%'";
								}
							}
						}
					}else{
						filterPart += "t." + paramName + " like :" + paramName;
						args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
					}
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		
		filterPart += " order by t.contractSignedDate DESC,t.createDate DESC";
		//filterPart += " order by t.createDate DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}
	
	public Page findContractByPage(Map<String, Object> filter, int pageNo,int pageSize,List<String> addDeptIdList) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Contract t ";
		String countHql = "select count(*) from Contract t ";
		String filterPart = "";
		int filterCounter = 0;
		
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0 ) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("selfNo") || paramName.equals("contractNo") || paramName.equals("projectName") 
						|| paramName.equals("projectNo") || paramName.equals("contractOwnerName") || paramName.equals("contractOwnerExecuteName") ){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("contractAttribute")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
					
				}
//                else if(paramName.equals("contractAttributeId")){
//					filterPart += "t." + paramName + " = :" + paramName;
//					args.add(new HqlParameter(paramName, filter.get(paramName)));
//				}
				else if(paramName.equals("contractType")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)+"%"));
				}else if(paramName.equals("contractSignedDate")){
					filterPart += "t." + paramName + " >=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("contractPrice")){
					filterPart += " t.contractPrice is not null and to_number(t." + paramName + ") >=to_number(" + filter.get(paramName)+")";
				}else if(paramName.equals("contractSignedEndDate")){
					filterPart += "t.contractSignedDate" + " <=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("current")){
					filterPart += "t.approvalDate <='" +filter.get("current")+"'";
				}else if(paramName.equals("hasQuantities")){
					if(filter.get(paramName).equals("2")){		//未绑定
						filterPart += "t.id not in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null and q.investEstimateId is not null group by q.contractId)";
					}else if(filter.get(paramName).equals("1")){		//已绑定
						filterPart += "t.id in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null and q.investEstimateId is not null group by q.contractId)";
					}
				}else if(paramName.equals("contractName")){
					String contractName = filter.get("contractName").toString();
					if(contractName.indexOf(" ")>0){			//回车符号
						String[] nameArray = contractName.split("\\s");
						if(nameArray!=null && nameArray.length>0){
							for(int m=0; m<nameArray.length; m++){
								if(m!=nameArray.length-1){
									filterPart += "t.contractName like '%"+nameArray[m]+"%' and ";
								}else{
									filterPart += "t.contractName like '%"+nameArray[m]+"%'";
								}
							}
						}
					}else{
						filterPart += "t." + paramName + " like :" + paramName;
						args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
					}
				}else if("complemented".equals(paramName)){
					Object complemented = filter.get("complemented"); 
					if(complemented!=null && "yes".equals(complemented)){
						filterPart += " t.contractType in ('2','2,1','2,2','2,3') and t.removed=0 " +
								" and (t.contractName is null or t.contractNo is null or t.contractPrice is null" +
								" or t.contractOwnerName is null or t.contractOwnerExecuteName is null or t.buildSupplierName  is null"+
								" or t.payType is null or t.contractPassedDate is null or t.contractStartDate is null  or t.contractSignedDate is null"+
								" or t.contractEndDate is null or t.contractStatus is null or t.inviteBidType is null or t.projectName is null)";	
					}else{
						filterPart = StringUtils.stripEnd(filterPart, "and");
					}
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		StringBuilder sb = null;
		if(filter.get("contractOwnerExecuteId")==null || "center".equals(filter.get("contractOwnerExecuteId"))){
			sb = new StringBuilder();
			if(addDeptIdList!=null && addDeptIdList.size()>0){
				for(int i=0; i<addDeptIdList.size(); i++){
					sb.append("'").append(addDeptIdList.get(i)).append("',");
				}
				if(sb!=null && sb.length()>1)
					filterPart += " and t.contractOwnerExecuteId in ("+sb.deleteCharAt(sb.length()-1)+")";
			}
		}
		/*if(status){
			filterPart += "and t.id not in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null and q.investEstimateId is null)";
		}*/
		filterPart += " order by nvl(t.contractSignedDate,to_date('1910-1-1','yyyy-mm-dd')) DESC,t.createDate DESC";
		//filterPart += " order by t.createDate DESC,t.id DESC";
		
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public Page findContractByPageWithoutExecuteId(Map<String, Object> filter,int pageNo, int pageSize, List<String> addDeptIdList) {
		
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Contract t ";
		String countHql = "select count(*) from Contract t ";
		String filterPart = "";
		int filterCounter = 0;
		
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				
				if (filterCounter > 0 ) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("selfNo") || paramName.equals("contractNo") || paramName.equals("projectName") 
						|| paramName.equals("projectNo") || paramName.equals("contractOwnerName") || paramName.equals("contractOwnerExecuteName") ){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("contractType")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)+"%"));
				}else if(paramName.equals("contractSignedDate") ){
					filterPart += "t." + paramName + " >=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("contractPrice")){
					filterPart += " t.contractPrice is not null and to_number(t." + paramName + ") >=to_number(" + filter.get(paramName)+")";
				}else if(paramName.equals("contractSignedEndDate")){
					filterPart += "t.contractSignedDate" + " <=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("current")){
					filterPart += "t.approvalDate <='" +filter.get("current")+"'";
				}else if(paramName.equals("hasQuantities")){
					if(filter.get(paramName).equals("2")){		//未绑定
						filterPart += "t.id not in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null and q.investEstimateId is not null group by q.contractId)";
					}else if(filter.get(paramName).equals("1")){		//已绑定
						filterPart += "t.id in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null and q.investEstimateId is not null group by q.contractId)";
					}
				}else if(paramName.equals("contractName")){
					String contractName = filter.get("contractName").toString();
					if(contractName.indexOf(" ")>0){			//回车符号
						String[] nameArray = contractName.split("\\s");
						if(nameArray!=null && nameArray.length>0){
							for(int m=0; m<nameArray.length; m++){
								if(m!=nameArray.length-1){
									filterPart += "t.contractName like '%"+nameArray[m]+"%' and ";
								}else{
									filterPart += "t.contractName like '%"+nameArray[m]+"%'";
								}
							}
						}
					}else{
						filterPart += "t." + paramName + " like :" + paramName;
						args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
					}
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		StringBuilder sb = null;
		if(filter.get("contractOwnerExecuteId")==null){
			sb = new StringBuilder();
			if(addDeptIdList!=null && addDeptIdList.size()>0){
				for(int i=0; i<addDeptIdList.size(); i++){
					sb.append("'").append(addDeptIdList.get(i)).append("',");
				}
				if(sb!=null && sb.length()>1)
					filterPart += " and (t.contractOwnerExecuteId not in ("+sb.deleteCharAt(sb.length()-1)+") or t.contractOwnerExecuteId is null)";
			}
		}
		//filterPart += " order by t.createDate DESC,t.id DESC";
		filterPart += " order by NVL(t.contractSignedDate,to_date('1910-1-1','yyyy-mm-dd')) DESC,t.createDate DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	@Override
	public Page findByContractName(Map<String, Object> filter, int pageNo,int pageSize){
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Contract t left outer join t.contractChangeProtocols p ";
		String countHql = "select count(*) from Contract t ";
		String filterPart = "";
		
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				String paramName = i.next();
				if(paramName.equals("contractName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}
			}
		}
		
		filterPart += " order by t.contractSignedDate DESC,t.createDate DESC";
		Page page = findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
		List<Contract> result = page.getResult();
		List<ContractApiDto> finalResult = new ArrayList<ContractApiDto>();
		for(Contract con : result){
			ContractApiDto apiDto = new ContractApiDto();
			apiDto.setId(con.getId());
			apiDto.setContractName(con.getContractName());
			apiDto.setContractNo(con.getContractNo());
			apiDto.setSelfNo(con.getSelfNo());
			apiDto.setContractPrice(con.getContractPrice());
			apiDto.setBuildSupplierName(con.getBuildSupplierName());
			apiDto.setContractStartDate(con.getContractStartDate());
			apiDto.setContractEndDate(con.getContractEndDate());
            apiDto.setContractType(con.getContractType());
			
			List<ContractChangeProtocol> proList = new ArrayList<ContractChangeProtocol>();
			for(ContractChangeProtocol tmp : con.getContractChangeProtocols()){
				ContractChangeProtocol protocol = new ContractChangeProtocol();
				protocol.setId(tmp.getId());
				protocol.setChangePrice(tmp.getChangePrice());
				protocol.setContractNo(tmp.getContractNo());
				protocol.setContractName(tmp.getContractName());
				proList.add(protocol);
			}
			apiDto.setContractChangeProtocols(proList);
			finalResult.add(apiDto);
		}
		page.setResult(finalResult);
		return page;
	}
	
	@Override
	public Page findContractChangeItems(Map<String, Object> filter,String loc, int pageNo,int pageSize){
		
		if (filter == null)
			filter = new HashMap<String, Object>();
		String hql = "select t.id from ContractChangeItem t left outer join t.contract c where t.removed='0' ";
		String filterPart = "";
		
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				String paramName = i.next();
				if(paramName.equals("contractName")){
					filterPart += " and c.contractName like :" + paramName;
                    filter.put(paramName,"%"+filter.get(paramName)+"%");
				}else if(paramName.equals("contractId")){
					filterPart += " and c.id like :" + paramName;
                    filter.put(paramName,"%"+filter.get(paramName)+"%");
				}else if(paramName.equals("contractNo")){
					filterPart += " and t.changeContractNo like :" + paramName;
                    filter.put(paramName,"%"+filter.get(paramName)+"%");
				}
			}
		}
        String url = "/";
        Page page =   new Page();
        try {
            Resource resource = new ClassPathResource("/config.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            url = props.getProperty("apiUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> result = getSession().createQuery(hql + filterPart).setProperties(filter).list();
        StringBuffer stringBuffer = new StringBuffer();
		if(result!=null && result.size()>0){
		
			for(String id : result){
                stringBuffer.append("'").append(id).append("',");
			}

            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
            String processIdName = "";
            if(StringUtils.isBlank(loc)||"protocol".equals(loc)){
                processIdName = "contract_change_protocol_id";
            }else if("agreement".equals(loc)){
                processIdName = "contract_change_agreement_id";
            }else{
                processIdName = "contract_change_protocol_id";
            }
            BeanPropertyRowMapper argTypes = new BeanPropertyRowMapper(ContractChangeItemDto.class);
            String sql = "select * from ( select row_.*, rownum rownum_ from (select id,change_content changeContent,change_price changePrice,change_type changeType,change_nature changeNature,change_workload changeWorkload,is_picture isPicture,change_contract_no changeContractNo,change_serial_no changeSerialNo,change_put_forword changePutForword," +
                    "change_item_description changeItemDescription,change_reason_description changeReasonDescription,contract_name contractName,attach,change_item_type1 changeItemType1,change_item_type2 changeItemType2,contract_id contractId,'" + url + "investCost/contractChangeItem/changeItem.action?id='||id detailUrl from t_contract_change_item t where t."+processIdName+" is null and  t.id  in (" + stringBuffer.toString() + ") "+" ) row_ ) where rownum_ <= " + (pageNo*pageSize) + " and rownum_ > " + ((pageNo-1)*pageSize);
            List<ContractChangeItem> changeItems = jdbcTemplate.query(sql, argTypes);
            page.setResult(changeItems);
            page.setCurrentPageNo(pageNo);
            page.setCurrentPageSize(pageSize);
            page.setTotalSize(jdbcTemplate.queryForInt("select count(1) from t_contract_change_item t where t."+processIdName+" is null and t.id  in (" + stringBuffer.toString() + ") "));

        }

       return page;
	}


    @Override
	public void saveContract(Contract contract) {
		try {
			getHibernateTemplate().saveOrUpdate(contract);
		} catch (DataAccessException e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}

	@Override
	public Contract findContractById(String id) {
		return (Contract) getHibernateTemplate().get(Contract.class, id);
	}

	@Override
	public void update(Contract contract) {
		getHibernateTemplate().update(contract);
	}

	@Override
	public void deleteById(String id) {
		Contract contract = (Contract) getHibernateTemplate().load(Contract.class, id);
		if(contract!=null)
			getHibernateTemplate().delete(contract);
	}

	@Override
	public List<Contract> findByContractNoWithFuzzySearch(String contractNo) {
		String hql = "from Contract t where t.removed='0' and t.contractNo like '%"+contractNo+"%'";
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<Contract> findByContractNameWithFuzzySearch(String contractName) {
		String hql = "from Contract t where t.removed='0' and t.contractName like '%"+contractName+"%'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void deleteByIdOnLogical(String id) {
		String hql ="update Contract t set t.removed='1' where t.id='"+id+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Object[]> findCountGroupByContractType() {
		String hql ="select t.contractType,count(*) from Contract t where t.contractType like '1,%' or t.contractType like '2,%' group by t.contractType order by t.contractType ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Object[]> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList) {
		StringBuilder sb = null;
		if(contractOwnerExecuteIdList!=null && contractOwnerExecuteIdList.size()>0){
			sb = new StringBuilder();
			for(int i=0; i<contractOwnerExecuteIdList.size(); i++){
				sb.append("'").append(contractOwnerExecuteIdList.get(i)).append("',");
			}
		}
		String hql ="select t.contractOwnerExecuteId,count(*) from Contract t where t.contractOwnerExecuteId in ("+sb.deleteCharAt(sb.length()-1)+") and t.removed='0' group by t.contractOwnerExecuteId order by t.contractOwnerExecuteId ASC";
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<Object[]> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList,String contractType) {
		StringBuilder sb = null;
		if(contractOwnerExecuteIdList!=null && contractOwnerExecuteIdList.size()>0){
			sb = new StringBuilder();
			for(int i=0; i<contractOwnerExecuteIdList.size(); i++){
				sb.append("'").append(contractOwnerExecuteIdList.get(i)).append("',");
			}
		}
		String hql ="";
		if(contractType!=null && !"".equals(contractType)){
			hql ="select t.contractOwnerExecuteId,count(*) from Contract t where t.contractOwnerExecuteId in ("+sb.deleteCharAt(sb.length()-1)+") and t.contractType like '"+contractType+"%' and t.removed='0' group by t.contractOwnerExecuteId order by t.contractOwnerExecuteId ASC";
		}else{
			hql ="select t.contractOwnerExecuteId,count(*) from Contract t where t.contractOwnerExecuteId in ("+sb.deleteCharAt(sb.length()-1)+") and t.removed='0' group by t.contractOwnerExecuteId order by t.contractOwnerExecuteId ASC";
		}
		
		return getHibernateTemplate().find(hql);
	}


	@Override
	public List<Object[]> findAllByProjectIdAndQuantitiesInEstimateSubject(String projectId, String estimateSubjectId) {
		String hql="select t.id,t.contractNo,t.contractName,t.contractPrice from Contract t where t.removed='0' and t.projectId='"+projectId+"' and t.id in (select q.contractId from QuantitiesSubject q where q.investEstimateId='"+estimateSubjectId+"')";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Contract> findAllByContractIdList(List<String> idList) {
		StringBuilder contractIdFilter = null;
		if(idList!=null && idList.size()>0){
			contractIdFilter = new StringBuilder();
			for(int i =0; i<idList.size(); i++){
				contractIdFilter.append("'").append(idList.get(i)).append("',");
			}
		}else{
			return null;
		}
		String hql ="from Contract t where t.id in ("+contractIdFilter.deleteCharAt(contractIdFilter.length()-1)+") and t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Object[]> findCountByContractType(List<String> typeList,Map<String, Object> filter,List<String> addDeptIdList) {
		StringBuilder typeFilter = null;
		if(typeList!=null && typeList.size()>0){
			typeFilter = new StringBuilder();
			for(int i=0; i<typeList.size(); i++){
				typeFilter.append("'").append(typeList.get(i)).append("',");
			}
		}
		String hql ="select t.contractType,count(*) from Contract t where t.removed='0' and t.contractType in ("+typeFilter.deleteCharAt(typeFilter.length()-1)+") ";
		
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					hql += " and ";
				String paramName = i.next();
				if(paramName.equals("contractNo") || paramName.equals("contractName") || paramName.equals("projectName") 
						|| paramName.equals("projectNo") || paramName.equals("contractOwnerName") || paramName.equals("contractOwnerExecuteName") ){
					hql += "t." + paramName + " like '%" + filter.get(paramName) +"%'";
				}else if(paramName.equals("contractType")){
					//hql += "t." + paramName + " like '%" + filter.get(paramName) + "%'";
					hql = hql.substring(0,hql.length()-4);
				}else if(paramName.equals("contractSignedDate") ){
					hql += "t." + paramName + " >='" +filter.get(paramName)+"'";
				}else if(paramName.equals("contractPrice")){
					hql += " t.contractPrice is not null and to_number(t." + paramName + ") >=to_number(" + filter.get(paramName)+")";
				}else if(paramName.equals("contractSignedEndDate")){
					hql += "t.contractSignedDate"+ " <='" +filter.get(paramName)+"'";
				}else if(paramName.equals("hasQuantities")){
					if(filter.get(paramName).equals("2")){
						hql += "t.id not in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null group by q.contractId)";
					}else{
						hql += "t.id in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null group by q.contractId)";
					}
				}else{
					hql += "t." + paramName + "='" + filter.get(paramName)+"'";
				}
			}
		}
		StringBuilder sb = null;
		if(filter.get("contractOwnerExecuteId")==null){
			sb = new StringBuilder();
			if(addDeptIdList!=null && addDeptIdList.size()>0){
				for(int i=0; i<addDeptIdList.size(); i++){
					sb.append("'").append(addDeptIdList.get(i)).append("',");
				}
				if(sb!=null && sb.length()>1)
					hql += " and t.contractOwnerExecuteId in ("+sb.deleteCharAt(sb.length()-1)+")";
			}
		}
		
		hql +=" group by t.contractType  order by to_number(replace(t.contractType,',','')) ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Object[]> findCountByContractTypeWithoutExecuteId(
			List<String> typeList, Map<String, Object> filter,
			List<String> addDeptIdList) {
		StringBuilder typeFilter = null;
		if(typeList!=null && typeList.size()>0){
			typeFilter = new StringBuilder();
			for(int i=0; i<typeList.size(); i++){
				typeFilter.append("'").append(typeList.get(i)).append("',");
			}
		}
		String hql ="select t.contractType,count(*) from Contract t where t.removed='0' and t.contractType in ("+typeFilter.deleteCharAt(typeFilter.length()-1)+") ";
		
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
					hql += " and ";
				String paramName = i.next();
				if(paramName.equals("contractNo") || paramName.equals("contractName") || paramName.equals("projectName") 
						|| paramName.equals("projectNo") || paramName.equals("contractOwnerName") || paramName.equals("contractOwnerExecuteName") ){
					hql += "t." + paramName + " like '%" + filter.get(paramName) +"%'";
				}else if(paramName.equals("contractType")){
					//hql += "t." + paramName + " like '%" + filter.get(paramName) + "%'";
					hql = hql.substring(0,hql.length()-4);
				}else if(paramName.equals("contractSignedDate") ){
					hql += "t." + paramName + " >='" +filter.get(paramName)+"'";
				}else if(paramName.equals("contractPrice")){
					hql += "t.contractPrice is not null and to_number(t." + paramName + ") >=to_number(" + filter.get(paramName)+")";
				}else if(paramName.equals("hasQuantities")){
					if(filter.get(paramName).equals("2")){
						hql += "t.id not in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null group by q.contractId)";
					}else{
						hql += "t.id in (select q.contractId from QuantitiesSubject q where q.removed='0' and q.contractId is not null group by q.contractId)";
					}
				}else{
					hql += "t." + paramName + "='" + filter.get(paramName)+"'";
				}
			}
		}
		StringBuilder sb = null;
		if(filter.get("contractOwnerExecuteId")==null){
			sb = new StringBuilder();
			if(addDeptIdList!=null && addDeptIdList.size()>0){
				for(int i=0; i<addDeptIdList.size(); i++){
					sb.append("'").append(addDeptIdList.get(i)).append("',");
				}
				if(sb!=null && sb.length()>1)
					hql += " and (t.contractOwnerExecuteId not in ("+sb.deleteCharAt(sb.length()-1)+") or t.contractOwnerExecuteId is null)";
			}
		}
		
		hql +=" group by t.contractType  order by to_number(replace(t.contractType,',','')) ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public String findSumOfContractPriceByInvestEstimateId(String investEstimateId) {
		String hql ="select sum(c.contractPrice) from Contract c " +
				"where c.id in (select qs.contractId from QuantitiesSubject qs where qs.investEstimateId='"+investEstimateId+"' and qs.removed='0' group by qs.contractId)"+
				" and c.removed='0' ";
		List<String> result = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(result!=null && result.size()>0){
			return result.get(0);
		}else{
			return "0";
		}
	}

	@Override
	public void updateProjectNameByProjectId(String projectId,String projectName) {
		String hql="update Contract t set t.projectName='"+projectName+"' where t.removed='0' and t.projectId='"+projectId+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	

	@Override
	public void updateProjectNoAndProjectNameByProjectId(String projectId,
			String projectNo, String projectName) {
		String hql="update Contract t set t.projectName='"+projectName+"',t.projectNo='"+projectNo+"'  where t.removed='0' and t.projectId='"+projectId+"'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void updateBatchEdit(String[] ids, String executeName,String executeId, String type) {
		if(ids==null || "".equals(ids)) return ;
		String idFilter ="";
		for(int i=0; i<ids.length; i++){
			idFilter += "'"+ids[i]+"',";
		}
		idFilter = idFilter.substring(0,idFilter.length()-1);
		
		String hql ="update Contract t set ";
		if(executeName!=null && !"".equals(executeName) && executeId!=null && !"".equals(executeId)){
			hql +="t.contractOwnerExecuteName='"+executeName+"', t.contractOwnerExecuteId='"+executeId+"'";
			if(type!=null && !"".equals(type)){
				hql +=",t.contractType='"+type+"'";
			}
		}else{
			if(type!=null && !"".equals(type)){
				hql +="t.contractType='"+type+"'";
			}
		}
		hql += " where t.id in ("+idFilter+") and t.removed='0'";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	@Override
	public Long findContractsSum() {
		String hql="select count(*) from Contract t where t.removed='0'";
		List<Long> result = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(result==null || result.size()<1) return null;
		return result.get(0);
	}
	
	@Override
	public Long findContractsSumByType(String type) {
		String hql="select count(*) from Contract t where t.removed='0' and t.contractType like '"+type+"%'";
		List<Long> result = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(result==null || result.size()<1) return null;
		return result.get(0);
	}
	
	@Override
	public Long findContractsSumByTypeWithoutOwnerExecuteId(String type) {
		String hql="select count(*) from Contract t where t.removed='0' and t.contractType like '"+type+"%' and t.contractOwnerExecuteId is null";
		List<Long> result = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(result==null || result.size()<1) return null;
		return result.get(0);
	}
	
	@Override
	public Long findSumOfContractByTypeWithoutExecuteId(String type) {
		String hql ="select count(*) from Contract t where t.removed='0' and t.contractType like '"+type+"%' and t.contractOwnerExecuteId is null";
		List<Long> result = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(result==null || result.size()<1) return null;
		return result.get(0);
	}

	@Override
	public Long findSumOfContractByTypeWithoutExecuteId(String type,
			List<String> executeIdList) {
		String executeIdFilter = "";
		if(executeIdList!=null && executeIdList.size()>0){
			for(int i=0; i<executeIdList.size(); i++){
				executeIdFilter += "'"+executeIdList.get(i)+"',";
			}
		}
		String hql ="select count(*) from Contract t where t.removed='0' and t.contractType like '"+type+"%' " +
				"and (t.contractOwnerExecuteId is null or t.contractOwnerExecuteId not in ("+executeIdFilter.subSequence(0, executeIdFilter.length()-1)+"))";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Long> result = session.createQuery(hql).setMaxResults(1).list();
		tx.commit();
		session.close();
		if(result==null || result.size()<1) return null;
		return result.get(0);
	}

	/**
	 * 将HtXx表中的数据导入C_contract表中，用完之后删除本方法
	 * start******************************************
	 */

	@Override
	public List<HtXx> findAllHtXx() {
		String hql="from HtXx t where t.removed='0'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Contract> findAllByContractNo(String contractNo) {
		String hql ="from Contract t where t.removed='0' and t.contractNo ='"+contractNo+"'";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public HtBa findHtBaByHtXxId(long htXxId) {
		String hql="from HtBa t where t.htId="+htXxId+"";
		List<HtBa> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}

	@Override
	public void saveAll(List<Contract> contractList) {
		getHibernateTemplate().saveOrUpdateAll(contractList);
	}

	
	
	@Override
	public List<Contract> findByContractNoLowerCase(String contractNo) {
		String hql ="from Contract t where t.removed='0' and lower(trim(t.contractNo))=lower('"+contractNo.trim()+"')";
		return getHibernateTemplate().find(hql);
	}
	
	
	/**
	 * end******************************************
	 */
	
	@Override
	public List<Contract> findByContractNoWithFuzzySearch(String contractNo,String type) {
		String hql = "from Contract t where t.removed='0' and t.contractNo like '%"+contractNo+"%'";
		if("1".equals(type)){
			hql += " and t.contractType like '1,%'";
		}else if("2".equals(type)){
			hql += " and t.contractType like '2,%'";
		}
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(20);
		return query.list();
	}
	
	@Override
	public List<Contract> findByContractNameWithFuzzySearch(String contractName,String type) {
		String hql = "from Contract t where t.removed='0' and t.contractName like '%"+contractName+"%'";
		if("1".equals(type)){
			hql += " and t.contractType like '1,%'";
		}else if("2".equals(type)){
			hql += " and t.contractType like '2,%'";
		}
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(20);
		return query.list();
	}

	@Override
	public List<Contract> findByContractType(String contractType) {
		String hql = "from Contract t where t.removed='0' and t.contractType like '"+contractType+"%' order by t.contractSignedDate ASC";
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<Contract> findByFilter(Map<String, Object> filter) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		String hql = "from Contract t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0 ) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("selfNo") || paramName.equals("contractNo") || paramName.equals("contractOwnerExecuteName") || paramName.equals("contractType") || paramName.equals("contractName")) {
					filterPart += "t." + paramName + " like '%" + filter.get(paramName)+"%'";
				}else if(paramName.equals("contractSignedDate") ){
					filterPart += "t." + paramName + " >='" + filter.get(paramName)+"'";
				}else if(paramName.equals("contractSignedEndDate")){
					filterPart += "t.contractSignedDate" + " <='" + filter.get(paramName)+"'";
				}else if(paramName.equals("contractPrice")){
					filterPart += "t.contractPrice is not null and to_number(t." + paramName + ") >=to_number(" + filter.get(paramName)+")";
				}
                else if(paramName.equals("id")){
                    filterPart += "t.id ='"+filter.get(paramName)+"'";
                }

                else{
					filterPart += "t." + paramName + "=" + filter.get(paramName);
				}
				filterCounter++;
			}
		}
		
		filterPart += " order by t.contractSignedDate DESC,t.createDate DESC";
		
		return getHibernateTemplate().find(hql+filterPart);
	}

	@Override
	public String executeSQLReturnOneData(String sql) {
		List<Object> result = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		if(result==null || result.size()<1 || result.get(0)==null) return "0";
		return result.get(0).toString();
	}

	@Override
	public List<Contract> findContractBySelfNoWithFuzzySearch(String selfNo) {
		String hql ="from Contract t where t.selfNo like '%"+selfNo+"%' and t.removed=0 order by t.contractSignedDate desc,t.createDate desc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Contract> findBySelfNoIgnoreCase(String selfNo) {
		
		String hql ="from Contract t where t.removed='0' and lower(trim(t.selfNo))=lower('"+selfNo.trim()+"')";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<CompanyRoute> findCompanyByNameAndType(String name,String type) {
		String hql ="from CompanyRoute t where t.removed='0' and t.objectName like '%"+name+"%' and t.type='"+type+"' order by t.order ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<CompanyRoute> findByPidAndType(String id, String type) {
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(type)) return null;
		String hql ="from CompanyRoute t where t.removed='0' and t.pid='"+id+"' and t.type='"+type+"' order by t.order ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<CompanyRoute> findAllLine() {
		String hql ="from CompanyRoute t where t.removed='0' and t.type='2' and t.pid='01' order by t.order ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Contract> findContractByHQL(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<Object[]> findContractBySQL(String sql) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		return query.list();
	}

	@Override
	public List<Contract> findAllContractByUpdateDate(String startDate,
			String endDate) {
		String hql="from Contract t where t.removed='0' and t.updateDate>? and t.updateDate<?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, startDate).setString(1, endDate);
		return query.list();
	}

	@Override
	public void saveKpiClear(KpiClear kpiClear) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().save(kpiClear);
	}
	
	@Override
	public void saveKpiClearStatus(KpiClearStatus kpiClearStatus) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().save(kpiClearStatus);
	}
	
	@Override
	public void updateKpiClearStatus(KpiClearStatus kpiClearStatus) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().update(kpiClearStatus);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findKpiContractByType(String type, Map<String, Object> filter) {
		
		if (filter == null)
			filter = new HashMap<String, Object>();
		String hql = "select t.id,t.contractNo,t.contractName,t.contractOwnerExecuteName, " 
			+"t.contractGrouping,k.opinion,k.kpiClearId,t.contractSignedDate,t.contractStartDate "	
			+"from KpiClear k right outer join k.contractId t right outer join k.contractId t  ";
		String filterPart = "";
		if("0".equals(type) || "23".equals(type)){
			hql += "join t.kpiClearStatus s";
		}
		if("1".equals(type)){
			hql += "left outer join t.kpiClearStatus s";
		}
		
		if("0".equals(type)){
			filterPart += " where k.createTime = ( ";
			filterPart += "		select max(createTime) ";
			filterPart += "		from KpiClear ";
			filterPart += "		where contractId=t.id) ";
			filterPart += " and s.kpiStatus='" + type + "' ";
		}
		if("1".equals(type)){
			filterPart += " where (k.createTime = ( ";
			filterPart += "		select max(createTime) ";
			filterPart += "		from KpiClear ";
			filterPart += "		where contractId=t.id) ";
			filterPart += " or k.createTime is null) ";
			if(filter.get("kpiStatus")!=null && ("0".equals(filter.get("kpiStatus")))||"3".equals(filter.get("kpiStatus"))){
				filterPart += " and s.kpiStatus='"+filter.get("kpiStatus")+"' ";
			}else{
				filterPart += " and (s.kpiStatus!='3' and s.kpiStatus!='0' or s.kpiStatus is null) ";
			}
		}
		if("23".equals(type)){
			filterPart += " where k.createTime = ( ";
			filterPart += "		select max(createTime) ";
			filterPart += "		from KpiClear ";
			filterPart += "		where contractId=t.id) ";
			if(filter.get("kpiStatus")!=null && ("0".equals(filter.get("kpiStatus")))||"3".equals(filter.get("kpiStatus"))){
				filterPart += " and s.kpiStatus='"+filter.get("kpiStatus")+"' ";
			}else{
				filterPart += " and (s.kpiStatus='2' or s.kpiStatus='3') ";
			}
			
			/**
			int count = 0;
			String filterExtra = "";
			List<Map<String, Object>> groupList = (List<Map<String, Object>>) filter.get("mulFilter");
			for(Map<String, Object> map : groupList){
				count ++;
				if(count == groupList.size()){
					filterPart += " and ((t.contractOwnerExecuteName like '%"+map.get("ownerExecute")+"%' " 
					+ " and t.contractGrouping='"+map.get("group")+"') "+filterExtra+")";
				}else{
					filterExtra += " or (t.contractOwnerExecuteName like '%"+map.get("ownerExecute")+"%' " 
					+ " and t.contractGrouping='"+map.get("group")+"') ";
				}
			}
			*/
		}
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter >= 0 ) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("selfNo") || paramName.equals("contractNo") || paramName.equals("contractOwnerExecuteName") || paramName.equals("contractType") || paramName.equals("contractName")) {
					filterPart += "t." + paramName + " like '%" + filter.get(paramName)+"%'";
				}else if(paramName.equals("contractSignedDate") ){
					filterPart += "t." + paramName + " >='" + filter.get(paramName)+"'";
				}else if(paramName.equals("contractSignedEndDate")){
					filterPart += "t.contractSignedDate" + " <='" + filter.get(paramName)+"'";
				}else if(paramName.equals("contractPrice")){
					filterPart += "t.contractPrice is not null and to_number(t." + paramName + ") >=to_number(" + filter.get(paramName)+")";
				}else if(paramName.equals("ownerExecute")){
					filterPart += " 1=1 ";
				}else if(paramName.equals("contractGrouping")){
					if("-1".equals(filter.get("contractGrouping"))){
						filterPart += " 1=1 ";
					}else if("3".equals(filter.get("contractGrouping"))){
						filterPart += "t.contractGrouping is null";
					}else{
						filterPart += "t.contractGrouping='" + filter.get("contractGrouping")+"'";
					}
				}else if(paramName.equals("mulFilter")){
					filterPart += " 1=1 ";
				}else if(paramName.equals("clearBeginDate")){
					filterPart += " s.updateTime >= to_date('" + filter.get("clearBeginDate")+"','yyyy-MM-dd') ";
				}else if(paramName.equals("clearEndDate")){
					filterPart += "s.updateTime <= to_date('" + filter.get("clearEndDate")+"','yyyy-MM-dd') ";
				}else if(paramName.equals("kpiStatus")){
					filterPart += " 1=1 ";
				}else if(paramName.equals("queryFilter")){
					filterPart += filter.get("queryFilter");
				}else{
					filterPart += "t." + paramName + "=" + filter.get(paramName);
				}
				filterCounter++;
			}
		}
		
		if("1".equals(type)){
			filterPart += " order by nvl(k.createTime, to_date('1990/01/01','yyyy/MM/dd'))  desc,t.contractSignedDate DESC,t.createDate DESC ";
		}else if("23".equals(type)){
			filterPart += " order by nvl(k.createTime, to_date('1990/01/01','yyyy/MM/dd'))  desc,nvl(k.opinion, '')  ,t.contractSignedDate DESC,t.createDate DESC";
		}else if("0".equals(type)){
			filterPart += " order by nvl(s.updateTime, to_date('1990/01/01','yyyy/MM/dd'))  desc,t.contractSignedDate DESC,t.createDate DESC ";
		}else{
			filterPart += " order by t.contractSignedDate DESC,t.createDate DESC";
		}
		
		return getHibernateTemplate().find(hql+filterPart);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KpiClear> findKpiClearByContractId(String id) {
		String hql = "from KpiClear t where t.contractId=? order by createTime desc";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id);
		return query.list();
	}
	
	public List<KpiClearStatus> findKpiClearStatusByContractId(String id){
		String hql = "from KpiClearStatus t where t.contractId=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id);
		List<KpiClearStatus> list = query.list();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findUserGroupByName(String name){
		String sql = "select contract_owner_execute_name, contract_grouping from C_USER_GROUP where user_id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, name);
		return query.list();
	}
	
	@Override
	public String findQueryFilterByName(String name){
		String sql = "select query_filter from C_USER_GROUP where user_id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, name);
		List<String> list = query.list();
		if(list!=null && list.size()>0){
			return (String) list.get(0);
		}
		return null;
	}
	
	public void sendMessage(String receiver,String content,String sendLoginName,String sendUserName){
		String insertSql = "insert into T_SHORTMESSAGE_TASK (ID,RECEIVERS_MSG, CONTENT, SEND_USER_LOGINNAME, SEND_USER_NAME,SEND_TYPE,TASK_STATUS,REMOVED,PLAN_SEND_TIME,OPERATE_TIME) "+
			" values(?, ?, ?, ?, ?, ?, ?, ?, sysdate, sysdate)";
		UUID uuid = UUID.randomUUID();
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(insertSql);
		query.setString(0, uuid.toString().substring(0, 32));
		query.setString(1, receiver);
		query.setString(2, content);
		query.setString(3, sendLoginName);
		query.setString(4, sendUserName);
		query.setString(5, "1");
		query.setString(6, "1");
		query.setString(7, "0");
		query.executeUpdate();
	}

	@Override
	public String findNameByGroup(String executeName, String contractGroup) {
		String sql = "select user_id from C_USER_GROUP where contract_owner_execute_name=? and contract_grouping=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, executeName);
		query.setString(1, contractGroup);
		List<String> list = query.list();
		if(list!=null && list.size()>0){
			return (String) list.get(0);
		}
		return null;
	}

	@Override
	public List<Object[]> getKpiClearCount() {
		String sql = "select s.kpi_type kpiType, count(1) count from c_contract c, c_kpi_clear_status s "
			+" where c.id=s.contract_id and s.kpi_status='0' and c.contract_type like '2,%' group by s.kpi_type ";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		if(list!=null){
			return list;
		}
		return null;
	}
	
	@Override
	public List<Object[]> findKpiByContractId(String contractId){
		String sql = "select kpi_type,kpi_status from C_KPI_CLEAR_STATUS where contract_id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, contractId);
		List<Object[]> list = query.list();
		if(list!=null){
			return list;
		}
	 	return null;
	}
	
	@Override
	public List<String> findUserGroup(){
		String sql = "select query_filter from C_USER_GROUP";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		List<String> list = query.list();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	@Override
	public boolean isLoginNameInGroup(String filter, String contractId){
		String sql = "select count(1) from C_CONTRACT where "+filter+" and id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, contractId);
		BigDecimal count = (BigDecimal) query.uniqueResult();
		if(count.intValue()>0){
			return true;
		}
		return false;
		
	}
	
	@Override
	public List<String> findNameByQueryFilter(String filter){
		String sql = "select user_id from C_USER_GROUP where query_filter=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setString(0, filter);
		List<String> list = query.list();
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
