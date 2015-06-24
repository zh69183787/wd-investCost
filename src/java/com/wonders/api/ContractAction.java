package com.wonders.api;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.wonders.ic.contractChangeItem.service.ContractChangeItemService;
import com.wonders.ic.contractChangeProtocol.entity.bo.ContractChangeProtocol;
import com.wonders.ic.contractChangeProtocol.service.ContractChangeProtocolService;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.ic.contract.entity.bo.Contract;
import com.wonders.ic.contract.service.ContractService;
import com.wondersgroup.framework.core.bo.Page;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ContractAction  extends BaseAction implements ModelDriven<Contract> {

	private ContractService contractService;
    private ContractChangeItemService contractChangeItemService;
    private ContractChangeProtocolService contractChangeProtocolService;
	private Contract contract = new Contract();
    private String loc;
	private String contractId;
    private Date startDate;
    private Date endDate;
	
	@Override
	public Contract getModel() {
		return contract;
	}
	
	public String getKpiClearCount() throws Exception{
		List<Object[]> list = contractService.getKpiClearCount();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		for(Object[] obj : list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("kpiType", (String.valueOf((Character)obj[0])));
			map.put("count", obj[1]);
			result.add(map);
		}
		Map resMap = new HashMap();
		resMap.put("result", result);
		outputJson(resMap,getCallback());
		return Action.NONE;
	}

    public String history()throws Exception{
        Map<String, Object> filter = new HashMap<String, Object>();

        if(null != contract.getSelfNo() && !"".equals(contract.getSelfNo())){
            filter.put("selfNo", contract.getSelfNo());
        }
        if(null != contract.getContractNo() && !"".equals(contract.getContractNo())){
            filter.put("contractNo", contract.getContractNo());
        }
        if(null != contractId && !"".equals(contractId)){
            filter.put("contractId", contractId);
        }

        Map changeItemMap = contractChangeItemService.getChangeItemNum(filter);
        Map protocolMap = contractChangeProtocolService.getProtocolNumAndPrice(filter);
        HashMap param = new HashMap();
        param.put("id",contractId);
        List<Contract> list = contractService.findByFilter(param);
        String url = "/";
        if(list!=null){
            try {
                Resource resource = new ClassPathResource("/config.properties");
                Properties props = PropertiesLoaderUtils.loadProperties(resource);
                url = props.getProperty("apiUrl");

                url += "investCost/contract/showView.action?id=" + list.get(0).getId();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map result = new HashMap();
        result.put("changeItemTotalNum",changeItemMap.get("changeItemTotalNum"));
        result.put("protocolTotalNum",protocolMap.get("protocolTotalNum"));
        result.put("protocolTotalPrice",protocolMap.get("protocolTotalPrice"));
        result.put("url",url);
        outputJson(result, getCallback());
        return Action.NONE;
    }
	
	public String contracts() throws Exception{
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		if(null != contract.getContractName() && !"".equals(contract.getContractName())){
			filter.put("contractName", contract.getContractName());
		}
		
		Page page = contractService.findByContractName(filter, getCurrentPageNo(), getPageSize());
		
		Map resMap = new HashMap();
		resMap.put("result", page.getResult());
		resMap.put("page", page);
		outputJson(resMap, getCallback());
		return Action.NONE;
	}

    public String syncData() throws Exception{
        contractChangeProtocolService.syncData(startDate,endDate);
        contractChangeItemService.syncData(startDate,endDate);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", "数据同步成功");
        outputJson(resultMap, getCallback());
        return Action.NONE;
    }
	
	public String changeItems() throws Exception{

		Map<String, Object> filter = new HashMap<String, Object>();
		
		if(null != contract.getContractName() && !"".equals(contract.getContractName())){
			filter.put("contractName", contract.getContractName());
		}
		if(null != contract.getContractNo() && !"".equals(contract.getContractNo())){
			filter.put("contractNo", contract.getContractNo());
		}
		if(null != contractId && !"".equals(contractId)){
			filter.put("contractId", contractId);
		}
		
		Page page = contractService.findContractChangeItems(filter,loc, getCurrentPageNo(), getPageSize());
		
		Map resMap = new HashMap();
		resMap.put("result", page.getResult());
		resMap.put("page", page);
		outputJson(resMap, getCallback());
		return Action.NONE;
	}

	
	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public ContractChangeItemService getContractChangeItemService() {
        return contractChangeItemService;
    }

    public void setContractChangeItemService(ContractChangeItemService contractChangeItemService) {
        this.contractChangeItemService = contractChangeItemService;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ContractChangeProtocolService getContractChangeProtocolService() {
        return contractChangeProtocolService;
    }

    public void setContractChangeProtocolService(ContractChangeProtocolService contractChangeProtocolService) {
        this.contractChangeProtocolService = contractChangeProtocolService;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
