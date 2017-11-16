package ru.ets.repository.mock;

import org.springframework.stereotype.Repository;
import ru.ets.model.Question;
import ru.ets.repository.QuestionRepository;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryQuestionRepositoryImpl implements QuestionRepository {
    private Map<Integer, Question> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        final List<Question> Questions = Arrays.asList(
                new Question(counter.incrementAndGet(), "1+1", null, null, 0),
                new Question(counter.incrementAndGet(), "1+2", null, null, 0),
                new Question(counter.incrementAndGet(), "1+3", null, null, 0),
                new Question(counter.incrementAndGet(), "1+4", null, null, 0),
                new Question(counter.incrementAndGet(), "1+5", null, null, 0),
                new Question(counter.incrementAndGet(), "1+6", null, null, 0)
        );

        Questions.forEach(this::save);
    }

    @Override
    public Question save(Question question) {
            if (question.isNew()) {
                question.setId(counter.incrementAndGet());
            }
            repository.put(question.getId(), question);
            return question;
    }

    @Override
    public Collection<Question> getAll() {
        return repository.values().stream().
                collect(Collectors.toList());
    }
}
