package com.wonders.ic.meetEvent.service.impl;

import com.wonders.ic.meetEvent.entity.vo.MeetEventVo;
import com.wonders.ic.meetEvent.service.MeetEventService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class MeetEventServiceImpl implements MeetEventService{
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MeetEventVo> getMeetInfo(String startTime, String endTime) {
        //过滤
        StringBuffer query = new StringBuffer();
        List<String> param = new ArrayList<String>();
        if((startTime.length()  == 0 && endTime.length() == 0)
            || (startTime==null && endTime == null)){
            query.append("and to_date(t.start_date,'yyyy-mm-dd') >ADD_MONTHS(sysdate, -12)");
        }else {
            if (startTime != null && startTime.length() > 0) {
                query.append(" and t.start_date >= ?");
                param.add(startTime);
            }
            if (endTime != null && endTime.length() > 0) {
                query.append(" and t.start_date <= ?");
                param.add(endTime);
            }
        }


        String sql = "select x month,count(*) count from (\n" +
                "select to_char(to_date(t.start_date,'yyyy-mm-dd'),'yyyy-mm') x from t_met_meetflow t where t.meet_addr in (10080,10081)\n" +
                "and t.removed=0 \n" +
                "and to_date(t.start_date,'yyyy-mm-dd') <ADD_MONTHS(sysdate,0) \n" +
                "and t.meet_state='已通过'\n" + query +
                ") group by x order by x";
        return this.jdbcTemplate.query(sql,param.toArray(),new BeanPropertyRowMapper(MeetEventVo.class));
    }
}
