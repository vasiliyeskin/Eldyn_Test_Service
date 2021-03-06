package ru.web.ets.repository;

import ru.web.ets.model.QuestionForTest;
import ru.web.ets.model.Test;
import java.util.List;

public interface TestRepository {

    Test getTest(int id);

    Test save(Test test, int userId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Test get(int id, int userId);

    List<Test> getAll();

    QuestionForTest save(Test test, QuestionForTest questionForTest, int userId);

    Test deleteQuestion(int testid, int qid);
}
