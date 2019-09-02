package cn.rookiex.analysislog;

import cn.rookiex.analysislog.annotation.AnalysisLog;
import cn.rookiex.analysislog.enums.LogEnum;
import org.springframework.stereotype.Component;

/**
 * @Author : Rookiex
 * @Date : 2019/08/31
 * @Describe :
 */
@Component
public class TestService {

    @AnalysisLog
    public void testTime() throws InterruptedException {
        Thread.sleep(5 * 1000);
    }

    @AnalysisLog(logType = LogEnum.SYSTEM_EX_LOG)
    public void testTime2() throws InterruptedException {
        Thread.sleep(5 * 1000);
    }
}
