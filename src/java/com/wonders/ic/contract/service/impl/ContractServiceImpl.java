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

package com.wonders.ic.contract.service.impl;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.ic.contract.dao.ContractDao;
import com.wonders.ic.contract.entity.bo.CompanyRoute;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.entity.bo.HtBa;
import com.wonders.ic.contract.entity.bo.HtXx;
import com.wonders.ic.contract.entity.bo.KpiClear;
import com.wonders.ic.contract.entity.bo.KpiClearStatus;
import com.wonders.ic.contract.service.ContractService;
import com.wonders.ic.contract.service.ShortMsgService;
import com.wonders.ic.quantitiesSubject.dao.QuantitiesSubjectDao;
import com.wonders.ic.quantitiesSubject.entity.bo.QuantitiesSubject;
import com.wonders.ic.quantitiesSubject.service.QuantitiesSubjectService;
import com.wonders.ic.wbs.dao.WbsDao;
import com.wondersgroup.framework.core.bo.Page;
import org.apache.commons.lang.StringUtils;

/**
 * @author ycl
 * @author modify by $Author$
 * @version $Revision$
 * @date 2012-10-9
 * @since 1.0
 */

public class ContractServiceImpl implements ContractService {
    private ContractDao contractDao;
    private QuantitiesSubjectDao quantitiesSubjectDao;
    private QuantitiesSubjectService quantitiesSubjectService;
    private WbsDao wbsDao;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");


    public void setContractDao(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    public void setQuantitiesSubjectDao(QuantitiesSubjectDao quantitiesSubjectDao) {
        this.quantitiesSubjectDao = quantitiesSubjectDao;
    }

    public void setWbsDao(WbsDao wbsDao) {
        this.wbsDao = wbsDao;
    }


    public void setQuantitiesSubjectService(
            QuantitiesSubjectService quantitiesSubjectService) {
        this.quantitiesSubjectService = quantitiesSubjectService;
    }

    public Page findContractByPage(Map<String, Object> filter, int pageNo,
                                   int pageSize) {
        return contractDao.findContractByPage(filter, pageNo, pageSize);
    }

    @Override
    public Page findContractByPage(Map<String, Object> filter, int pageNo,
                                   int pageSize, List<String> addDeptIdList) {
        return contractDao.findContractByPage(filter, pageNo, pageSize, addDeptIdList);
    }

    @Override
    public Page findContractByPageWithoutExecuteId(Map<String, Object> filter, int pageNo, int pageSize, List<String> addDeptIdList) {
        return contractDao.findContractByPageWithoutExecuteId(filter, pageNo, pageSize, addDeptIdList);
    }

    @Override
    public Page findByContractName(Map<String, Object> filter, int pageNo, int pageSize) {
        return contractDao.findByContractName(filter, pageNo, pageSize);
    }

    @Override
    public Page findContractChangeItems(Map<String, Object> filter,String loc, int pageNo,
                                        int pageSize) {
        return contractDao.findContractChangeItems(filter,loc, pageNo, pageSize);
    }


    @Override
    public void saveContract(Contract contract) {
        contractDao.saveContract(contract);
    }

    @Override
    public Contract findContractById(String id) {

        return contractDao.findContractById(id);
    }

    @Override
    public void update(Contract contract) {
        contractDao.update(contract);
    }

    @Override
    public void deleteById(String id) {
        contractDao.deleteById(id);
    }

    @Override
    public List<Contract> findByContractNoWithFuzzySearch(String contractNo) {
        return contractDao.findByContractNoWithFuzzySearch(contractNo);
    }

    @Override
    public List<Contract> findByContractNameWithFuzzySearch(String contractName) {
        return contractDao.findByContractNameWithFuzzySearch(contractName);
    }

    @Override
    public void deleteByIdOnLogical(String id) {
        if (id != null && !"".equals(id))
            contractDao.deleteByIdOnLogical(id);
    }

    @Override
    public Map<String, String> findCountGroupByContractType() {
        Map<String, String> result = null;
        List<Object[]> list = contractDao.findCountGroupByContractType();
        if (list != null && list.size() > 0) {
            result = new HashMap<String, String>();
            for (int i = 0; i < list.size(); i++) {
                result.put(list.get(i)[0].toString(), list.get(i)[1].toString());
            }
        }
        return result;
    }

    @Override
    public Map<String, String> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList) {
        List<Object[]> list = contractDao.findCountGroupByAddDeptIds(contractOwnerExecuteIdList);
        Map<String, String> resultMap = null;
        if (list != null && list.size() > 0) {
            resultMap = new HashMap<String, String>();
            for (int i = 0; i < list.size(); i++) {
                resultMap.put(list.get(i)[0].toString(), list.get(i)[1].toString());
            }
        }
        return resultMap;
    }

