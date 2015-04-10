package com.alf;

import java.util.List;

import com.alf.bean.AntiMoneyLaunderingData;

/**
 * 反洗文件接口，表示一个反洗钱文件。
 * 
 * @author Zhao Jinyan
 * @since {@link com.alf.CreateAntiMoneyLaunderingDatasFile}
 */
public interface AntiMoneyLaunderingDatesFile {

    /**
     * 在文件中添加一条AntiMoneyLaunderingData记录。
     * 
     * @param data 一条反些钱记录
     */
    public void addAntiMoneyLaunderingData(AntiMoneyLaunderingData data);

    /**
     * 在文件中添加多条AntiMoneyLaunderingData记录。
     * 
     * @param datas 多条反洗钱记录
     */
    public void addAntiMoneyLaunderingDatas(List<AntiMoneyLaunderingData> datas);

    /**
     * 关闭文件IO。
     */
    public void close();

    /**
     * 获取写入文件的行数。
     * 
     * @return 文件行数
     */
    public long getLineNumber();
}
