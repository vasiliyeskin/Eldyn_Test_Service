package ru.web.ets.repository.mock;

import org.springframework.stereotype.Repository;
import ru.web.ets.model.Answer;
import ru.web.ets.model.Question;
import ru.web.ets.repository.QuestionRepository;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryQuestionRepositoryImpl implements QuestionRepository {
    private Map<Integer, Question> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private AtomicInteger countAns = new AtomicInteger(0);

    {
        final List<Answer> ans1 = Arrays.asList(
                new Answer(countAns.incrementAndGet(), "2", null),
                new Answer(countAns.incrementAndGet(), "3", null),
                new Answer(countAns.incrementAndGet(), "4", null)
        );
        final List<Answer> ans2 = Arrays.asList(
                new Answer(countAns.incrementAndGet(), "2", null),
                new Answer(countAns.incrementAndGet(), "3", null),
                new Answer(countAns.incrementAndGet(), "4", null)
        );
        final List<Answer> ans3 = Arrays.asList(
                new Answer(countAns.incrementAndGet(), "2", null),
                new Answer(countAns.incrementAndGet(), "3", null),
                new Answer(countAns.incrementAndGet(), "4", null)
        );
        final List<Answer> ans4 = Arrays.asList(
                new Answer(countAns.incrementAndGet(), "2", null),
                new Answer(countAns.incrementAndGet(), "3", null),
                new Answer(countAns.incrementAndGet(), "4", null)
        );
        final List<Answer> ans5 = Arrays.asList(
                new Answer(countAns.incrementAndGet(), "2", null),
                new Answer(countAns.incrementAndGet(), "3", null),
                new Answer(countAns.incrementAndGet(), "4", null)
        );
        final List<Answer> ans6 = Arrays.asList(
                new Answer(countAns.incrementAndGet(), "2", null),
                new Answer(countAns.incrementAndGet(), "3", null),
                new Answer(countAns.incrementAndGet(), "4", null)
        );


        final List<Question> Questions = Arrays.asList(
                new Question(counter.incrementAndGet(), "1+1", null, ans1, 0),
                new Question(counter.incrementAndGet(), "1+2", null, ans2, 0),
                new Question(counter.incrementAndGet(), "1+3", null, ans3, 0),
                new Question(counter.incrementAndGet(), "1+4", null, ans4, 0),
                new Question(counter.incrementAndGet(), "1+5", null, ans5, 0),
                new Question(counter.incrementAndGet(), "1+6", null, ans6, 0)
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

    @Override
    public void delete(int id) {
        Question m = repository.get(id);
            repository.remove(id);
    }

    @Override
    public Question get(int id) {
        Question q = repository.get(id);
        return q;
    }

    @Override
    public void deleteAnswer(int id, int idAns) {
        /*for (Answer a :repository.get(id).getAnswerList()
             ) {
            if(a.getId() == idAns)
            {
                repository.get(id).getAnswerList().remove(a);
                return;
            }
        };*/
    }
}










