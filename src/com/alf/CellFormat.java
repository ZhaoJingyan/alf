package com.alf;

/**
 * ��Ԫ��ʽ����ӿڣ����ݲ�ͬ���͵����ݣ����ɲ�ͬ���ַ�����
 * 
 * @author Zhao Jinyan
 *
 */
public interface CellFormat {

    /**
     * ����ָ����ʽ���ַ�����
     * 
     * @param object ����ת��Ϊ�ַ����Ķ���
     * @param index object�ĵ���˳��
     * @return
     */
    public String print(Object object, int index);
}
