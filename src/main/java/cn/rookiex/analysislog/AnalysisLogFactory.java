package cn.rookiex.analysislog;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe :
 */
public class AnalysisLogFactory {
    public static final String LOG_NAME_PREFIX = "AnalysisLog.";
    public static final String LOG_NAME_SEPARATOR = ".";

    public static AnalysisLog getAnalysisLogger(Class clazz) {
        Logger logger = Logger.getLogger(LOG_NAME_PREFIX + LOG_NAME_SEPARATOR + clazz.getName());
        return new AnalysisLog(logger);
    }

    public static AnalysisLog getAnalysisLogger(String logName) {
        Logger logger = Logger.getLogger(LOG_NAME_PREFIX + LOG_NAME_SEPARATOR + logName);
        return new AnalysisLog(logger);
    }
}
