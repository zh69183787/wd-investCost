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

package com.wonders.ic.project.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.api.dto.ContractDto;
import com.wonders.api.dto.ProjectDto;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.project.dao.ProjectDao;
import com.wonders.ic.project.entity.bo.Project;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * Projectʵ�嶨��
 *
 * @author ycl
 * @author modify by $Author$
 * @version $Revision$
 * @date 2012-10-9
 * @since 1.0
 */

public class ProjectDaoImpl extends AbstractHibernateDaoImpl<Project> implements
        ProjectDao {
    @Override
    public Page findProject(Project project, int pageNo, int pageSize) {
        StringBuffer sql = new StringBuffer("SELECT *" +
                "  FROM (SELECT P.id," +
                "               P.project_No," +
                "               P.project_Name," +
                "               P.project_Type," +
                "               P.dispatch_No," +
                "               P.project_Adddept," +
                "               P.project_Addperson," +
                "               P.project_Budget_All," +
                "               P.project_Moneysource," +
                "               P.primary_Design_Budget," +
                "               (SELECT SUM(DECODE(NVL(NATION_VERIFY_PRICE,0),0,VERIFY_PRICE,NATION_VERIFY_PRICE)) FROM C_CONTRACT WHERE P.ID=PROJECT_ID AND REMOVED='0') settlementPrice," +
                "               P.project_state," +
                "               P.pay_type" +
                "          FROM C_PROJECT P" +
                "         WHERE P.PROJECT_TYPE IN ('2', '3', '4')" +
                "           AND P.REMOVED = '0'" +
                "           AND EXISTS( " +
                "						SELECT 1 from C_CONTRACT WHERE P.ID = PROJECT_ID AND REMOVED = 0)" +
                "           AND NOT EXISTS(" +
                " 						SELECT 1 from C_CONTRACT WHERE P.ID = PROJECT_ID AND (CONTRACT_STATUS != '3' OR CONTRACT_STATUS is null) AND REMOVED = 0" +
                "						) " +
                "			AND P.project_state != '3'" +
                "		order by P.dispatch_No) T" +
                " WHERE 1=1");

        HashMap map = new HashMap();
        if (StringUtils.isNotBlank(project.getDispatchNo())) {
            sql.append(" AND T.DISPATCH_NO like :dispatchNo");
            map.put("dispatchNo","%"+project.getDispatchNo().trim()+"%");
        }

        if (StringUtils.isNotBlank(project.getProjectName())) {
            sql.append(" AND T.project_Name like :projectName");
            map.put("projectName", "%" + project.getProjectName().trim() + "%");

        }
        
        if (StringUtils.isNotBlank(project.getProjectState())) {
            sql.append(" AND T.project_state like :projectState");
            map.put("projectState", "%" + project.getProjectState().trim() + "%");

        }

        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(map).setFirstResult((pageNo - 1)*pageSize).setMaxResults(pageSize).list();
        BigDecimal num = (BigDecimal)getSession().createSQLQuery(sql.toString().replace("*","count(1)")).setProperties(map).uniqueResult();
       List<ProjectDto> list = new ArrayList<ProjectDto>();
        for (Object[] o : result) {
            ProjectDto dto = new ProjectDto();
            dto.setId((String) o[0]);
            dto.setProjectNo((String) o[1]);
            dto.setProjectName((String) o[2]);
            dto.setProjectType((String) o[3]);
            dto.setDispatchNo((String) o[4]);
            dto.setProjectAdddept((String) o[5]);
            dto.setProjectAddperson((String) o[6]);
            dto.setProjectBudgetAll((String) o[7]);
            dto.setProjectMoneysource((String) o[8]);
            dto.setPrimaryDesignBudget((String) o[9]);
            if(o[10] != null)
            dto.setSettlementPrice(String.valueOf(((BigDecimal) o[10]).doubleValue()));
            dto.setPayType((String) o[12]);
            list.add(dto);
        }

        Page page = new Page((pageNo - 1), pageSize, num.intValue(), pageSize, list);
        return page;
    }

    public Page findProjectByPage(Map<String, Object> filter, int pageNo, int pageSize, List<String> addDeptIdList) {
        if (filter == null)
            filter = new HashMap<String, Object>();
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        String hql = "select t from Project t ";
        String countHql = "select count(*) from Project t ";
        String filterPart = "";
        int filterCounter = 0;
        if (!filter.isEmpty()) {
            filterPart += " where ";
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext(); ) {
                String paramName = i.next();
                if (filterCounter > 0 && !paramName.equals("projectType") && !paramName.equals("projectAdddeptId")) {
                    filterPart += " and ";
                }

                if (paramName.equals("projectMoneysource") || paramName.equals("approvalDate")) {
                    if (filter.get(paramName).equals("全网络")) {
                        filterPart += " (t.projectMoneysource like '%\"lineName\":\"none\"%' or t.projectMoneysource like '%\"lineName\":\"\"%')";
                    } else {
                        filterPart += "t." + paramName + " like :" + paramName;
                        args.add(new HqlParameter(paramName, "%" + filter.get(paramName) + "%"));
                    }
                } else if (paramName.equals("projectNo") || paramName.equals("projectAdddept") || paramName.equals("dispatchNo")) {
                    filterPart += "t." + paramName + " like :" + paramName;
                    args.add(new HqlParameter(paramName, "%" + filter.get(paramName) + "%"));
                } else if (paramName.equals("projectType")) {
                    if (!filter.get(paramName).equals("0")) {
                        filterPart += " and ";
                        filterPart += "t." + paramName + " = :" + paramName;
                        args.add(new HqlParameter(paramName, filter.get(paramName)));
                    }
                } else if (paramName.equals("projectStarttimePlanDateEnd")) {
                    filterPart += "t.projectStarttimePlanDate" + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectStarttimePlanDate")) {
                    filterPart += "t." + paramName + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectEndtimePlanDateStart")) {
                    filterPart += "t.projectEndtimePlanDate" + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectEndtimePlanDate")) {
                    filterPart += "t." + paramName + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("approvalDate")) {
                    filterPart += "t." + paramName + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("approvalDateEnd")) {
                    filterPart += "t.approvalDate" + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectAdddeptId")) {
                    filterCounter--;
                } else if (paramName.equals("current")) {
                    filterPart += "t.approvalDate <='" + filter.get("current") + "'";
                } else if (paramName.equals("projectName")) {
                    String contractName = filter.get("projectName").toString();
                    if (contractName.indexOf(" ") > 0) {            //回车符号
                        String[] nameArray = contractName.split("\\s");
                        if (nameArray != null && nameArray.length > 0) {
                            for (int m = 0; m < nameArray.length; m++) {
                                if (m != nameArray.length - 1) {
                                    filterPart += "t.projectName like '%" + nameArray[m] + "%' and ";
                                } else {
                                    filterPart += "t.projectName like '%" + nameArray[m] + "%'";
                                }
                            }
                        }
                    } else {
                        filterPart += "t." + paramName + " like :" + paramName;
                        args.add(new HqlParameter(paramName, "%" + filter.get(paramName) + "%"));
                    }
                } else if ("complemented".equals(paramName)) {
                    Object complemented = filter.get("complemented");
                    if (complemented != null && "yes".equals(complemented)) {
                        filterPart += " t.projectType in (2,3,4) and t.removed=0 and " +
                                "(t.dispatchNo is null or  t.approvalDate is null  " +
                                " or  t.projectMoneysource is null " +
                                " or t.moneySourceType  is null or  t.professionalType is null )";
                    } else {
                        filterPart = StringUtils.stripEnd(filterPart, "and");
                    }
                } else {
                    filterPart += "t." + paramName + "=:" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }
                filterCounter++;
            }
        }
        if (filter.get("projectType") != null && filter.get("projectType").equals("0")) {
            filterPart += " and t.projectType in ('2','3','4')";
        }
        StringBuilder addDeptIdListFilter = null;
        if (filter.get("projectAdddeptId") == null) {
            addDeptIdListFilter = new StringBuilder();
            if (addDeptIdList != null && addDeptIdList.size() > 0) {
                for (int i = 0; i < addDeptIdList.size(); i++) {
                    addDeptIdListFilter.append("'").append(addDeptIdList.get(i)).append("',");
                }
            }
            if (addDeptIdListFilter != null && addDeptIdListFilter.length() > 1)
                filterPart += " and t.projectAdddeptId in (" + addDeptIdListFilter.deleteCharAt(addDeptIdListFilter.length() - 1) + ")";
        } else if (filter.get("projectAdddeptId").equals("-1")) {
            filterPart += " and t.projectAdddeptId is null or t.projectAdddeptId=''";
        } else if (filter.get("projectAdddeptId").equals("other")) {
            filterPart += " and t.projectAdddeptId not in ('2541','2542','2543','2544','2540','2718','2719','2720','2721','2722','2545','2546')";
        } else {
            filterPart += " and t.projectAdddeptId ='" + filter.get("projectAdddeptId") + "'";
        }
        filterPart += " order by t.createDate DESC,t.id DESC";
        return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
    }

    @Override
    public Page findProjectByPageWithoutAdddeptList(Map<String, Object> filter, int pageNo, int pageSize, List<String> addDeptIdList) {
        if (filter == null)
            filter = new HashMap<String, Object>();
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        String hql = "select t from Project t ";
        String countHql = "select count(*) from Project t ";
        String filterPart = "";
        int filterCounter = 0;
        if (!filter.isEmpty()) {
            filterPart += " where ";
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext(); ) {
                String paramName = i.next();
                if (filterCounter > 0 && !paramName.equals("projectType") && !paramName.equals("projectAdddeptId")) {
                    filterPart += " and ";
                }

                if (paramName.equals("projectNo") || paramName.equals("projectAdddept") || paramName.equals("projectMoneysource") || paramName.equals("approvalDate")) {
                    filterPart += "t." + paramName + " like :" + paramName;
                    args.add(new HqlParameter(paramName, "%" + filter.get(paramName) + "%"));
                } else if (paramName.equals("projectType")) {
                    if (!filter.get(paramName).equals("0")) {
                        filterPart += " and ";
                        filterPart += "t." + paramName + " = :" + paramName;
                        args.add(new HqlParameter(paramName, filter.get(paramName)));
                    }
                } else if (paramName.equals("projectStarttimePlanDateEnd")) {
                    filterPart += "t.projectStarttimePlanDate" + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectStarttimePlanDate")) {
                    filterPart += "t." + paramName + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectEndtimePlanDateStart")) {
                    filterPart += "t.projectEndtimePlanDate" + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectEndtimePlanDate")) {
                    filterPart += "t." + paramName + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("approvalDate")) {
                    filterPart += "t." + paramName + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("approvalDateEnd")) {
                    filterPart += "t.approvalDate" + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectAdddeptId")) {
                    filterCounter--;
                } else if (paramName.equals("current")) {
                    filterPart += "t.approvalDate <='" + filter.get("current") + "'";
                } else if (paramName.equals("projectName")) {
                    String contractName = filter.get("projectName").toString();
                    if (contractName.indexOf(" ") > 0) {            //回车符号
                        String[] nameArray = contractName.split("\\s");
                        if (nameArray != null && nameArray.length > 0) {
                            for (int m = 0; m < nameArray.length; m++) {
                                if (m != nameArray.length - 1) {
                                    filterPart += "t.projectName like '%" + nameArray[m] + "%' and ";
                                } else {
                                    filterPart += "t.projectName like '%" + nameArray[m] + "%'";
                                }
                            }
                        }
                    } else {
                        filterPart += "t." + paramName + " like :" + paramName;
                        args.add(new HqlParameter(paramName, "%" + filter.get(paramName) + "%"));
                    }
                } else {
                    filterPart += "t." + paramName + "=:" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }
                filterCounter++;
            }
        }
        if (filter.get("projectType") != null && filter.get("projectType").equals("0")) {
            filterPart += " and t.projectType in ('2','3','4')";
        }
        StringBuilder addDeptIdListFilter = new StringBuilder();
        if (addDeptIdList != null && addDeptIdList.size() > 0) {
            for (int i = 0; i < addDeptIdList.size(); i++) {
                addDeptIdListFilter.append("'").append(addDeptIdList.get(i)).append("',");
            }
        }
        if (addDeptIdListFilter != null && addDeptIdListFilter.length() > 1)
            filterPart += " and ( t.projectAdddeptId is null or t.projectAdddeptId not in (" + addDeptIdListFilter.deleteCharAt(addDeptIdListFilter.length() - 1) + "))";
        filterPart += " order by t.createDate DESC,t.id DESC";
        return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
    }

    public void saveProject(Project project) {
        getHibernateTemplate().saveOrUpdate(project);
    }

    public Project findProjectById(String id) {
        String hql = "from Project t where t.id='" + id + "' and t.removed='0'";
        List<Project> list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }

    public void update(Project project) {
        getHibernateTemplate().update(project);
    }

    public void deleteById(String id) {
        Project project = (Project) getHibernateTemplate().load(Project.class, id);
        if (project != null)
            getHibernateTemplate().delete(project);
    }

    @Override
    public List<Project> findByProjetNBoWithFuzzySearch(String projectNo) {
        String hql = "from Project t where t.removed='0' and t.projectNo like '%" + projectNo + "%'";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Project> findByProjectNameWithFuzzySearch(String projectName) {
        String hql = "from Project t where t.removed='0' and t.projectName like '%" + projectName + "%'";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Object[]> findCountGroupByAddDeptIds(List<String> addDeptIds) {
        StringBuilder sb = new StringBuilder();
        if (addDeptIds != null && addDeptIds.size() > 0) {
            for (int i = 0; i < addDeptIds.size(); i++) {
                sb.append("'").append(addDeptIds.get(i)).append("',");
            }
        }
        String hql = "select t.projectAdddeptId,count(*) from Project t where t.projectAdddeptId in (" + sb.deleteCharAt(sb.length() - 1) + ") and t.removed='0' group by t.projectAdddeptId order by t.projectAdddeptId ASC";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Object[]> findCountGroupByAddDeptIds(List<String> addDeptIds, String projectType) {
        StringBuilder sb = new StringBuilder();
        if (addDeptIds != null && addDeptIds.size() > 0) {
            for (int i = 0; i < addDeptIds.size(); i++) {
                sb.append("'").append(addDeptIds.get(i)).append("',");
            }
        }
        String hql = "";
        if (projectType != null && !"".equals(projectType) && projectType.equals("0")) {
            hql = "select t.projectAdddeptId,count(*) from Project t where t.projectAdddeptId in (" + sb.deleteCharAt(sb.length() - 1) + ") and t.projectType in ('2','3','4') and t.removed='0' group by t.projectAdddeptId order by t.projectAdddeptId ASC";
        } else {
            hql = "select t.projectAdddeptId,count(*) from Project t where t.projectAdddeptId in (" + sb.deleteCharAt(sb.length() - 1) + ") and t.projectType='" + projectType + "' and t.removed='0' group by t.projectAdddeptId order by t.projectAdddeptId ASC";
        }
        return getHibernateTemplate().find(hql);
    }


    public String getLineByCompanyId(String id) {
        Session session = this.getSession();
        String sql = "select line from c_unit_line_relation where company_id = '" + id + "'";
        Query query = session.createSQLQuery(sql).addScalar("line", Hibernate.STRING);
        return (String) query.uniqueResult();
    }

    @Override
    public List<Project> findAllByName(String projectName, String projectId) {
        String hql = "from Project t where t.projectName='" + projectName + "' and t.removed='0'";
        if (projectId != null && !"".equals(projectId)) {
            hql += " and t.id !='" + projectId + "'";
        }
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Project> findAllByNo(String projectNo, String projectId) {
        String hql = "from Project t where t.projectNo='" + projectNo + "' and t.removed='0'";
        if (projectId != null && !"".equals(projectId)) {
            hql += " and t.id !='" + projectId + "'";
        }
        return getHibernateTemplate().find(hql);
    }

    @Override
    public Long findSumOfProject() {
        String hql = "select count(*) from Project t where t.removed='0'";
        List<Long> list = getHibernateTemplate().find(hql);
        if (list == null || list.size() < 1) return null;
        return list.get(0);
    }

    @Override
    public Long findSumOfProjectByAddDeptIdAndType(String addDeptId, String type) {
        String hql = "select count(*) from Project t where t.removed='0' ";

        if (addDeptId != null && !"".equals(addDeptId)) {
            hql += " and t.projectAdddeptId ='" + addDeptId + "'";
        }
        if (type.equals("0")) {
            hql += " and t.projectType in ('2','3','4')";
        } else {
            hql += " and t.projectType ='" + type + "'";
        }
        List<Long> list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public Long findSumOfProjectByTypeWithoutAdddeptId(String type, List<String> adddeptId) {

        String hql = "select count(*) from Project t where t.removed='0' ";

        if (adddeptId != null && adddeptId.size() > 0) {
            StringBuilder idFilter = new StringBuilder();
            for (int i = 0; i < adddeptId.size(); i++) {
                idFilter.append("'").append(adddeptId.get(i)).append("',");
            }
            hql += " and (t.projectAdddeptId is null or t.projectAdddeptId not in (" + idFilter.deleteCharAt(idFilter.length() - 1).toString() + "))";
        }
        if (type.equals("0")) {
            hql += " and t.projectType in ('2','3','4')";
        } else {
            hql += " and t.projectType ='" + type + "'";
        }
        List<Long> list = getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public List<Project> findByProjetNBoWithFuzzySearch(String projectNo, String type) {
        String hql = "from Project t where t.removed='0' and t.projectNo like '%" + projectNo + "%'";
        if ("1".equals(type)) {
            hql += " and t.projectType = '1'";
        } else if ("2".equals(type)) {
            hql += " and (t.projectType = '2' or t.projectType = '3' or t.projectType = '4')";
        }
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Project> findByProjectNameWithFuzzySearch(String projectName, String type) {
        String hql = "from Project t where t.removed='0' and t.projectName like '%" + projectName + "%'";
        if ("1".equals(type)) {
            hql += " and t.projectType = '1'";
        } else if ("2".equals(type)) {
            hql += " and (t.projectType = '2' or t.projectType = '3' or t.projectType = '4')";
        }
        return getHibernateTemplate().find(hql);
    }

    @Override
    public Page findByPage(Map<String, Object> filter, int pageNo, int pageSize) {
        if (filter == null)
            filter = new HashMap<String, Object>();
        List<HqlParameter> args = new ArrayList<HqlParameter>();
        String hql = "select t from Project t ";
        String countHql = "select count(*) from Project t ";
        String filterPart = "";
        int filterCounter = 0;
        if (!filter.isEmpty()) {
            filterPart += " where ";
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext(); ) {
                if (filterCounter > 0) {
                    filterPart += " and ";
                }
                String paramName = i.next();
                if (paramName.equals("subSql")) {
                    filterPart += filter.get("subSql");
                } else if ("projectTypeSet".equals(paramName)) {
                    if (filter.get(paramName).equals("2")) {
                        filterPart += " t.projectType in (2,3,4)";
                    }
                } else if ("projectNo".equals(paramName) || "projectName".equals(paramName) || "projectAdddept".equals(paramName) || paramName.equals("approvalDate") || paramName.equals("dispatchNo")) {
                    filterPart += "t." + paramName + " like :" + paramName;
                    args.add(new HqlParameter(paramName, "%" + filter.get(paramName) + "%"));
                } else if (paramName.equals("projectStarttimePlanDateEnd")) {
                    filterPart += "t.projectStarttimePlanDate" + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectStarttimePlanDate")) {
                    filterPart += "t." + paramName + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("projectEndtimePlanDateStart")) {
                    filterPart += "t.projectEndtimePlanDate" + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("approvalDate")) {
                    filterPart += "t." + paramName + " >= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else if (paramName.equals("approvalDateEnd")) {
                    filterPart += "t.approvalDate" + " <= :" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                } else {
                    filterPart += "t." + paramName + "=:" + paramName;
                    args.add(new HqlParameter(paramName, filter.get(paramName)));
                }
                filterCounter++;
            }
        }
        filterPart += " order by t.createDate DESC,t.id DESC";
        return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
    }

    @Override
    public List<Project> findByProjectName(String projectName) {
        String hql = "from Project t where t.removed='0' and t.projectName='" + projectName + "'";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Project> findByDispatchNo(String dispatchNo) {
        String hql = "from Project t where t.removed='0' and lower(trim(t.dispatchNo))=lower('" + dispatchNo + "')";
        return getHibernateTemplate().find(hql);
    }
    
    @Override
    public List<Project> findByProjectTypeAndProjectAddeptId(String projectType, String projectAddeptId) {
        String hql = "from Project t where t.removed='0' ";
        if (projectType != null && !"".equals(projectType)) {
            if (projectType.equals("2")) {
                hql += " and t.projectType in (2,3,4)";
            } else if ("1".equals(projectType)) {
                hql += " and t.projectType = '" + projectType + "'";
            }
        }
        if (projectAddeptId != null && !"".equals(projectAddeptId)) {
            if ("other".equals(projectAddeptId)) {
                hql += " and t.projectAdddeptId not in ('2541','2542','2543','2544','2540','2718','2719','2720','2721','2722','2545','2546')";
            } else {
                hql += " and t.projectAdddeptId = '" + projectAddeptId + "'";
            }
        }
        hql += " order by t.createDate DESC,t.id DESC";
        return getHibernateTemplate().find(hql);
    }


    @Override
    public List<Project> findByFilter(Map<String, Object> filter) {
        if (filter == null)
            filter = new HashMap<String, Object>();
        String hql = "from Project t ";
        int filterCounter = 0;
        if (!filter.isEmpty()) {
            hql += " where ";
            for (Iterator<String> i = filter.keySet().iterator(); i.hasNext(); ) {
                String paramName = i.next();
                if (filterCounter > 0) {
                    hql += " and ";
                }

                if (paramName.equals("projectNo") || paramName.equals("projectAdddept") || paramName.equals("dispatchNo") || paramName.equals("projectName") || paramName.equals("approvalDate")) {
                    hql += "t." + paramName + " like '%" + filter.get(paramName) + "%'";
                } else if (paramName.equals("projectType")) {
                    if (filter.get(paramName).equals("2")) {
                        hql += " t.projectType in (2,3,4)";
                    } else if ("1".equals(filter.get(paramName))) {
                        hql += " t.projectType = '" + filter.get(paramName) + "'";
                    }
                } else if (paramName.equals("approvalDate")) {
                    hql += "t." + paramName + " >= '" + filter.get(paramName) + "'";
                } else if (paramName.equals("approvalDateEnd")) {
                    hql += "t.approvalDate" + " <= '" + filter.get(paramName) + "'";
                } else if (paramName.equals("projectAdddeptId")) {
                    if ("other".equals(filter.get(paramName))) {
                        hql += " t.projectAdddeptId not in ('2541','2542','2543','2544','2540','2718','2719','2720','2721','2722','2545','2546')";
                    } else if ("center".equals(filter.get(paramName))) {
                        hql += " t.projectAdddeptId in ('2718','2719','2720','2721','2722','2545')";
                    } else {
                        hql += " t.projectAdddeptId = '" + filter.get(paramName) + "'";
                    }
                } else {
                    hql += "t." + paramName + "= '" + filter.get(paramName) + "'";
                }
                filterCounter++;
            }
        }

        hql += " order by t.createDate DESC,t.id DESC";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Project> findByCondition(String condition, String projectAddeptId, Map<String, Object> filter) {
        String hql = "";
        if ("condition1".equals(condition)) {
            hql = "select new com.wonders.ic.project.entity.bo.Project(p) from Project p where p.projectType in (2,3,4) and p.removed=0 and p.id in (select c.projectId from Contract c where c.removed='0' and to_date(p.approvalDate,'yyyy-mm-dd')<(to_date(c.contractSignedDate,'yyyy-mm-dd')-120)) ";

            if (StringUtils.isNotEmpty(filter.get("projectNo") == null ? "" : filter.get("projectNo").toString())) {
                hql += " and p.projectNo like '%" + filter.get("projectNo").toString() + "%'";
            }
            if (StringUtils.isNotEmpty(filter.get("projectName") == null ? "" : filter.get("projectName").toString())) {
                hql += " and p.projectName like '%" + filter.get("projectName").toString() + "%'";
            }
            if (StringUtils.isNotEmpty(filter.get("dispatchNo") == null ? "" : filter.get("dispatchNo").toString())) {
                hql += " and p.dispatchNo like '%" + filter.get("dispatchNo").toString() + "%'";
            }
            if (StringUtils.isNotEmpty(filter.get("projectAdddept") == null ? "" : filter.get("projectAdddept").toString())) {
                hql += " and p.projectAdddept like '%" + filter.get("projectAdddept").toString() + "%'";
            }
            if (StringUtils.isNotEmpty(filter.get("professionalType") == null ? "" : filter.get("professionalType").toString())) {
                hql += " and p.professionalType = '" + filter.get("professionalType").toString() + "'";
            }

            if (StringUtils.isNotEmpty(filter.get("approvalDate") == null ? "" : filter.get("approvalDate").toString())) {
                hql += " and p.approvalDate >= '" + filter.get("approvalDate").toString() + "'";
            }
            if (StringUtils.isNotEmpty(filter.get("approvalDateEnd") == null ? "" : filter.get("approvalDateEnd").toString())) {
                hql += " and p.approvalDate <= '" + filter.get("approvalDateEnd").toString() + "'";
            }
            if (projectAddeptId != null && !"".equals(projectAddeptId)) {
                if ("other".equals(projectAddeptId)) {
                    hql += "and p.projectAdddeptId not in ('2541','2542','2543','2544','2540','2718','2719','2720','2721','2722','2545','2546')";
                } else if ("center".equals(projectAddeptId)) {
                    hql += "and p.projectAdddeptId in ('2718','2719','2720','2721','2722','2545')";
                } else {
                    hql += "and p.projectAdddeptId='" + projectAddeptId + "'";
                }
            }
            hql += " and p.id not in (" +
                    " select distinct p.id from Project p,Contract c where p.projectType in (2,3,4) and p.removed=0" +
                    " and p.id = c.projectId and c.contractType in ('2','2,1','2,2','2,3') and c.removed=0 and c.projectId is not null" +
                    " and p.approvalDate is not null and c.contractSignedDate is not null" +
                    " and to_date(p.approvalDate,'yyyy-mm-dd') >= (to_date(c.contractSignedDate,'yyyy-mm-dd')-120)" +
                    ")";
            hql += " and p.id not in (" +
                    "select distinct p.id from Project p,Contract c where p.projectType in (2,3,4) and p.removed=0" +
                    " and p.id = c.projectId and c.contractType in ('2','2,1','2,2','2,3') and c.removed=0 and c.projectId is not null" +
                    " and p.approvalDate is not null and c.contractSignedDate is not null" +
                    " and to_date(p.approvalDate,'yyyy-mm-dd') >= (to_date(c.contractSignedDate,'yyyy-mm-dd')-120)" +
                    ")";
            hql += " order by p.createDate DESC,p.id DESC";
        }
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<Project> findByConditonAndFilter(String condition,
                                                 Map<String, Object> filter) {
    /*
		String hql ="";
		if("condition1".equals(condition)){
			hql ="select new com.wonders.ic.project.entity.bo.Project(p) from Project p where p.projectType in (2,3,4) and p.removed=0 and p.id in (select c.projectId from Contract c where c.removed='0' and to_date(p.approvalDate,'yyyy-mm-dd')<(to_date(c.contractSignedDate,'yyyy-mm-dd')-120)) ";
			
			if(projectAddeptId!=null && !"".equals(projectAddeptId)){
				if("other".equals(projectAddeptId)){
					hql += "and p.projectAdddeptId not in (2541,2542,2543,2544,2540,2718,2719,2720,2721,2722,2545)";
				}else{
					hql += "and p.projectAdddeptId='"+projectAddeptId+"'";
				}
			}
			hql +=" and p.id not in (" +
				" select distinct p.id from Project p,Contract c where p.projectType in (2,3,4) and p.removed=0"+ 
				" and p.id = c.projectId and c.contractType in ('2','2,1','2,2','2,3') and c.removed=0 and c.projectId is not null"+
				" and p.approvalDate is not null and c.contractSignedDate is not null"+
				" and to_date(p.approvalDate,'yyyy-mm-dd') >= (to_date(c.contractSignedDate,'yyyy-mm-dd')-120)" +
			")";
			hql += " order by p.createDate DESC,p.id DESC";
		}
		return getHibernateTemplate().find(hql);*/
        return null;
    }

    @Override
    public String executeSQLReturnOneData(String sql) {
        List<Object> result = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
        if (result == null || result.size() < 1 || result.get(0) == null) return "0";
        return result.get(0).toString();
    }

    @Override
    public List<Project> findProjectByHql(String hql, int begin, int dataSize) {
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        if (begin > 0) {
            query.setFirstResult(begin);
        }
        query.setMaxResults(dataSize);
        return query.list();
    }

    @Override
    public int findCountBySql(String sql) {
        SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        BigDecimal de = (BigDecimal) query.uniqueResult();

        return de.intValue();
    }

    @Override
    public List<Object[]> findBySQL(String sql) {
        return getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
    }

    @Override
	public Page findProjectByPage(Map<String, Object> filter, Integer pageNo, Integer pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from Project t ";
		String countHql = "select count(*) from Project t ";
		String filterPart = "";
		int filterCounter = 0;
		filterPart += " where t.removed = '0' ";
		if (!filter.isEmpty()) {
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				filterPart += " and ";
				String paramName = i.next();
				if(paramName.equals("dispatchNo")||paramName.equals("projectName")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));					
				}else if(paramName.equals("projectState")){
					filterPart += "t." + paramName + " !='3'";
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				filterCounter++;
			}
		}
		Page page = findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
		List list = new ArrayList();
	
		for(Object o : page.getResult()){
			ProjectDto tmp = new ProjectDto();
			Project p = (Project) o;
			tmp.setId(p.getId());
			tmp.setProjectNo(p.getProjectNo());
			tmp.setProjectName(p.getProjectName());
			tmp.setProjectType(p.getProjectType());
			tmp.setDispatchNo(p.getDispatchNo());
			tmp.setProjectAdddept(p.getProjectAdddept());
			tmp.setProjectAddperson(p.getProjectAddperson());
			tmp.setProjectBudgetAll(p.getProjectBudgetAll());
			tmp.setProjectMoneysource(p.getProjectMoneysource());
			tmp.setPrimaryDesignBudget(p.getPrimaryDesignBudget());
			tmp.setContracts(this.findContractsByProjectId(p.getId()));
			list.add(tmp);
		}
		page.setResult(list);
		return page;
	}

	@Override
	public List<ContractDto> findContractsByProjectId(String projectId) {
		String hql = "select t from Contract t where t.removed=0 and t.projectId='" + projectId +"'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		List<ContractDto> list = new ArrayList<ContractDto>();
		for(Object o : query.list()){
			ContractDto tmp = new ContractDto();
			Contract c = (Contract) o;
			tmp.setId(c.getId());
			tmp.setContractNo(c.getContractNo());
			tmp.setSelfNo(c.getSelfNo());
			tmp.setContractName(c.getContractName());
			tmp.setContractType(c.getContractType());
			tmp.setContractOwnerExecuteName(c.getContractOwnerExecuteName());
			tmp.setBuildSupplierName(c.getBuildSupplierName());
			tmp.setContractSignedDate(c.getContractSignedDate());
			tmp.setContractPrice(c.getContractPrice());
			list.add(tmp);
		}
		return list;
	}

}
