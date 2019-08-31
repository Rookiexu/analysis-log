package cn.rookiex.analysislog.aop;

import cn.rookiex.analysislog.AnalysisLog;
import cn.rookiex.analysislog.AnalysisLogFactory;
import cn.rookiex.analysislog.annotation.runTimeLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static cn.rookiex.analysislog.AnalysisLogFactory.LOG_NAME_PREFIX;
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
        AnalysisLog analysisLogger = AnalysisLogFactory.getAnalysisLogger(runTimeLog.logName());
        analysisLogger.info(runTimeLog.logType(),cost);
        return proceed;
    }

    private void getLogName(ProceedingJoinPoint joinPoint,runTimeLog runTimeLog){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String logTypeValue = runTimeLog.logType().getValue();
        Method method = signature.getMethod();
        String name = method.getName();

//        String logName = LOG_NAME_PREFIX + LOG_NAME_SEPARATOR + getName(logTypeValue, signature);

    }
}
