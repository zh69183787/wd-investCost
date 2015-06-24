package com.wonders.ic.meetEvent.action;

import com.wonders.ic.meetEvent.entity.vo.FixContractVo;
import com.wonders.ic.meetEvent.entity.vo.MeetEventVo;
import com.wonders.ic.meetEvent.entity.vo.SignedContractVo;
import com.wonders.ic.meetEvent.service.ContractEventService;
import com.wonders.ic.meetEvent.service.MeetEventService;
import net.sf.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class MeetEventAction implements ServletRequestAware,ServletResponseAware{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private MeetEventService meetEventService;
    private ContractEventService contractEventService;

    /**
     * 已签合同总体情况
     * 查询条件 签约时间
     */
    public String getSignedContractInfo() throws Exception {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        List<SignedContractVo> vo = this.contractEventService.getSignedInfo(startTime,endTime);
        JSONObject root = new JSONObject();
        root.put("result",vo);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(root.toString(1,1));
        return "none";
    }

    /**
     * 维修合同统计情况
     * 查询条件 签约时间
     */
    public String getFixContractInfo(){
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        List<FixContractVo> vo = this.contractEventService.getFixInfo(startTime, endTime);
        request.setAttribute("result",vo);
        request.setAttribute("startTime",startTime);
        request.setAttribute("endTime",endTime);
        return "fix";
    }

    /**
     * 评标室情况
     * 查询条件 开始时间
     * 查询条件 结束时间
     */
    public String getEvaluationInfo() throws Exception{
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        List<MeetEventVo> vo = this.meetEventService.getMeetInfo(startTime,endTime);
        JSONObject root = new JSONObject();
        root.put("result",vo);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(root.toString(1,1));
        return "none";
    }

    public MeetEventService getMeetEventService() {
        return meetEventService;
    }

    public void setMeetEventService(MeetEventService meetEventService) {
        this.meetEventService = meetEventService;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public ContractEventService getContractEventService() {
        return contractEventService;
    }

    public void setContractEventService(ContractEventService contractEventService) {
        this.contractEventService = contractEventService;
    }
}
