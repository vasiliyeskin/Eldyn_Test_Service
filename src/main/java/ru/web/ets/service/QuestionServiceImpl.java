package ru.web.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.ets.model.Question;
import ru.web.ets.repository.QuestionRepository;
import ru.web.ets.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository repository;

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    public void setRepository(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question create(Question question, int userId) {
        return repository.save(question, userId);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public Question get(int id, int userId) throws NotFoundException {
        return repository.get(id, userId);
    }

    @Override
    public Question update(Question question, int userId) {
        return repository.save(question, userId);
    }

    @Override
    public void deleteAnswer(int id, int idAns) {
        repository.deleteAnswer(id, idAns);
    }

    @Override
    public void createAnswer(int id, String text) {
        repository.createAnswer(id, text);
    }
}