    @Override
    public Map<String, String> findCountGroupByAddDeptIds(List<String> contractOwnerExecuteIdList, String contractType) {
        List<Object[]> list = contractDao.findCountGroupByAddDeptIds(contractOwnerExecuteIdList, contractType);
        Map<String, String> resultMap = null;
        if (list != null && list.size() > 0) {
            resultMap = new HashMap<String, String>();
            for (int i = 0; i < list.size(); i++) {
                resultMap.put(list.get(i)[0].toString(), list.get(i)[1].toString());
            }
        }
        return resultMap;
    }

    @Override
    public List<Object[]> findAllByProjectIdAndQuantitiesInEstimateSubject(String projectId, String estimateSubjectId) {

        return contractDao.findAllByProjectIdAndQuantitiesInEstimateSubject(projectId, estimateSubjectId);
    }

    @Override
    public List<Contract> findAllByContractIdArray(String[] contractIdArray) {
        List<String> idList = null;
        if (contractIdArray != null && contractIdArray.length > 0) {
            idList = new ArrayList<String>();
            for (int i = 0; i < contractIdArray.length; i++) {
                idList.add(contractIdArray[i]);
            }
        }
        return contractDao.findAllByContractIdList(idList);
    }

    @Override
    public void updateToBindBatchEstimateSubject(List<Contract> contractList, String subjectId, String subjectName) {
        if (contractList == null || contractList.size() < 1 || "".equals(subjectId) || "".equals(subjectName)) {
            return;
        }
        List<QuantitiesSubject> updateList = new ArrayList<QuantitiesSubject>();

        for (int i = 0; i < contractList.size(); i++) {
            Contract tempContract = contractList.get(i);
            QuantitiesSubject qs = new QuantitiesSubject();
            qs.setContractId(tempContract.getId());
            qs.setContractNo(tempContract.getContractNo());
            qs.setInvestEstimateId(subjectId);
            qs.setInvestEstimateName(subjectName);
            qs.setRemoved("0");
            qs.setCreateDate(sdf.format(new Date()));
            qs.setSubjectName(tempContract.getContractName());
            qs.setTotalPrice(getFormattedMoney(String.valueOf(Double.valueOf(tempContract.getContractPrice()) * 10000)));
            qs.setUnitPrice(getFormattedMoney(String.valueOf(Double.valueOf(tempContract.getContractPrice()) * 10000)));
            qs.setAmount("1");
            qs.setDataType("1");
            quantitiesSubjectService.saveQuantitiesSubject(qs);
            quantitiesSubjectService.saveAddOnPage(qs, "0", "1", "1", "1", "no", "");
        }

    }


    //得到保留6位小数后的字符串
    public String getFormattedMoney(String money) {
        if (money == null || "".equals(money)) {
            money = "0";
        }
        Double result = 0d;
        try {
            result = Double.valueOf(money);
        } catch (NumberFormatException e) {
            result = 0d;
        }
        DecimalFormat df = new DecimalFormat("#0.000000");
        return df.format(result);
    }

    @Override
    public List<Object[]> findCountByContractType(List<String> typeList, Map<String, Object> filter, List<String> addDeptIdList) {
        return contractDao.findCountByContractType(typeList, filter, addDeptIdList);
    }


    @Override
    public List<Object[]> findCountByContractTypeWithoutExecuteId(
            List<String> typeList, Map<String, Object> filter,
            List<String> addDeptIdList) {
        return contractDao.findCountByContractTypeWithoutExecuteId(typeList, filter, addDeptIdList);
    }

    @Override
    public String findSumOfContractPriceByInvestEstimateId(String investEstimateId) {
        //String result = contractDao.findSumOfContractPriceByInvestEstimateId(investEstimateId);
        String result = quantitiesSubjectDao.findSumOfTotalPriceByInvestEstimateId(investEstimateId);  //单位是元
        if (result == null || "null".equals(result)) {
            return "0";
        } else {
            return String.valueOf(Double.valueOf(result) / 10000);
        }
    }

    @Override
    public void updateProjectNameByProjectId(String projectId, String projectName) {
        contractDao.updateProjectNameByProjectId(projectId, projectName);
    }


    @Override
    public void updateProjectNoAndProjectNameByProjectId(String projectId,
                                                         String projectNo, String projectName) {
        contractDao.updateProjectNoAndProjectNameByProjectId(projectId, projectNo, projectName);

    }

