package com.imooc.dao;

import com.imooc.bean.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
        List<Message> messageList = new ArrayList<Message>();
        //jdbc连接数据库
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mirco_message","root","root");
            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1 ");
            //因为有条件查询，但是statement在有条件查询之前就创建好了，这样如果再有了条件没法拼接sql语句
            //所以设一个List来缓存sql语句，然后拼接进statement中
            List<String> paramList = new ArrayList<String>();
            if (command != null && !"".equals(command.trim())){
                sql.append(" and COMMAND=?");
                paramList.add(command);
            }
            if (description != null && !"".equals(description.trim())){
                sql.append(" and DESCRIPTION like '%' ? '%'");
                paramList.add(description);
            }
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            //把条件拼到statement中
            for (int i = 0; i < paramList.size(); i++){
                statement.setString(i + 1, paramList.get(i));
            }
            ResultSet rs = statement.executeQuery();
            //取出来是多组数据，再单独建立一个与数据库表对应的java bean。bean的包叫bean。
            //然后把取出的值放入到list里面
            while(rs.next()){
                Message message = new Message();
                messageList.add(message);
                message.setId(rs.getString("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }




}
