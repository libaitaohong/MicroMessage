package com.mybatis.service;

import com.mybatis.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghe on 2017/4/25.
 * 维护Service
 * 维护相关的业务功能
 */
public class MaintainService {
    /**
     * 单条删除
     * 由于在DeleteOneServlet中的req.getParameter是String类型，需要这里传入id是int型，在使用时需要转换类型。
     * 但是这种转化类型与判断是否为空等操作都不应该在servlet中做，而是应该在service中做。
     * 所以将传入参数的值直接设置为String类型，然后对这个id进行判空
     */
    public void deleteOne(String id){
        System.out.println("进入service.deleteOne");
        if (id != null && !"".equals(id.trim())){
            MessageDao messageDao = new MessageDao();
            messageDao.deleteOne(Integer.valueOf(id));
        }
        System.out.println("退出service.deleteOne");
    }

    /**
     * 批量删除
     */
    public void deleteBatch(String[] ids){
        MessageDao messageDao = new MessageDao();
        List<Integer> idList = new ArrayList<Integer>();
        for (String id : ids){
            idList.add(Integer.valueOf(id));
        }
        messageDao.deleteBatch(idList);

    }
}
