package ru.web.ets.service;

import ru.web.ets.model.Question;
import ru.web.ets.util.exception.NotFoundException;

import java.util.Collection;

public interface QuestionService {

    Question create(Question question);

    void delete(int id) throws NotFoundException;

    Question get(int id) throws NotFoundException;

    void update(Question question);

    Collection<Question> getAll();

}
