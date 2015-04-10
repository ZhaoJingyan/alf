package com.alf.dao;

import java.util.Date;
import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;

/**
 * myBatis动态代理接口，为{@link com.alf.bean.AntiMoneyLaunderingData
 * AntiMoneyLaunderingData}提供基本操作。
 * 
 * @author Zhao Jinyan
 * @since com.alf.dao.AntiMoneyLaunderingDataDao
 */
public interface AntiMoneyLaunderingDataMessage {

    /**
     * 根据日期，返回反洗钱数据。
     * 
     * @param date 日期
     * @return 反洗钱数据
     */
    public List<AntiMoneyLaunderingData> getAntiMoneyLaunderingDatasBy(Date date);

    /**
     * 添加一个AntiMoneyLaunderingData。
     * 
     * @param antiMoneyLaunderingData 一个反洗钱数据实例
     * @return 影响行数
     */
    public int addAntiMoneyLaunderingData(
	    AntiMoneyLaunderingData antiMoneyLaunderingData);

    /**
     * 获取所有数据。
     * 
     * @return 所有数据
     */
    public List<AntiMoneyLaunderingData> getAllAntiMoneyLaunderingDatas();
}
