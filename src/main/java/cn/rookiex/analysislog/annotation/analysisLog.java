package cn.rookiex.analysislog.annotation;

import cn.rookiex.analysislog.enums.LogEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe : 分析日志的注解,默认是输出是记录调用超时的方法,也可以设置logType为其他的,只打印异常信息
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface analysisLog {
    /**
     * 日志类型
     * @return logType
     */
    LogEnum logType() default LogEnum.RUN_LONG_TIME;

}
