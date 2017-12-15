package ru.web.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.ets.model.Answer;
import ru.web.ets.repository.AnswerRepository;
import ru.web.ets.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Override
    public Answer create(Answer answer, int userId) {
        return repository.save(answer, userId);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Answer get(int id, int userId) throws NotFoundException {
        return repository.get(id, userId);
    }

    @Override
    public void update(Answer a, int userId) {
        repository.save(a, userId);
    }

    @Override
    public Collection<Answer> getAll() {
        return repository.getAll();
    }
}
