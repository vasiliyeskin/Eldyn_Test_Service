package ru.web.ets.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.QuestionForTest;
import ru.web.ets.model.Test;
import ru.web.ets.repository.TestRepository;

import java.util.List;

@Repository
public class DataJpaTestRepositoryImpl implements TestRepository {
    private static final Sort SORT_NAME = new Sort("text");

    @Autowired
    CrudTestRepository crudTestRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudQuestionForTest crudQuestionForTest;

    @Override
    public Test getTest(int id) {
        return crudTestRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public Test save(Test test, int userId) {
        if (!test.isNew() && get(test.getId(), userId) == null) {
            return null;
        }
        test.setCreator(crudUserRepository.getOne(userId));
        return crudTestRepository.save(test);
    }

    @Override
    public boolean delete(int id) {
        return crudTestRepository.delete(id) != 0;
    }

    @Override
    public Test get(int id, int userId) {
        return crudTestRepository.findById(id).filter(test -> test.getCreator().getId() == userId).orElse(null);
    }

    @Override
    public List<Test> getAll() {
        return crudTestRepository.findAll(SORT_NAME);
    }


    @Transactional
    public QuestionForTest save(Test test, QuestionForTest questionForTest, int userId) {
        if (!questionForTest.isNew() && get(questionForTest.getId(), userId) == null) {
            return null;
        }
        questionForTest.setCreator(crudUserRepository.getOne(userId));
        return crudQuestionForTest.save(questionForTest);
    }
}
