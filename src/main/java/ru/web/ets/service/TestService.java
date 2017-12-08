package ru.web.ets.service;

import ru.web.ets.model.TestAndQuestions;
import ru.web.ets.util.exception.NotFoundException;

public interface TestService {


    TestAndQuestions create(TestAndQuestions testAndQuestions);

    TestAndQuestions get(int id) throws NotFoundException;

    void update(TestAndQuestions testAndQuestions);
}
