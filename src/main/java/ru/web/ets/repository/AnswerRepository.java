package ru.web.ets.repository;

import ru.web.ets.model.Answer;
import java.util.Collection;

public interface AnswerRepository {

    boolean delete(int id);

    Answer get(int id, int userId);

    Collection<Answer> getAll();

    Answer save(Answer answer, int userId);

}
