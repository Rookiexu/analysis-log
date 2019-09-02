package cn.rookiex.analysislog;

import cn.rookiex.analysislog.annotation.runTimeLog;
import org.springframework.stereotype.Component;

/**
 * @Author : Rookiex
 * @Date : 2019/08/31
 * @Describe :
 */
@Component
public class TestService {

    @runTimeLog
    public void testTime() throws InterruptedException {
        Thread.sleep(5 * 1000);
    }
}
