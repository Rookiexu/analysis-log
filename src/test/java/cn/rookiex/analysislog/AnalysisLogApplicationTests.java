package cn.rookiex.analysislog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AnalysisLogApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(AnalysisLogApplicationTests.class, args);
	}


}
