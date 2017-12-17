package ru.web.ets.service;

import ru.web.ets.model.Question;
import ru.web.ets.util.exception.NotFoundException;

import java.util.Collection;

public interface QuestionService {

    Question create(Question question, int userId);

    void delete(int id) throws NotFoundException;

    Question get(int id, int userId) throws NotFoundException;

    Question update(Question question, int userId);

    Collection<Question> getAll();

    void deleteAnswer(int id, int idAns);

    void createAnswer(int id, String text);
}
