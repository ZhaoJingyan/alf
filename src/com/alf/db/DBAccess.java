package com.alf.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 数据库连接类，获取数据库连接。 本程序通过mybatis访问数据库。
 * 
 * @author Zhao Jinyan
 * @since <a href="http://www.imooc.com">慕课网</a>
 */
public class DBAccess {

    /**
     * sqlSession工厂
     */
    private static SqlSessionFactory sqlSessionFactory = null;

    /**
     * 返回一个全新的打开状态的sqlSession。
     * 
     * @return 数据库连接
     * @throws IOException 配置文件读取异常
     */
    public static SqlSession getSqlSession() throws IOException {
	if (sqlSessionFactory == null) {
	    /* 读取配置文件 */
	    Reader reader = Resources.getResourceAsReader("Configuration.xml");
	    /* 获取sqlSession工厂 */
	    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	/* 打开并返回一个sqlSession */
	return sqlSessionFactory.openSession();
    }
}