    @Override
    public void deleteContractAndWbsAndQuantitiesByContractId(String contractId) {
        contractDao.deleteByIdOnLogical(contractId);        //逻辑删合同
        wbsDao.deleteAllByObjectIdOnLogical("2", contractId);    //wbs逻辑删除
        quantitiesSubjectDao.deleteAllByContractIdOnLogical(contractId);

    }

    /**
     * 将HtXx表中的数据导入C_contract表中，用完之后删除本方法
     *
     * @return
     */
    public void importHtXxToContract() {
        int addNum = 0;
        int updateNumm = 0;
        List<HtXx> htXxlist = contractDao.findAllHtXx();
        if (htXxlist != null && htXxlist.size() > 0) {
            for (int i = 0; i < htXxlist.size(); i++) {
                HtXx htXx = htXxlist.get(i);
                HtBa htBa = contractDao.findHtBaByHtXxId(htXx.getId());
                List<Contract> contractList = contractDao.findAllByContractNo(htXx.getContractNum());
                if (contractList != null && contractList.size() > 0) { //合同存在,更新
                    List<Contract> updateList = new ArrayList<Contract>();
                    for (int m = 0; m < contractList.size(); m++) {
                        Contract contract = contractList.get(m);
                        contract = setContractInfo(contract, htXx, htBa);
                        if (contract != null) {
                            updateList.add(contract);
                        }
                    }
                    contractDao.saveAll(updateList);
                    updateNumm += updateList.size();
                } else {    //合同不存在，新增
                    Contract contract = null;
                    if (htXx != null && htBa != null) {
                        contract = new Contract();
                        contract = setContractInfo(contract, htXx, htBa);
                    }
                    if (contract != null) {
                        contract.setRemoved("0");
                        contract.setCreateDate(sdf.format(new Date()));
                        contractDao.save(contract);
                        addNum++;
                    }
                }

            }
        }
        System.out.println("新增数据=" + addNum);
        System.out.println("更新数据=" + updateNumm);
    }


    @Override
    public void updateBatchEdit(String[] ids, String executeName,
                                String executeId, String type) {
        contractDao.updateBatchEdit(ids, executeName, executeId, type);
    }


    @Override
    public Long findSumOfContractByTypeWithoutExecuteId(String type) {

        return contractDao.findSumOfContractByTypeWithoutExecuteId(type);
    }

    @Override
    public Long findSumOfContractByTypeWithoutExecuteId(String type,
                                                        List<String> executeIdList) {

        return contractDao.findSumOfContractByTypeWithoutExecuteId(type, executeIdList);
    }

    public Contract setContractInfo(Contract contract, HtXx htXx, HtBa htBa) {

        if (htXx != null) {
            contract.setContractName(htXx.getContractName());
            contract.setContractNo(htXx.getContractNum());
            contract.setContractPrice(htXx.getContractMoney());
            contract.setContractOwnerExecuteName(htXx.getProjectCoName());
            contract.setPayType(htXx.getContractMoneyType());
            if (htXx.getFileNum() != null && !"".equals(htXx.getFileNum()) && !"8a818e903ad34e26013af75b1b0a077f".equals(contract.getProjectId())) {
                contract.setProjectId(htXx.getFileNum());
            }
        }
        String endDateString = null;
        if (htBa != null) {
            contract.setContractSignedDate(htBa.getExaminePassTime());
            contract.setContractPassedDate(htBa.getContractSignTime());
            contract.setContractStartDate(htBa.getExaminePassTime());
            endDateString = htBa.getPerformTimelimit();
        }

        String startDateString = contract.getContractStartDate();
        if (endDateString != null && endDateString.length() <= 10) {        //必须比格式yyyy-MM-dd的长度小
            if (endDateString.indexOf("-") > 0) {    //说明是yyyy-MM-dd格式的
                contract.setContractEndDate(endDateString);
            } else {
                try {
                    int dayBetween = Integer.valueOf(endDateString);        //捕捉到异常后，后面的代码不执行
                    Date startDate = sdf2.parse(startDateString);
                    contract.setContractEndDate(sdf2.format(addDays(startDate, dayBetween)));
                } catch (NumberFormatException e) {
                    return contract;
                } catch (ParseException e) {
                    return contract;
                }
            }
        }
        return contract;
    }

