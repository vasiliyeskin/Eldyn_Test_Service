package ru.web.ets.repository.mock;

import org.springframework.stereotype.Repository;
import ru.web.ets.model.Test;
import ru.web.ets.repository.TestRepository;

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
    public Test save(Test Test) {
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

}
