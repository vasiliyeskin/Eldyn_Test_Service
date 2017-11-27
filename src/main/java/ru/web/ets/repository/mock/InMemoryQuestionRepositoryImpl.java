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
    public static AtomicInteger countAns = new AtomicInteger(0);

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

        List<Integer> listCorrect = new ArrayList<Integer>();
        listCorrect.add(0);

        final List<Question> Questions = Arrays.asList(
                new Question(counter.incrementAndGet(), "1+1", null, ans1),
                new Question(counter.incrementAndGet(), "1+2", null, ans2),
                new Question(counter.incrementAndGet(), "1+3", null, ans3),
                new Question(counter.incrementAndGet(), "1+4", null, ans4),
                new Question(counter.incrementAndGet(), "1+5", null, ans5),
                new Question(counter.incrementAndGet(), "1+6", null, ans6)
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
            repository.remove(id);
    }

    @Override
    public Question get(int id) {
        Question q = repository.get(id);
        return q;
    }

    @Override
    public void deleteAnswer(int idQuesiton, int idAns) {
        this.save(new Question(repository.get(idQuesiton), deleteAnswerFromList(idQuesiton, idAns)));
    }

    @Override
    public void createAnswer(int id, String text) {
        Question question = repository.get(id);
        List<Answer> answerList = question.getAnswerList();
        answerList.add(new Answer(countAns.incrementAndGet(), text, null));
        this.save(new Question(question, answerList));
    }

    private List<Answer> deleteAnswerFromList(int idQuesiton, int idAns)
    {
        List<Answer> answerList = new ArrayList<Answer>(repository.get(idQuesiton).getAnswerList());
        for (Iterator<Answer> iter = answerList.listIterator(); iter.hasNext(); ) {
            Answer a = iter.next();
            if (a.getId() == idAns) {
                iter.remove();
                break;
            }
        }
        return answerList;
    }
}










