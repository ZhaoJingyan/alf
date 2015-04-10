package com.alf.dao;

import java.util.Date;
import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;

/**
 * myBatis��̬����ӿڣ�Ϊ{@link com.alf.bean.AntiMoneyLaunderingData
 * AntiMoneyLaunderingData}�ṩ����������
 * 
 * @author Zhao Jinyan
 * @since com.alf.dao.AntiMoneyLaunderingDataDao
 */
public interface AntiMoneyLaunderingDataMessage {

    /**
     * �������ڣ����ط�ϴǮ���ݡ�
     * 
     * @param date ����
     * @return ��ϴǮ����
     */
    public List<AntiMoneyLaunderingData> getAntiMoneyLaunderingDatasBy(Date date);

    /**
     * ���һ��AntiMoneyLaunderingData��
     * 
     * @param antiMoneyLaunderingData һ����ϴǮ����ʵ��
     * @return Ӱ������
     */
    public int addAntiMoneyLaunderingData(
	    AntiMoneyLaunderingData antiMoneyLaunderingData);

    /**
     * ��ȡ�������ݡ�
     * 
     * @return ��������
     */
    public List<AntiMoneyLaunderingData> getAllAntiMoneyLaunderingDatas();
}
