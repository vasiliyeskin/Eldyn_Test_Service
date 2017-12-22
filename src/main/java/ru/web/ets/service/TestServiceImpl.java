package ru.web.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.ets.model.Question;
import ru.web.ets.model.QuestionForTest;
import ru.web.ets.model.Test;
import ru.web.ets.repository.TestRepository;
import ru.web.ets.util.exception.NotFoundException;

import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;

    public void setRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Test create(Test test, int userId) {
        return testRepository.save(test, userId);
    }

    @Override
    public Test get(int id) throws NotFoundException {
        return testRepository.getTest(id);
    }

    @Override
    public void delete(int testid) {
        testRepository.delete(testid);
    }

    @Override
    public Test deleteQuestion(int testid, int qid) {
        return testRepository.deleteQuestion(testid, qid);
    }

    @Override
    public Test update(Test test, int userId) {
        return testRepository.save(test, userId);
    }

    @Override
    public List<Test> getAll() {
        return testRepository.getAll();
    }

    public QuestionForTest save(Test test, QuestionForTest questionForTest, int userId)
    {
        return testRepository.save(test, questionForTest, userId);
    }
}
