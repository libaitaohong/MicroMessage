package com.imooc.servlet;

import com.imooc.bean.Message;
import com.imooc.service.ListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghe on 2017/4/20.
 * 列表页面初始化控制
 */
public class ListServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符集，防止乱码
        req.setCharacterEncoding("UTF-8");
        //接收页面的值，用两个变量来接收List页面来的两个查询条件参数command和description
        String command = req.getParameter("command");
        String description = req.getParameter("description");
        //向页面传值。下面是为了查询之后能够把之前输入的值留在文本框中，还需要在jsp里面设置value的值
        req.setAttribute("command",command);
        req.setAttribute("description",description);
        //servlet调用service
        ListService listService = new ListService();
        //查询消息列表并传给页面。最后放到request的Attribute里面，传入到页面中.
        req.setAttribute("messageList",listService.queryMessageList(command,description));
        //跳转
        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
