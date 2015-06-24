package com.wonders.ic.meetEvent.service;

import com.wonders.ic.meetEvent.entity.vo.FixContractVo;
import com.wonders.ic.meetEvent.entity.vo.SignedContractVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
public interface ContractEventService {
    public List<SignedContractVo> getSignedInfo(String startTime,String endTime);
    public List<FixContractVo> getFixInfo(String startTime,String endTime);
}
