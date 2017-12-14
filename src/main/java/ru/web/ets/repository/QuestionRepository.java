package ru.web.ets.repository;

import ru.web.ets.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    boolean delete(int id);

    Question get(int id, int userId);

    Collection<Question> getAll();

    Question save(Question question, int userId);

    void deleteAnswer(int id, int idAns);

    void createAnswer(int id, String text);
}
