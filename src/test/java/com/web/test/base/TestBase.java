package com.web.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:applicationContext-redis.xml", "classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBase {

}
