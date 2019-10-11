package org.tony.transaction.tcc.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class TestBase extends AbstractJUnit4SpringContextTests {

	static {
		System.setProperty("mb.environment", "test");
	}

	public void waitThread(int m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
