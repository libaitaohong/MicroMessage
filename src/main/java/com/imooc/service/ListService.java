package com.imooc.service;

import com.imooc.bean.Message;
import com.imooc.dao.MessageDao;

import java.util.List;

/**
 * Created by zhanghe on 2017/4/23.
 * 列表相关的业务功能
 */
public class ListService {
    public List<Message> queryMessageList(String command, String description){
        MessageDao messageDao = new MessageDao();
        //返回查询结果
        return messageDao.queryMessageList(command,description);
    }
}
