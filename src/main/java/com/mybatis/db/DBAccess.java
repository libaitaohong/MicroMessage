package com.mybatis.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by zhanghe on 2017/4/23.
 * 访问数据库类
 */
public class DBAccess {
     public SqlSession getSqlSession() throws IOException {
         System.out.println("进入DBAccess.getSqlSession()");
        //通过配置文件获取数据库连接
         //通过Resources类获取Reader这个对象，然后给出核心配置文件所在路径
         //将异常抛给dao层去处理
         Reader reader = Resources.getResourceAsReader("Configuration.xml");
         //通过配置信息构建一个SqlSessionFactory
         SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
         //通过sqlSessionFactory打开一个数据库会话
         SqlSession sqlSession = sqlSessionFactory.openSession();
         System.out.println("返回sqlSession");
         return sqlSession;
     }
}
