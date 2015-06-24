package com.wonders.ic.meetEvent.service;


import com.wonders.ic.meetEvent.entity.vo.MeetEventVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public interface MeetEventService {
    public List<MeetEventVo> getMeetInfo(String startTime,String endTime);
}
