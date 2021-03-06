package ru.web.ets.repository.mock;

import org.springframework.stereotype.Repository;
import ru.web.ets.model.QuestionForTest;
import ru.web.ets.model.Test;
import ru.web.ets.repository.TestRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ImMemoryTestRepositoryImpl implements TestRepository {

    private Map<Integer, Test> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        InMemoryQuestionRepositoryImpl repositoryQuestion = new InMemoryQuestionRepositoryImpl();
        //this.save(new Test(null, repositoryQuestion.getAll()));
    }

    @Override
    public Test save(Test Test, int userId) {
        if (Test.isNew()) {
            Test.setId(counter.incrementAndGet());
        }
        repository.put(Test.getId(), Test);
        return Test;
    }

    @Override
    public Test getTest(int id) {
        return repository.get(id);
    }

    @Override
    public Test deleteQuestion(int testid, int qid) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Test get(int id, int userId) {
        return null;
    }

    @Override
    public List<Test> getAll() {
        return null;
    }

    @Override
    public QuestionForTest save(Test test, QuestionForTest questionForTest, int userId) {
        return null;
    }
}
