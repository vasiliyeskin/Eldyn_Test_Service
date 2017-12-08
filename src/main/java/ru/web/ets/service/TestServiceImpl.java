package ru.web.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.ets.model.TestAndQuestions;
import ru.web.ets.repository.TestRepository;
import ru.web.ets.util.exception.NotFoundException;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;

    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public TestAndQuestions create(TestAndQuestions testAndQuestions) {
        return null;
    }

    @Override
    public TestAndQuestions get(int id) throws NotFoundException {
        return testRepository.getTest(id);
    }

    @Override
    public void update(TestAndQuestions testAndQuestions) {

    }
}
