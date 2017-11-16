package ru.ets.repository;

import ru.ets.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    Collection<Question> getAll();
    Question save(Question question);
}
