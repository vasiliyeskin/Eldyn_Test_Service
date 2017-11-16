package ru.ets.web.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.ets.model.Question;
import ru.ets.service.QuestionService;

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

}
