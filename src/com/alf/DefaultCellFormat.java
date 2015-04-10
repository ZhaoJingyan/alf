package com.alf;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ĭ�ϵ�Ԫ��ʽ��
 * 
 * @since {@link com.alf.CellFormat}
 * @author Zhao Jinyan
 *
 */
public class DefaultCellFormat implements CellFormat {

    /**
     * ���췽��
     */
    public DefaultCellFormat() {
	dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // ���ڸ�ʽ
	decimalFormat = new DecimalFormat("###################.00");
    }

    /**
     * ���ڸ�ʽ��
     */
    private DateFormat dateFormat;

    /**
     * ���ָ�ʽ�����
     */
    private DecimalFormat decimalFormat;

    /**
     * ����ָ����ʽ���ַ�����
     * 
     * @param object ����ת��Ϊ�ַ����Ķ���
     * @param index object�ĵ���˳��
     * @return
     */
    public String print(Object object, int index) {
	String result = null;
	if (object == null)
	    return "";
	if (object instanceof Date) {
	    result = dateFormat.format((Date) object);
	} else if (object instanceof String) {
	    result = (String) object;
	} else if (object instanceof Double) {
	    result = decimalFormat.format((Double) object);
	} else {
	    result = object.toString();
	}

	return result;
    }
}
