package com.tek.order;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("MyTestSuite")
@SelectClasses({
    com.example.demo.controller.OrderControllerTest.class,
    com.example.service.OrderServiceTest.class
})
public class TestSuite {
}
