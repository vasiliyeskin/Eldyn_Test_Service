package ru.web.ets.web.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.web.ets.AuthorizedUser;
import ru.web.ets.model.QuestionForTest;
import ru.web.ets.model.Test;
import ru.web.ets.service.TestService;

import java.util.List;

@Controller
public class TestRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestService testService;

    public Test get(int id) {
        log.info("get test {}", id);
        return testService.get(id);
    }

    public Test create(Test test) {
        return testService.create(test, AuthorizedUser.id());
    }

    public Test save(Test test) {
        log.info("update {} with id={}", test, test.getId());
        return testService.update(test, AuthorizedUser.id());
    }


    public List<Test> getAll() {
        return testService.getAll();
    }

    public QuestionForTest save(Test test, QuestionForTest questionForTest)
    {
        return testService.save(test, questionForTest, AuthorizedUser.id());
    }

    public void delete(int testid) {
        log.info("delete test with id={}", testid);
        testService.delete(testid);
    }

    public Test deleteQuestion(int testid, int qid) {
        log.info("delete question with id={} from test with id={}", qid, testid);
        return testService.deleteQuestion(testid, qid);
    }
}
