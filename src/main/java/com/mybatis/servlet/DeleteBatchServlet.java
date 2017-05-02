package com.mybatis.servlet;

import com.mybatis.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhanghe on 2017/5/2.
 * 批量删除
 */
public class DeleteBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符集
        req.setCharacterEncoding("UTF-8");
        //接收页面的值，
        String[] ids = req.getParameterValues("id");
        //servlet调用service
        MaintainService maintainService = new MaintainService();
        //req.getParameter获取的只有String类型，所以这里要转换类型。
        //id还有可能是空值，所以还要做判断。其实转型和判断都不应该在servlet里做，应该在service里面做。
        maintainService.deleteBatch(ids);
        //跳转
        //req.getRequestDispatcher("/WEB-INF/jsp/back/list1.jsp").forward(req,resp);
        //删除后直接回到jsp界面没有数据，体验不好，所以回到/list.action1
        req.getRequestDispatcher("/List.action1").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

