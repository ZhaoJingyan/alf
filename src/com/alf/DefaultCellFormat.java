package com.alf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 默认单元格式。
 * 
 * @since {@link com.alf.CellFormat}
 * @author Zhao Jinyan
 *
 */
public class DefaultCellFormat implements CellFormat {
	
	/**
	 * 构造方法
	 */
	public DefaultCellFormat() {
		dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // 日期格式
	}
	
	/**
	 * 日期格式化
	 */
	private DateFormat dateFormat;
	
	/**
	 * 生成指定格式的字符串。
	 * 
	 * @param object 将被转化为字符串的对象
	 * @param index object的调用顺序
	 * @return
	 */
	public String print(Object object, int index){
		String result = null;
		if(object == null)
			return "";
		if(object instanceof Date){
			result = dateFormat.format((Date)object);
		} else if (object instanceof String) {
			result = (String)object;
		} else {
			result = object.toString();
		}
		
		return result;
	}
}
