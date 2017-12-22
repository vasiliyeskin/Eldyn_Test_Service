package ru.web.ets.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.Question;
import ru.web.ets.service.QuestionService;
import static ru.web.ets.QuestionTestData.*;
import static ru.web.ets.UserTestData.USER_ID;
import static ru.web.ets.UserTestData.ADMIN_ID;
import static ru.web.ets.UserTestData.TEACH_ID;

public class DataJpaQuestionServiceTest extends AbstractServiceTest {

    @Autowired
    protected QuestionService service;

    @Test
    public void create() throws Exception {
        Question question = new Question(null, "adfd", null, user);
        Question created = service.create(question, USER_ID );
        question.setId(created.getId());
        assertMatch(service.getAll(), question);
    }
}
