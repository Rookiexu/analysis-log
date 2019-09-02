package cn.rookiex.analysisLog.aop;

import cn.rookiex.analysisLog.AnalysisLog;
import cn.rookiex.analysisLog.AnalysisLogFactory;
import cn.rookiex.analysisLog.annotation.analysisLog;
import cn.rookiex.analysisLog.data.AnalysisLogConfig;
import cn.rookiex.analysisLog.enums.LogEnum;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static cn.rookiex.analysisLog.AnalysisLogFactory.LOG_NAME_SEPARATOR;

/**
 * @Author : Rookiex
 * @Date : 2019/08/30
 * @Describe :
 */
@Aspect
@Component
@Order(0)//加载顺序
public class LogAspectHandler {

    private AnalysisLogConfig analysisLogConfig;

    @Autowired
    public void setAnalysisLogConfig(AnalysisLogConfig analysisLogConfig) {
        this.analysisLogConfig = analysisLogConfig;
    }

    @Around(value = "@annotation(analysisLog)")
    public Object before(ProceedingJoinPoint joinPoint, analysisLog analysisLog) {
        long startTime = System.currentTimeMillis();
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            logSystemExInfo(joinPoint, throwable);
            throwable.printStackTrace();
        }

        if (analysisLog.logType().equals(LogEnum.COUNT_LOG)) {
            logCountCalls(joinPoint, startTime, analysisLog.logType());
        } else if (analysisLog.logType().equals(LogEnum.RUN_LONG_TIME)) {
            logRunTimeInfo(joinPoint, startTime, analysisLog.logType());
        }
        return proceed;
    }

    private void logSystemExInfo(ProceedingJoinPoint joinPoint, Throwable throwable) {
        String exLogName = getLogName(joinPoint, LogEnum.SYSTEM_EX_LOG);
        AnalysisLog analysisLogger = AnalysisLogFactory.getAnalysisLogger(exLogName);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("logType", LogEnum.SYSTEM_EX_LOG.getValue());
        map.put("method", exLogName);
        map.put("exMsg", throwable);
        analysisLogger.info(JSON.toJSONString(map));
    }

    private void logCountCalls(ProceedingJoinPoint joinPoint, long startTime, LogEnum logEnum) {
        String logName = getLogName(joinPoint, logEnum);
        AnalysisLog analysisLogger = AnalysisLogFactory.getAnalysisLogger(logName);
        long overTime = System.currentTimeMillis();
        long cost = overTime - startTime;
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("logType", LogEnum.COUNT_LOG.getValue());
        map.put("method", logName);
        map.put("costTime", cost);
        map.put("count", 1);
        analysisLogger.info(JSON.toJSONString(map));
    }

    private String getLogName(ProceedingJoinPoint joinPoint, LogEnum runTimeLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String logTypeValue = runTimeLog.getValue();
        String annotationName = String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        return logTypeValue + LOG_NAME_SEPARATOR + annotationName;
    }

    private void logRunTimeInfo(ProceedingJoinPoint joinPoint, long startTime, LogEnum logEnum) {
        String logName = getLogName(joinPoint, logEnum);
        AnalysisLog analysisLogger = AnalysisLogFactory.getAnalysisLogger(logName);
        long overTime = System.currentTimeMillis();
        long cost = overTime - startTime;
        if (cost > analysisLogConfig.getLongTime()) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("logType", LogEnum.RUN_LONG_TIME.getValue());
            map.put("method", logName);
            map.put("costTime", cost);
            analysisLogger.info(JSON.toJSONString(map));
        }
    }

    private void logSystemExInfo(AnalysisLog analysisLogger, String logName, Throwable exMsg) {

    }

}
