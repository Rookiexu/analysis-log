package cn.rookiex.analysislog;

import cn.rookiex.analysislog.aop.LogAspectHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author : Rookiex
 * @Date : 2019/08/31
 * @Describe :
 */
@Configuration
@Import(LogAspectHandler.class)
public class AnalysisLogAutoConfig {

}
