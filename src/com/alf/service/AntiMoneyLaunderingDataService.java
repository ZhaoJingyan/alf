package com.alf.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;
import com.alf.dao.AntiMoneyLaunderingDataDao;

/**
 * ����ҵ�������
 * 
 * @author Zhao Jinyan
 *
 */
public class AntiMoneyLaunderingDataService {

	/**
	 * ��ʼ��ҵ���߼���
	 * 
	 * @throws IOException
	 *             IO����
	 */
	public AntiMoneyLaunderingDataService() throws IOException {
		this.antiMoneyLaunderingDataDao = new AntiMoneyLaunderingDataDao();
	}

	private AntiMoneyLaunderingDataDao antiMoneyLaunderingDataDao;

	/**
	 * ��ȡָ�����ڵķ�ϴǮ���ݡ�
	 * 
	 * @param date
	 *            ����
	 * @return ��ϴǮ����
	 */
	public List<AntiMoneyLaunderingData> queryAntiMoneyLaunderingData(Date date) {
		return this.antiMoneyLaunderingDataDao
				.getAntiMoneyLaunderingDatasBy(date);
	}

	/**
	 * �ر�ҵ���߼���
	 */
	public void close() {
		antiMoneyLaunderingDataDao.closeSqlSession();
	}
}
