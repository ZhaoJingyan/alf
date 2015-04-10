package com.alf.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alf.bean.AntiMoneyLaunderingData;
import com.alf.db.DBAccess;

/**
 * 反洗钱数据基本操作。
 * 
 * @author Zhao Jinyan
 *
 */
public class AntiMoneyLaunderingDataDao implements
	AntiMoneyLaunderingDataMessage {

    /**
     * 创建Dao，同时打开数据库连接。
     * 
     * @throws IOException 数据连接打开异常
     */
    public AntiMoneyLaunderingDataDao() throws IOException {
	/* 获取数据库连接 */
	this.sqlSession = DBAccess.getSqlSession();
	/* 动态代理接口 */
	this.antiMoneyLaunderingDataMessage = this.sqlSession
		.getMapper(AntiMoneyLaunderingDataMessage.class);
    }

    /**
     * 数据库连接
     */
    private SqlSession sqlSession;

    /**
     * AntiMoneyLaunderingData数据库操作动态代理接口
     */
    private AntiMoneyLaunderingDataMessage antiMoneyLaunderingDataMessage;

    /**
     * 根据日期，返回反洗钱数据。
     * 
     * @param date 日期
     * @return 反洗钱数据
     */
    @Override
    public List<AntiMoneyLaunderingData> getAntiMoneyLaunderingDatasBy(Date date) {
	return antiMoneyLaunderingDataMessage
		.getAntiMoneyLaunderingDatasBy(date);
    }

    /**
     * 关闭数据库连接。
     */
    public void closeSqlSession() {
	this.sqlSession.close();
    }

    /**
     * 将反洗钱数据插入表中。
     * 
     * @param antiMoneyLaunderingData 要被添加到数据的bean
     * @return 影响行数
     */
    @Override
    public int addAntiMoneyLaunderingData(
	    AntiMoneyLaunderingData antiMoneyLaunderingData) {
	return this.antiMoneyLaunderingDataMessage
		.addAntiMoneyLaunderingData(antiMoneyLaunderingData);
    }

    /**
     * 提交事务。
     */
    public void commit() {
	this.sqlSession.commit();
    }

    /**
     * 获取所有数据。
     * 
     * @return 所有数据
     */
    @Override
    public List<AntiMoneyLaunderingData> getAllAntiMoneyLaunderingDatas() {
	return this.antiMoneyLaunderingDataMessage
		.getAllAntiMoneyLaunderingDatas();
    }
}
