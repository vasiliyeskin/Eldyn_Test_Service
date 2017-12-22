package ru.web.ets.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.Question;
import ru.web.ets.repository.QuestionRepository;

import java.util.List;

@Repository
public class DataJpaQuestionRepositoryImpl implements QuestionRepository {
    private static final Sort SORT_NAME = new Sort("text");

    @Autowired
    CrudQuestionRepository crudQuestionRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudTeacherAnswerRepository crudTeacherAnswerRepository;

//    @Override
//    public Question getQuestion(int id) {
//        return crudQuestionRepository.findById(id).orElse(null);
//    }

    @Override
    @Transactional
    public Question save(Question question, int userId) {
        if (!question.isNew() && get(question.getId(), userId) == null) {
            return null;
        }
        question.setCreator(crudUserRepository.getOne(userId));
        return crudQuestionRepository.save(question);
    }

    @Override
    public boolean delete(int id) {
        return crudQuestionRepository.delete(id) != 0;
    }

    @Override
    public Question get(int id, int userId) {
        /*return crudQuestionRepository.findById(id).filter(q -> q.getCreator().getId() == userId).orElse(null);*/
        return crudQuestionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> getAll() {
        return crudQuestionRepository.findAll(SORT_NAME);
    }

    @Override
    public void deleteAnswer(int idAns) {
        crudTeacherAnswerRepository.delete(idAns);
    }

    @Override
    public void createAnswer(int id, String text) {
    }
}
