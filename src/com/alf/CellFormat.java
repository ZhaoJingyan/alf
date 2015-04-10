package com.alf;

/**
 * 单元格式定义接口，根据不同类型的数据，生成不同的字符串。
 * 
 * @author Zhao Jinyan
 *
 */
public interface CellFormat {

    /**
     * 生成指定格式的字符串。
     * 
     * @param object 将被转化为字符串的对象
     * @param index object的调用顺序
     * @return
     */
    public String print(Object object, int index);
}
