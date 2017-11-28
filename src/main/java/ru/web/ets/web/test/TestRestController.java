package ru.web.ets.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.web.ets.model.Test;
import ru.web.ets.service.TestService;

@Controller
public class TestRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    TestService testService;

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public Test get(int id) {
        log.info("get test {}", id);
        return testService.get(id);
    }
}
