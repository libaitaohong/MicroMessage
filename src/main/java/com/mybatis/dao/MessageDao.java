package com.mybatis.dao;

import com.mybatis.bean.Message;
import com.mybatis.db.DBAccess;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghe on 2017/4/23.
 * 和message表相关的数据库操作
 */
public class MessageDao {
    /**
     * 根据查询条件查询消息列表
     * 查询条件是command description
     * 返回值是一个消息列表List<Message>
     */
    public List<Message> queryMessageList(String command, String description){
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行sql语句
            //通过namesace.sql的名字执行sql语句。selsct语句就是.select
            messageList = sqlSession.selectList("Message.queryMessageList",message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null)
                sqlSession.close();
        }
        return messageList;
    }

    /**
     * 单条删除
     */
    public void deleteOne(int id){
        System.out.println("进入dao.deleteOne()");
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlsession执行SQL语句
            System.out.println("sqlSession.delete");
            sqlSession.delete("Message.deleteOne",id);
            //Mybatis对connection做了封装，默认不自动提交，需要我们手动提交
            sqlSession.commit();
            System.out.println("sql执行完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null)
                sqlSession.close();
            System.out.println("退出dao.deleteOne()");
        }
    }
    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlsession执行SQL语句
            sqlSession.delete("Message.deleteBatch",ids);
            //Mybatis对connection做了封装，默认不自动提交，需要我们手动提交
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null)
                sqlSession.close();
        }
    }

}