    public Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }


    @Override
    public List<Contract> findByContractNoLowerCase(String contractNo) {
        return contractDao.findByContractNoLowerCase(contractNo);
    }

    @Override
    public void saveAll(List<Contract> contractList) {
        contractDao.saveAll(contractList);
    }

    @Override
    public List<Contract> findByContractNoWithFuzzySearch(String contractNo, String type) {
        return contractDao.findByContractNoWithFuzzySearch(contractNo, type);
    }

    @Override
    public List<Contract> findByContractNameWithFuzzySearch(String contractName, String type) {
        return contractDao.findByContractNameWithFuzzySearch(contractName, type);
    }

    @Override
    public List<Contract> findByContractType(String contractType) {

        return contractDao.findByContractType(contractType);
    }


    @Override
    public List<Contract> findByFilter(Map<String, Object> filter) {
        return contractDao.findByFilter(filter);
    }

    @Override
    public String findOneData(String sql) {

        return contractDao.executeSQLReturnOneData(sql);
    }

    @Override
    public List<Contract> findContractBySelfNoWithFuzzySearch(String selfNo) {

        return contractDao.findContractBySelfNoWithFuzzySearch(selfNo);
    }

    @Override
    public boolean isSelfNoExistIgnoreCase(String selfNo) {
        List<Contract> list = contractDao.findBySelfNoIgnoreCase(selfNo);
        if (list != null && list.size() > 0) return true;
        return false;
    }

    @Override
    public List<CompanyRoute> findCompanyByName(String name) {

        return contractDao.findCompanyByNameAndType(name, "1");
    }

    @Override
    public List<CompanyRoute> findLineByCompanyId(String id) {
        return contractDao.findByPidAndType(id, "2");
    }

    @Override
    public List<CompanyRoute> findAllLine() {

        return contractDao.findAllLine();
    }

    @Override
    public List<Contract> findByHql(String hql) {
        return contractDao.findContractByHQL(hql);
    }

    @Override
    public List<Object[]> findBySQL(String sql) {
        return contractDao.findContractBySQL(sql);
    }

    @Override
    public void updateAllContract(List<Contract> list) {
        contractDao.saveAll(list);
    }

    @Override
    public List<Contract> findAllContractByUpdateDate(String startDate,
                                                      String endDate) {

        return contractDao.findAllContractByUpdateDate(startDate, endDate);
    }

    @Override
    public void saveKpiClear(KpiClear kpiClear) {
        contractDao.saveKpiClear(kpiClear);
    }

    @Override
    public void saveKpiClearStatus(KpiClearStatus kpiClearStatus) {
        contractDao.saveKpiClearStatus(kpiClearStatus);
    }

    @Override
    public void updateKpiClearStatus(KpiClearStatus kpiClearStatus) {
        contractDao.updateKpiClearStatus(kpiClearStatus);
    }

    @Override
    public List<Object[]> findKpiContractByType(String type, Map<String, Object> filter) {
        return contractDao.findKpiContractByType(type, filter);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<KpiClear> findKpiClearByContractId(String id) {
        return contractDao.findKpiClearByContractId(id);
    }

    @Override
    public List<Map<String, Object>> findUserGroupByName(String name) {
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        List<Object[]> list = contractDao.findUserGroupByName(name);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] tmp = list.get(i);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("ownerExecute", tmp[0]);
                map.put("group", tmp[1]);
                res.add(map);
            }
            return res;
        }
        return null;
    }

    public String findQueryFilterByName(String name) {
        return contractDao.findQueryFilterByName(name);
    }

    @Override
    public List<KpiClearStatus> findKpiClearStatusByContractId(String id) {
        return contractDao.findKpiClearStatusByContractId(id);
    }


    @Override
    public String findNameByGroup(String executeName, String contractGroup) {
        return contractDao.findNameByGroup(executeName, contractGroup);
    }

    @Override
    public List<Object[]> getKpiClearCount() {
        return contractDao.getKpiClearCount();
    }

    @Override
    public List<Map<String, Object>> findKpiByContractId(String contractId) {
        List<Object[]> list = contractDao.findKpiByContractId(contractId);
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (list != null) {
            for (Object[] obj : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("type", obj[0]);
                map.put("status", obj[1]);
                result.add(map);
            }
        }
        return result;
    }

    @Override
    public List<String> getNameByContractIdInGroup(String contractId) {
        List<String> userGroups = contractDao.findUserGroup();
        List<String> result = new ArrayList<String>();
        boolean flag = false;
        for (String obj : userGroups) {
            if (StringUtils.isNotBlank(obj)){
                flag = contractDao.isLoginNameInGroup(obj, contractId);

                if (flag) {
                    result = contractDao.findNameByQueryFilter(obj);
                }
            }

        }
        return result;
    }

}
