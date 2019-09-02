package cn.rookiex.analysisLog.data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author : Rookiex
 * @Date : 2019/09/02
 * @Describe :
 */
@ConfigurationProperties(prefix = AnalysisLogConfig.PREFIX)
public class AnalysisLogConfig {
    public static final String PREFIX = "cn.rookiex.analysis-log";
    private long longTime = 200;

    public void setLongTime(long longTime) {
        this.longTime = longTime;
    }

    public long getLongTime() {
        return longTime;
    }
}
