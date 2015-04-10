package com.alf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置格式对象，默认格式对象为{@link com.alf.DefaultCellFormat} 。 格式对象必须实现接口
 * {@link com.alf.CellFormat}。
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
