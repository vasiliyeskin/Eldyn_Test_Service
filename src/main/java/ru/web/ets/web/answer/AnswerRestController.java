package ru.web.ets.web.answer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.web.ets.AuthorizedUser;
import ru.web.ets.model.Answer;
import ru.web.ets.service.AnswerService;

import java.util.Collection;

@Controller
public class AnswerRestController {
    protected final Logger log = LoggerFactory.getLogger((getClass()));

    @Autowired
    private AnswerService service;

    public AnswerRestController(AnswerService service) {
        this.service = service;
    }


    public Collection<Answer> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Answer get(int id) {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public Answer create(Answer answer) {
        log.info("create {}", answer);
        return service.create(answer, AuthorizedUser.id());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Answer answer, int id) {
        log.info("update {} with id={}", answer, id);
        service.update(answer, AuthorizedUser.id());
    }

}
