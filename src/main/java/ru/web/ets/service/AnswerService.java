package ru.web.ets.service;

import ru.web.ets.model.Answer;
import ru.web.ets.util.exception.NotFoundException;
import java.util.Collection;

public interface AnswerService {


    Answer create(Answer answer, int userId);

    void delete(int id) throws NotFoundException;

    Answer get(int id, int userId) throws NotFoundException;

    void update(Answer a, int userId);

    Collection<Answer> getAll();
    }
