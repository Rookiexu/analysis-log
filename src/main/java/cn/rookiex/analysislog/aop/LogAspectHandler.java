package cn.rookiex.analysislog.aop;

import cn.rookiex.analysislog.AnalysisLogFactory;
import cn.rookiex.analysislog.annotation.AnalysisLog;
import cn.rookiex.analysislog.data.AnalysisLogConfig;
import cn.rookiex.analysislog.enums.LogEnum;
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

import static cn.rookiex.analysislog.AnalysisLogFactory.LOG_NAME_SEPARATOR;

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

    @Around(value = "@annotation(AnalysisLog)")
    public Object before(ProceedingJoinPoint joinPoint, AnalysisLog AnalysisLog) {
        cn.rookiex.analysislog.AnalysisLog analysisLogger;
        long startTime = System.currentTimeMillis();
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            String exLogName = getLogName(joinPoint, LogEnum.SYSTEM_EX_LOG);
            analysisLogger = AnalysisLogFactory.getAnalysisLogger(exLogName);
            logSystemExInfo(analysisLogger, exLogName, throwable);
            throwable.printStackTrace();
        }

        if (AnalysisLog.logType().equals(LogEnum.RUN_LONG_TIME)) {
            String logName = getLogName(joinPoint, AnalysisLog.logType());
            analysisLogger = AnalysisLogFactory.getAnalysisLogger(logName);
            long overTime = System.currentTimeMillis();
            long cost = startTime - overTime;
            if (cost > analysisLogConfig.getLongTime())
                logRunTimeInfo(analysisLogger, logName, cost);
        }
        return proceed;
    }

    private String getLogName(ProceedingJoinPoint joinPoint, LogEnum runTimeLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String logTypeValue = runTimeLog.getValue();
        String annotationName = String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        return logTypeValue + LOG_NAME_SEPARATOR + annotationName;
    }

    private void logRunTimeInfo(cn.rookiex.analysislog.AnalysisLog analysisLogger, String logName, long cost) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("logType", LogEnum.RUN_LONG_TIME.getValue());
        map.put("method", logName);
        map.put("costTime", cost);
        analysisLogger.info(JSON.toJSONString(map));
    }

    private void logSystemExInfo(cn.rookiex.analysislog.AnalysisLog analysisLogger, String logName, Throwable exMsg) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("logType", LogEnum.SYSTEM_EX_LOG.getValue());
        map.put("method", logName);
        map.put("exMsg", exMsg.toString());
        analysisLogger.info(JSON.toJSONString(map));
    }
}
