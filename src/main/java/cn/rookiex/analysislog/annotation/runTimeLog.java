package cn.rookiex.analysislog.annotation;

import cn.rookiex.analysislog.enums.LogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe :
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface runTimeLog {
    /**
     * 日志类型
     * @return logType
     */
    LogType logType() default LogType.RunTime;

    /**
     * 日志记录的方法
     * @return logName
     */
    String logName() default "";
}
