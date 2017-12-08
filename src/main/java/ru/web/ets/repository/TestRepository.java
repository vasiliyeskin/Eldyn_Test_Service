package ru.web.ets.repository;

import ru.web.ets.model.TestAndQuestions;

public interface TestRepository {

    TestAndQuestions getTest(int id);

    TestAndQuestions save(TestAndQuestions testAndQuestions);
}
