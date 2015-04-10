package com.alf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���ø�ʽ����Ĭ�ϸ�ʽ����Ϊ{@link com.alf.DefaultCellFormat} �� ��ʽ�������ʵ�ֽӿ�
 * {@link com.alf.CellFormat}��
 * 
 * @author Zhao Jinyan
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Format {
    public String value() default "com.alf.DefaultCellFormat";
}
