package cn.rookiex.analysislog;

import cn.rookiex.analysislog.enums.LogType;
import org.apache.log4j.Logger;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe :
 */
public class AnalysisLog {
    private Logger logger;

    protected AnalysisLog(Logger logger) {
        this.logger = logger;
    }

    private Object checkAddString(Object message) {
        return message;
    }

    public void info(Object message, Throwable t) {
        logger.info(checkAddString(message), t);
    }

    public void info(LogType logType, Object message) {
        logger.info(checkAddString(message));
    }

    public void info(Object message) {
        logger.info(checkAddString(message));
    }

    public void warn(Object message) {
        logger.warn(checkAddString(message));
    }

    public void warn(Object message, Throwable t) {
        logger.warn(checkAddString(message), t);
    }

    public void debug(Object message) {
        logger.debug(checkAddString(message));
    }

    public void debug(Object message, Throwable t) {
        logger.debug(checkAddString(message), t);
    }
}
