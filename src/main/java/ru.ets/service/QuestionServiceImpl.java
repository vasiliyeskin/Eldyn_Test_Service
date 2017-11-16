package ru.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ets.model.Question;
import ru.ets.repository.QuestionRepository;

import java.util.Collection;

@Service
public class QuestionServiceImpl implements QuestionService {


    @Autowired
    private QuestionRepository repository;

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }
}
