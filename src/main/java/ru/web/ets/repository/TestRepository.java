package ru.web.ets.repository;

import ru.web.ets.model.Test;
import java.util.List;

public interface TestRepository {

    Test getTest(int id);

    Test save(Test test);

    // false if not found
    boolean delete(int id);

    // null if not found
    Test get(int id);

    List<Test> getAll();
}
