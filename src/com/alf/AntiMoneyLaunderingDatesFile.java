package com.alf;

import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;

/**
 * ��ϴ�ļ��ӿڣ���ʾһ����ϴǮ�ļ���
 * 
 * @author Zhao Jinyan
 * @since {@link com.alf.CreateAntiMoneyLaunderingDatasFile}
 */
public interface AntiMoneyLaunderingDatesFile {

    /**
     * ���ļ������һ��AntiMoneyLaunderingData��¼��
     * 
     * @param data һ����ЩǮ��¼
     */
    public void addAntiMoneyLaunderingData(AntiMoneyLaunderingData data);

    /**
     * ���ļ�����Ӷ���AntiMoneyLaunderingData��¼��
     * 
     * @param datas ������ϴǮ��¼
     */
    public void addAntiMoneyLaunderingDatas(List<AntiMoneyLaunderingData> datas);

    /**
     * �ر��ļ�IO��
     */
    public void close();

    /**
     * ��ȡд���ļ���������
     * 
     * @return �ļ�����
     */
    public long getLineNumber();
}
