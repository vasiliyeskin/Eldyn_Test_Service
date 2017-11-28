package ru.web.ets.repository;

import ru.web.ets.model.Test;

public interface TestRepository {

    Test getTest(int id);

    Test save(Test test);
}
