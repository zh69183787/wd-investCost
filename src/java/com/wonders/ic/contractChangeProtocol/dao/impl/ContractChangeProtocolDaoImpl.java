package com.wonders.ic.contractChangeProtocol.dao.impl;

import java.io.IOException;
import java.util.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contractChangeProtocol.dao.ContractChangeProtocolDao;
import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class ContractChangeProtocolDaoImpl extends AbstractHibernateDaoImpl<Contract> implements ContractChangeProtocolDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveContractChangeProtocol(ContractChangeProtocol contractChangeProtocol) {
        try {
            getHibernateTemplate().saveOrUpdate(contractChangeProtocol);
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
        ContractChangeProtocol contractChangeProtocol = (ContractChangeProtocol) getHibernateTemplate().load(ContractChangeProtocol.class, id);
        if (contractChangeProtocol != null)
            getHibernateTemplate().delete(contractChangeProtocol);
    }

    @Override
    public Page findItemByPage(Map<String, Object> filter, int pageNo,
                               int pageSize) {
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        args.add(new HqlParameter("removed", "0"));
        StringBuffer hql = new StringBuffer("select t from ContractChangeProtocol t left join t.contract c left join fetch t.contractChangeItemList where t.removed = :removed");
        StringBuffer countHql = new StringBuffer("select count(t.id) from ContractChangeProtocol t left join t.contract c where t.removed = :removed");
        if (!filter.isEmpty()) {
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext(); ) {
                String paramName = i.next();
                hql.append(" and t." + paramName + " like :" + paramName.replaceAll("\\.", "") + " ");
                countHql.append(" and t." + paramName + " like :" + paramName.replaceAll("\\.", "") + " ");
                args.add(new HqlParameter(paramName.replaceAll("\\.", ""), "%" + filter.get(paramName) + "%"));
            }
        }
        hql.append(" order by t.createTime");

        return findByHQLWithPage(hql.toString(), args, pageNo, pageSize, countHql.toString());
    }

    @Override
    public void deleteOnLogical(String id) {
        String hql = "update ContractChangeProtocol t set t.removed='1' where t.id='" + id + "'";
        getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
    }

    @Override
    public ContractChangeProtocol findById(String id) {
        // TODO Auto-generated method stub
        return (ContractChangeProtocol) getHibernateTemplate().get(ContractChangeProtocol.class, id);

    }

    @Override
    public Map count(Map<String, Object> filter) {
        String hql = "select nvl(count(t.id),0),nvl(sum(t.changePrice),0) from ContractChangeProtocol t left join t.contract c where t.removed=:removed ";
        if (filter.get("contractId") != null) {
            hql += " and t.contract.id = :contractId";
        }
        if (filter.get("contractNo") != null) {
            hql += " and c.contractNo = :contractNo";
        }
        if (filter.get("selfNo") != null) {
            hql += " and c.selfNo = :selfNo";
        }
        filter.put("removed", "0");
        Object[] obj = (Object[]) getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setProperties(filter).uniqueResult();

        Map result = new HashMap();
        result.put("protocolTotalNum", obj[0]);
        result.put("protocolTotalPrice", obj[1]);
        return result;
    }

    public void syncInsertData(Date startDate, Date endDate) {
        String dbUser = "";
        HashMap parameter = new HashMap();
        try {
            Resource resource = new ClassPathResource("/config.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            dbUser = props.getProperty("dbUser") + ".";
        } catch (IOException e) {
            e.printStackTrace();
        }
        String whereSql = "";
        if (startDate != null) {
            whereSql += " AND TO_DATE(PASS_TIME,'yyyy-mm-dd') >= :startDate";
            parameter.put("startDate", startDate);
        }
        if (endDate != null) {
            whereSql += " AND TO_DATE(PASS_TIME,'yyyy-mm-dd') <= :endDate";
            parameter.put("endDate", endDate);
        }
        if (startDate == null && endDate == null) {
            whereSql += " AND TO_DATE(PASS_TIME,'yyyy-mm-dd') = :passTime";
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            parameter.put("passTime", calendar);
        }
        String sql = "INSERT INTO " + dbUser + "C_CONTRACT_CHANGE_PROTOCOL C " +
                "  (ID, " +
                "   ORIGINAL_CONTRACT_NAME, " +
                "   CHANGE_PRICE, " +
                "   CHANGE_ITEM_TYPE, " +
                "   DEAL_PERSON, " +
                "   REG_PERSON, " +
                "   SIGN_TIME, " +
                "   DEAL_DEPT_SUGGEST, " +
                "   MEMO, " +
                "   CONTRACT_NAME, " +
                "   CONTRACT_NO, " +
                "   CONTRACT_SELF_NUM, " +
                "   CONTRACT_PRICE, " +
                "   OPPOSITE_COMPANY, " +
                "   EXEC_PERIOD_START, " +
                "   REG_LOGIN_NAME, " +
                "   REG_TIME, " +
                "   PASS_TIME, " +
                "   EXEC_PERIOD_END, " +
                "   ATTACH, " +
                "   CREATE_TIME, " +
                "   UPDATE_TIME, " +
                "   REMOVED, " +
                "   DEPT, " +
                "   DEPT_ID, " +
                "   CONTRACT_ID, " +
                "   CORPORATION_ID) " +
                "  SELECT ID, " +
                "         ORIGINAL_CONTRACT_NAME, " +
                "         CHANGE_PRICE, " +
                "         CHANGE_ITEM_TYPE, " +
                "         DEAL_PERSON, " +
                "         REG_PERSON, " +
                "         TO_DATE(SIGN_TIME,'yyyy-mm-dd'), " +
                "         DEAL_DEPT_SUGGEST, " +
                "         MEMO, " +
                "         CONTRACT_NAME, " +
                "         CONTRACT_NO, " +
                "         SELF_NO, " +
                "         CONTRACT_PRICE, " +
                "         OPPOSITE_COMPANY, " +
                "         TO_DATE(EXEC_PERIOD_START,'yyyy-mm-dd'), " +
                "         REG_LOGIN_NAME, " +
                "         TO_DATE(REG_TIME,'yyyy-mm-dd hh24:mi:ss'), " +
                "         TO_DATE(PASS_TIME,'yyyy-mm-dd hh24:mi:ss'), " +
                "         TO_DATE(EXEC_PERIOD_END,'yyyy-mm-dd'), " +
                "         ATTACH, " +
                "         SYSDATE, " +
                "         SYSDATE, " +
                "         REMOVED, " +
                "         DEPT, " +
                "         DEPT_ID, " +
                "         CONTRACT_ID, " +
                "         CORPORATION_ID " +
                "    FROM STPT.T_CONTRACT_CHANGE_PROTOCOL T " +
                "   WHERE NOT EXISTS " +
                "   (SELECT 1 FROM " + dbUser + "C_CONTRACT_CHANGE_PROTOCOL WHERE ID = T.ID) AND FLAG='1' AND REMOVED='0'  "+whereSql;


        getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setProperties(parameter).executeUpdate();
    }

    @Override
    public void syncUpdateData(Date startDate, Date endDate) {
        String dbUser = "";
        HashMap parameter = new HashMap();
        try {
            Resource resource = new ClassPathResource("/config.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            dbUser = props.getProperty("dbUser") + ".";
        } catch (IOException e) {
            e.printStackTrace();
        }

        String whereSql = "";
        if (startDate != null) {
            whereSql += " AND TO_DATE(PASS_TIME,'yyyy-mm-dd') >= :startDate";
            parameter.put("startDate", startDate);
        }
        if (endDate != null) {
            whereSql += " AND TO_DATE(PASS_TIME,'yyyy-mm-dd') <= :endDate";
            parameter.put("endDate", endDate);
        }
        if (startDate == null && endDate == null) {
            whereSql += " AND TO_DATE(PASS_TIME,'yyyy-mm-dd') = :passTime";
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            parameter.put("passTime", calendar);
        }

        String sql = "UPDATE " + dbUser + "C_CONTRACT_CHANGE_PROTOCOL C " +
                "   SET ( " +
                "        ORIGINAL_CONTRACT_NAME, " +
                "        CHANGE_PRICE, " +
                "        CHANGE_ITEM_TYPE, " +
                "        DEAL_PERSON, " +
                "        REG_PERSON, " +
                "        SIGN_TIME, " +
                "        DEAL_DEPT_SUGGEST, " +
                "        MEMO, " +
                "        CONTRACT_NAME, " +
                "        CONTRACT_NO, " +
                "        CONTRACT_SELF_NUM, " +
                "        CONTRACT_PRICE, " +
                "        OPPOSITE_COMPANY, " +
                "        EXEC_PERIOD_START, " +
                "        REG_LOGIN_NAME, " +
                "        REG_TIME, " +
                "        PASS_TIME, " +
                "        EXEC_PERIOD_END, " +
                "        ATTACH, " +
                "        CREATE_TIME, " +
                "        UPDATE_TIME, " +
                "        REMOVED, " +
                "        DEPT, " +
                "        DEPT_ID, " +
                "        CONTRACT_ID, " +
                "        CORPORATION_ID) = " +
                "       (SELECT  " +
                "               ORIGINAL_CONTRACT_NAME, " +
                "               CHANGE_PRICE, " +
                "               CHANGE_ITEM_TYPE, " +
                "               DEAL_PERSON, " +
                "               REG_PERSON, " +
                "               TO_DATE(SIGN_TIME, 'yyyy-mm-dd'), " +
                "               DEAL_DEPT_SUGGEST, " +
                "               MEMO, " +
                "               CONTRACT_NAME, " +
                "               CONTRACT_NO, " +
                "               SELF_NO, " +
                "               CONTRACT_PRICE, " +
                "               OPPOSITE_COMPANY, " +
                "               TO_DATE(EXEC_PERIOD_START, 'yyyy-mm-dd'), " +
                "               REG_LOGIN_NAME, " +
                "               TO_DATE(REG_TIME, 'yyyy-mm-dd hh24:mi:ss'), " +
                "               TO_DATE(PASS_TIME, 'yyyy-mm-dd hh24:mi:ss'), " +
                "               TO_DATE(EXEC_PERIOD_END, 'yyyy-mm-dd'), " +
                "               ATTACH, " +
                "               SYSDATE, " +
                "               SYSDATE, " +
                "               REMOVED, " +
                "               DEPT, " +
                "               DEPT_ID, " +
                "               CONTRACT_ID, " +
                "               CORPORATION_ID " +
                "          FROM STPT.T_CONTRACT_CHANGE_PROTOCOL T " +
                "         WHERE  C.ID = T.ID " +
                "           AND FLAG = '1' " +
                "           AND REMOVED = '0' " + whereSql +
                " ) WHERE  EXISTS (SELECT 1 " +
                "            FROM STPT.T_CONTRACT_CHANGE_PROTOCOL " +
                "           WHERE ID = C.ID " + whereSql + " AND FLAG = '1' AND REMOVED = '0')";

        getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).setProperties(parameter).executeUpdate();
    }

}
