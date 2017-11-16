package ru.ets.service;

import ru.ets.model.Question;
import java.util.Collection;

public interface QuestionService {

    Collection<Question> getAll();

}
