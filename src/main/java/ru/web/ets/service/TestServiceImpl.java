package ru.web.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.ets.model.Test;
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
    public Test create(Test test) {
        return null;
    }

    @Override
    public Test get(int id) throws NotFoundException {
        return testRepository.getTest(id);
    }

    @Override
    public void update(Test test) {

    }
}
