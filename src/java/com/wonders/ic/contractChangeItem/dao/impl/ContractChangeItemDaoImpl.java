package com.wonders.ic.contractChangeItem.dao.impl;

import java.util.*;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractChangeItem.dao.ContractChangeItemDao;
import com.wonders.ic.contractChangeItem.entity.bo.ContractChangeItem;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class ContractChangeItemDaoImpl extends AbstractHibernateDaoImpl<Contract>implements ContractChangeItemDao{

	private JdbcTemplate jdbcTemplate;  
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public void saveContractChangeItem(ContractChangeItem contractChangeItem) {
		try {
			getHibernateTemplate().saveOrUpdate(contractChangeItem);
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
		ContractChangeItem contractChangeItem = (ContractChangeItem) getHibernateTemplate().load(ContractChangeItem.class, id);
		if(contractChangeItem!=null)
			getHibernateTemplate().delete(contractChangeItem);
	}
	
	@Override
	public Page findItemByPage(Map<String, Object> filter, int pageNo,
			int pageSize) {
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        args.add(new HqlParameter("removed", "0"));
        StringBuffer hql = new StringBuffer("select t from ContractChangeItem t left join t.contract c where t.removed = :removed");
        StringBuffer countHql = new StringBuffer("select count(t.id) from ContractChangeItem t left join t.contract c where t.removed = :removed");
        if (!filter.isEmpty()) {
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext(); ) {
                String paramName = i.next();
                if(filter.get(paramName) != null && !paramName.startsWith("_")) {
                    hql.append(" and t." + paramName + " like :" + paramName.replaceAll("\\.", "") + " ");
                    countHql.append(" and t." + paramName + " like :" + paramName.replaceAll("\\.", "") + " ");
                    args.add(new HqlParameter(paramName.replaceAll("\\.", ""), "%" + filter.get(paramName) + "%"));
                }
            }

            if(filter.containsKey("_notSelected")&&"1".equals(filter.get("_notSelected"))){
                hql.append(" and (t.contractChangeProtocolId is null or t.contractChangeProtocolId = :contractChangeProtocolId) ");
                countHql.append(" and (t.contractChangeProtocolId is null  or t.contractChangeProtocolId = :contractChangeProtocolId) ");
                args.add(new HqlParameter("contractChangeProtocolId",  filter.get("contractChangeProtocolId")));
            }
        }
        hql.append(" order by t.createTime");

        return findByHQLWithPage(hql.toString(), args, pageNo, pageSize,countHql.toString());
	}



	@Override
	public void deleteOnLogical(String id) {
		String hql ="update ContractChangeItem t set t.removed=:removed where t.id=:id";
		getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("id",id).setString("removed","1").executeUpdate();
	}

	@Override
	public ContractChangeItem findById(String id) {
		// TODO Auto-generated method stub
		return (ContractChangeItem) getHibernateTemplate().get(ContractChangeItem.class, id);
	}
	
	@Override
	public void update(ContractChangeItem contractChangeItem) {
		getHibernateTemplate().update(contractChangeItem);
	}

    @Override
    public Integer getMaxNo(String contractId) {
        String hql ="select max(nvl(t.contractSq,0)+1) from ContractChangeItem t where t.contract.id = :contractId and t.removed=:removed";
        Integer result =  (Integer)getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString("contractId",contractId).setString("removed","0").uniqueResult();
        return result == null ?1:result;
    }

    @Override
    public Map countNum(Map<String, Object> filter) {
        String hql ="select count(t.contract.id) from ContractChangeItem t left join t.contract c where t.removed=:removed ";
        if(filter.get("contractId")!=null){
            hql += " and t.contract.id = :contractId";
        }
        if(filter.get("contractNo")!=null){
            hql += " and c.contractNo = :contractNo";
        }
        if(filter.get("selfNo")!=null){
            hql += " and c.selfNo = :selfNo";
        }
        filter.put("removed","0");
        Object i = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setProperties(filter).uniqueResult();

        Map result = new HashMap();
        result.put("changeItemTotalNum",i);
        return result;
    }

    @Override
    public void syncUpdate(Date startDate,Date endDate) {

    }

    @Override
    public void syncInsert(Date startDate,Date endDate) {

    }
}
