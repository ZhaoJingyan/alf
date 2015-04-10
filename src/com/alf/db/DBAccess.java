package com.alf.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * ���ݿ������࣬��ȡ���ݿ����ӡ� ������ͨ��mybatis�������ݿ⡣
 * 
 * @author Zhao Jinyan
 * @since <a href="http://www.imooc.com">Ľ����</a>
 */
public class DBAccess {

    /**
     * sqlSession����
     */
    private static SqlSessionFactory sqlSessionFactory = null;

    /**
     * ����һ��ȫ�µĴ�״̬��sqlSession��
     * 
     * @return ���ݿ�����
     * @throws IOException �����ļ���ȡ�쳣
     */
    public static SqlSession getSqlSession() throws IOException {
	if (sqlSessionFactory == null) {
	    /* ��ȡ�����ļ� */
	    Reader reader = Resources.getResourceAsReader("Configuration.xml");
	    /* ��ȡsqlSession���� */
	    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	/* �򿪲�����һ��sqlSession */
	return sqlSessionFactory.openSession();
    }
}
