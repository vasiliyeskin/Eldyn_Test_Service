package ru.web.ets.repository;

import ru.web.ets.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    void delete(int id);

    Question get(int id);

    Collection<Question> getAll();

    Question save(Question question);

    void deleteAnswer(int id, int idAns);

    void createAnswer(int id, String text);
}
