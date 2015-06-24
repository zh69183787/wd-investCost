package com.wonders.api;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.ic.corporation.entity.bo.PsCorporation;
import com.wonders.ic.corporation.service.PsCorporationService;
import com.wondersgroup.framework.core.bo.Page;

public class PsCorporationAction extends BaseAction implements ModelDriven<PsCorporation> {

	PsCorporation psCorporation = new PsCorporation();
	
	private PsCorporationService psCorporationService; 
	
	@Override
	public PsCorporation getModel() {
		return psCorporation;
	}

	public String corporations() throws Exception{
		
		Map<String, Object> filter = new HashMap<String, Object>();
		
		if(null!=psCorporation.getCompanyNameChn() && !"".equals(psCorporation.getCompanyNameChn())){
			filter.put("companyNameChn", psCorporation.getCompanyNameChn());
		}
		
		Page page = psCorporationService.findByCompanyNameChn(filter, getCurrentPageNo(), getPageSize());
		Map resMap = new HashMap();
		resMap.put("result", page.getResult());
		resMap.put("page", page);
		outputJson(resMap, getCallback());
		return Action.NONE;
	}
	
	public PsCorporationService getPsCorporationService() {
		return psCorporationService;
	}

	public void setPsCorporationService(PsCorporationService psCorporationService) {
		this.psCorporationService = psCorporationService;
	}
	

}
