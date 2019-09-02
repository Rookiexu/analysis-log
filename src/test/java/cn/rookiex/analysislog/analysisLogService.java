package cn.rookiex.analysislog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author : Rookiex
 * @Date : 2019/09/02
 * @Describe :
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnalysisLogApplicationTests.class)
public class analysisLogService {
    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Test
    public void testRunTimeLog() throws InterruptedException {
        testService.testRunTimeLog();
    }

    @Test
    public void testExRunTimeLog() throws Exception {
        testService.testExRunTimeLog();
    }
    @Test
    public void testExLog() throws Exception {
        testService.testExLog();
    }

    @Test
    public void testExLogWithoutEx() throws InterruptedException {
        testService.testExLogWithoutEx();
    }

    @Test
    public void testCountLog() throws InterruptedException {
        testService.testCountLog();
    }
}
