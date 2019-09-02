package cn.rookiex.analysisLog;

import cn.rookiex.analysisLog.aop.LogAspectHandler;
import cn.rookiex.analysisLog.data.AnalysisLogConfig;
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
