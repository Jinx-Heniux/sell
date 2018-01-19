package top.catalinali;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

	private static final Logger LOG = LoggerFactory.getLogger(LoggerTest.class);

	@Test
	public void contextLoads() {
		String name = "imooc";
		String password = "123456";
		LOG.debug("debug...");
		LOG.info("name: " + name + " ,password: " + password);
		LOG.info("name: {}, password: {}", name, password);
		LOG.error("error...");
		LOG.warn("warn...");
	}

}
