package ru.web.ets.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.Answer;
import ru.web.ets.repository.AnswerRepository;
import java.util.List;

@Repository
public class DataJpaAnswerRepositoryImpl implements AnswerRepository {
    private static final Sort SORT_NAME = new Sort("text");


    @Autowired
    CrudAnswerRepository crudAnswerRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;


    @Override
    @Transactional
    public Answer save(Answer answer, int userId) {
        if (!answer.isNew() && get(answer.getId(), userId) == null) {
            return null;
        }
        answer.setCreator(crudUserRepository.getOne(userId));
        return crudAnswerRepository.save(answer);
    }

    @Override
    public boolean delete(int id) {
        return crudAnswerRepository.delete(id) != 0;
    }

    @Override
    public Answer get(int id, int userId) {
        return crudAnswerRepository.findById(id).filter(a -> a.getCreator().getId() == userId).orElse(null);
    }

    @Override
    public List<Answer> getAll() {
        return crudAnswerRepository.findAll(SORT_NAME);
    }
}
