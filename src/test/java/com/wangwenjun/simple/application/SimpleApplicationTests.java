package com.wangwenjun.simple.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SimpleApplication.class)
@ComponentScan("com.wangwenjun.simple.application")
@ActiveProfiles("test")
public class SimpleApplicationTests {

  @Test
  public void contextLoads() {
  }
}
