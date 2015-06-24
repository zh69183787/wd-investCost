package com.wonders.ic.meetEvent.service.impl;

import com.wonders.ic.meetEvent.entity.vo.FixContractVo;
import com.wonders.ic.meetEvent.entity.vo.SignedContractVo;
import com.wonders.ic.meetEvent.service.ContractEventService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/1/27
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class ContractEventServiceImpl implements ContractEventService {
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SignedContractVo> getSignedInfo(String startTime, String endTime) {
        //获得需要计算的部门 固定条件
        String sql = "select distinct c.contract_owner_execute_name company,contract_owner_execute_id\n" +
                "from c_contract c where c.contract_type in ('2,1','2,2','2,3')\n" +
                "and c.contract_grouping = '2' and c.contract_owner_execute_id is not null\n" +
                "and c.removed=0 order by c.contract_owner_execute_id";
        List<SignedContractVo> result = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(SignedContractVo.class));

        //过滤
        StringBuffer query = new StringBuffer();
        List<String> param = new ArrayList<String>();
        if (startTime != null && startTime.length() > 0) {
            query.append(" and c.contract_signed_date >= ?");
            param.add(startTime);
        }
        if (endTime != null && endTime.length() > 0) {
            query.append(" and c.contract_signed_date <= ?");
            param.add(endTime);
        }

        //获得计算部门制定条件下的 price
        sql = "select contract_owner_execute_id execId,\n" +
                "c.contract_owner_execute_name execName, sum(c.contract_price) price\n" +
                "from c_contract c where c.contract_type in ('2,1','2,2','2,3')\n" +
                "and c.contract_grouping = '2' and c.contract_owner_execute_id is not null\n" +
                "and removed=0\n" + query +
                " and c.stat_type='1'\n" +
                "group by contract_owner_execute_id,c.contract_owner_execute_name\n" +
                "order by c.contract_owner_execute_id";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, param.toArray());
        Map<String, Double> temp = new HashMap<String, Double>();
        for (Map<String, Object> map : list) {
            temp.put((String) map.get("execName"), ((BigDecimal) map.get("price")).doubleValue());
        }
        for (SignedContractVo vo : result) {
            if (temp.containsKey(vo.company)) {
                vo.price = temp.get(vo.company);
            }
        }

        //获取分组信息
        sql = "select c.contract_type type,c.contract_owner_execute_id execId,\n" +
                "c.contract_owner_execute_name execName,\n" +
                "count(*) count \n" +
                "from c_contract c where c.contract_type in ('2,1','2,2','2,3')\n" +
                "and c.contract_grouping = '2' and c.contract_owner_execute_id is not null and removed=0\n" +
                query +
                " and  c.stat_type= '1'\n" +
                "group by c.contract_type,c.contract_owner_execute_id,c.contract_owner_execute_name";
        list = this.jdbcTemplate.queryForList(sql, param.toArray());
        Map<String, List<String>> temp2 = new HashMap<String, List<String>>();
        for (Map<String, Object> map : list) {
            String a = (String) map.get("execName");
            String b = (String) map.get("type");
            Long c = ((BigDecimal) map.get("count")).longValue();
            if (temp2.containsKey(a)) {
                temp2.get(a).add(b + "||" + c);
            } else {
                List<String> l = new ArrayList<String>();
                l.add(b + "||" + c);
                temp2.put(a, l);
            }
        }
        for (SignedContractVo vo : result) {
            if (temp2.containsKey(vo.company)) {
                List<String> d = temp2.get(vo.company);
                if (d.size() > 0) {
                    for (String s : d) {
                        String type = s.split("\\|\\|")[0];
                        Long execCount = Long.parseLong(s.split("\\|\\|")[1]);
                        if (vo.count.containsKey(type)) {
                            vo.count.put(type, vo.count.get(type) + execCount);
                        } else {
                            vo.count.put(type, execCount);
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<FixContractVo> getFixInfo(String startTime, String endTime) {
        //过滤
        StringBuffer query = new StringBuffer();
        List<String> param = new ArrayList<String>();
        if (startTime != null && startTime.length() > 0) {
            query.append(" and c.contract_signed_date >= ?");
            param.add(startTime);
        }
        if (endTime != null && endTime.length() > 0) {
            query.append(" and c.contract_signed_date <= ?");
            param.add(endTime);
        }
        //获得需要计算的部门 固定条件
        String sql = "select * from (\n" +
                "select  contract_owner_execute_id companyId,\n" +
                "c.contract_owner_execute_name company,\n" +
                "sum(c.contract_price) price,\n" +
                "count(*) count,\n" +
                "sum(case c.stat_type when '2' then c.contract_price else '0' end) fixPrice,\n" +
                "count(case c.stat_type when '2' then 1 else null end) fixCount\n" +
                "from c_contract c where  c.contract_grouping = '1' \n" +
                "and c.contract_owner_execute_id is not null\n" +
                "and c.contract_type in ('2,1','2,2','2,3') and removed=0\n" + query +
               // " and c.stat_type= '2'\n" +
                "group by c.contract_owner_execute_id,c.contract_owner_execute_name\n" +
                ") order by companyId\n";
        List<FixContractVo> result = this.jdbcTemplate.query(sql, param.toArray(), new BeanPropertyRowMapper(FixContractVo.class));
        return result;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        System.out.println(list.toArray().length);
        int i = 98;
        char b = (char) i;
        char c = 'a';
        System.out.println(Integer.toHexString(0));
    }
}
