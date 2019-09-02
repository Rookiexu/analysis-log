package cn.rookiex.analysislog.aop;

import cn.rookiex.analysislog.AnalysisLog;
import cn.rookiex.analysislog.AnalysisLogFactory;
import cn.rookiex.analysislog.annotation.runTimeLog;
import cn.rookiex.analysislog.data.AnalysisLogConfig;
import cn.rookiex.analysislog.enums.LogEnum;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

    AnalysisLogConfig analysisLogConfig;

    @Autowired
    public void setAnalysisLogConfig(AnalysisLogConfig analysisLogConfig) {
        this.analysisLogConfig = analysisLogConfig;
    }

    @Around(value = "@annotation(runTimeLog)")
    public Object before(ProceedingJoinPoint joinPoint, runTimeLog runTimeLog) {
        long startTime = System.currentTimeMillis();
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long overTime = System.currentTimeMillis();
        long cost = startTime - overTime;
        String logName = getLogName(joinPoint, runTimeLog);
        AnalysisLog analysisLogger = AnalysisLogFactory.getAnalysisLogger(logName);
        if (cost > analysisLogConfig.getLongTime())
            logRunTimeInfo(analysisLogger, logName, cost);
        return proceed;
    }

    private String getLogName(ProceedingJoinPoint joinPoint, runTimeLog runTimeLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String logTypeValue = runTimeLog.logType().getValue();
        String annotationName = String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        return logTypeValue + LOG_NAME_SEPARATOR + annotationName;
    }

    private void logRunTimeInfo(AnalysisLog analysisLogger, String logName, long cost) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("logType", LogEnum.RUN_LONG_TIME.getValue());
        map.put("method", logName);
        map.put("costTime", cost);
        analysisLogger.info(JSON.toJSONString(map));
    }
}
