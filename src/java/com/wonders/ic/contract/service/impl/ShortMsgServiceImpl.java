package com.wonders.ic.contract.service.impl;

import com.wonders.ic.contract.dao.ShortMsgDao;
import com.wonders.ic.contract.service.ShortMsgService;

import java.util.Map;

/**
 * Created by Administrator on 2015/2/3.
 */
public class ShortMsgServiceImpl implements ShortMsgService {
    private ShortMsgDao shortMsgDao;

    public void setShortMsgDao(ShortMsgDao shortMsgDao) {
        this.shortMsgDao = shortMsgDao;
    }


    @Override
    public void sendMessage(String receiverId,String content) {

        Map<String,String> map = shortMsgDao.getUserInfoByName(receiverId);
        String phone = map==null ? "" : map.get("phone");
        shortMsgDao.sendMessage(phone, content);
    }
}
