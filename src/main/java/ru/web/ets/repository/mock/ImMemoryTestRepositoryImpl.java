package ru.web.ets.repository.mock;

import org.springframework.stereotype.Repository;
import ru.web.ets.model.TestAndQuestions;
import ru.web.ets.repository.TestRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ImMemoryTestRepositoryImpl implements TestRepository {

    private Map<Integer, TestAndQuestions> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        InMemoryQuestionRepositoryImpl repositoryQuestion = new InMemoryQuestionRepositoryImpl();
        this.save(new TestAndQuestions(null, repositoryQuestion.getAll()));
    }

    @Override
    public TestAndQuestions save(TestAndQuestions testAndQuestions) {
        if (testAndQuestions.isNew()) {
            testAndQuestions.setId(counter.incrementAndGet());
        }
        repository.put(testAndQuestions.getId(), testAndQuestions);
        return testAndQuestions;
    }

    @Override
    public TestAndQuestions getTest(int id) {
        return repository.get(id);
    }

}
