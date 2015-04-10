package com.alf.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.alf.bean.AntiMoneyLaunderingData;
import com.alf.db.DBAccess;

/**
 * ��ϴǮ���ݻ���������
 * 
 * @author Zhao Jinyan
 *
 */
public class AntiMoneyLaunderingDataDao implements
	AntiMoneyLaunderingDataMessage {

    /**
     * ����Dao��ͬʱ�����ݿ����ӡ�
     * 
     * @throws IOException �������Ӵ��쳣
     */
    public AntiMoneyLaunderingDataDao() throws IOException {
	/* ��ȡ���ݿ����� */
	this.sqlSession = DBAccess.getSqlSession();
	/* ��̬����ӿ� */
	this.antiMoneyLaunderingDataMessage = this.sqlSession
		.getMapper(AntiMoneyLaunderingDataMessage.class);
    }

    /**
     * ���ݿ�����
     */
    private SqlSession sqlSession;

    /**
     * AntiMoneyLaunderingData���ݿ������̬����ӿ�
     */
    private AntiMoneyLaunderingDataMessage antiMoneyLaunderingDataMessage;

    /**
     * �������ڣ����ط�ϴǮ���ݡ�
     * 
     * @param date ����
     * @return ��ϴǮ����
     */
    @Override
    public List<AntiMoneyLaunderingData> getAntiMoneyLaunderingDatasBy(Date date) {
	return antiMoneyLaunderingDataMessage
		.getAntiMoneyLaunderingDatasBy(date);
    }

    /**
     * �ر����ݿ����ӡ�
     */
    public void closeSqlSession() {
	this.sqlSession.close();
    }

    /**
     * ����ϴǮ���ݲ�����С�
     * 
     * @param antiMoneyLaunderingData Ҫ����ӵ����ݵ�bean
     * @return Ӱ������
     */
    @Override
    public int addAntiMoneyLaunderingData(
	    AntiMoneyLaunderingData antiMoneyLaunderingData) {
	return this.antiMoneyLaunderingDataMessage
		.addAntiMoneyLaunderingData(antiMoneyLaunderingData);
    }

    /**
     * �ύ����
     */
    public void commit() {
	this.sqlSession.commit();
    }

    /**
     * ��ȡ�������ݡ�
     * 
     * @return ��������
     */
    @Override
    public List<AntiMoneyLaunderingData> getAllAntiMoneyLaunderingDatas() {
	return this.antiMoneyLaunderingDataMessage
		.getAllAntiMoneyLaunderingDatas();
    }
}
