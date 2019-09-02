package cn.rookiex.analysislog;

import cn.rookiex.analysislog.aop.LogAspectHandler;
import cn.rookiex.analysislog.data.AnalysisLogConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author : Rookiex
 * @Date : 2019/08/31
 * @Describe :
 */
@Configuration
@ConditionalOnProperty(prefix = AnalysisLogConfig.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@Import(LogAspectHandler.class)
@EnableConfigurationProperties(AnalysisLogConfig.class)
public class AnalysisLogAutoConfig {

}
