package ru.web.ets.web.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.web.ets.AuthorizedUser;
import ru.web.ets.model.Question;
import ru.web.ets.service.QuestionService;

import java.util.Collection;

@Controller
public class QuestionRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private QuestionService service;

    @Autowired
    public QuestionRestController(QuestionService service) {
        this.service = service;
    }

    public Collection<Question> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Question get(int id) {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public Question create(Question question) {
        log.info("create {}", question);
        return service.create(question, AuthorizedUser.id());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Question update(Question question, int id) {
        log.info("update {} with id={}", question, id);
        return service.update(question, AuthorizedUser.id());
    }

    public void deleteAnswer(int id, int idAns) {
        log.info("delete answer {} from question {}", idAns, id);
        service.deleteAnswer(id, idAns);
    }

    public void createAnswer(int id, String text) {
        log.info("create answer for question {}", id);
        service.createAnswer(id, text);
    }
}
