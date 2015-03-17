package com.alf.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;
import com.alf.dao.AntiMoneyLaunderingDataDao;

/**
 * 基本业务操作。
 * 
 * @author Zhao Jinyan
 *
 */
public class AntiMoneyLaunderingDataService {

	/**
	 * 初始化业务逻辑。
	 * 
	 * @throws IOException
	 *             IO错误
	 */
	public AntiMoneyLaunderingDataService() throws IOException {
		this.antiMoneyLaunderingDataDao = new AntiMoneyLaunderingDataDao();
	}

	private AntiMoneyLaunderingDataDao antiMoneyLaunderingDataDao;

	/**
	 * 获取指定日期的反洗钱数据。
	 * 
	 * @param date
	 *            日期
	 * @return 反洗钱数据
	 */
	public List<AntiMoneyLaunderingData> queryAntiMoneyLaunderingData(Date date) {
		return this.antiMoneyLaunderingDataDao
				.getAntiMoneyLaunderingDatasBy(date);
	}

	/**
	 * 关闭业务逻辑。
	 */
	public void close() {
		antiMoneyLaunderingDataDao.closeSqlSession();
	}
}
