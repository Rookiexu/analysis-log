package cn.rookiex.analysislog;

import cn.rookiex.analysislog.annotation.analysisLog;
import cn.rookiex.analysislog.enums.LogEnum;
import org.springframework.stereotype.Component;

/**
 * @Author : Rookiex
 * @Date : 2019/08/31
 * @Describe :
 */
@Component
public class TestService {

    @analysisLog
    public void testRunTimeLog() throws InterruptedException {
        Thread.sleep(5 * 1000);
    }

    @analysisLog()
    public void testExRunTimeLog() throws Exception {
        Thread.sleep(5 * 1000);

        throw new Exception();
    }

    @analysisLog(logType = LogEnum.SYSTEM_EX_LOG)
    public void testExLog() throws Exception {
        Thread.sleep(5 * 1000);

        throw new Exception();
    }

    @analysisLog(logType = LogEnum.SYSTEM_EX_LOG)//只打印出现异常时的日志
    public void testExLogWithoutEx() throws InterruptedException {
        Thread.sleep(5 * 1000);

    }
}
