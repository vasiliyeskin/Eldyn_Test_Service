package ru.web.ets.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.Test;
import ru.web.ets.repository.TestRepository;

import java.util.List;

@Repository
public class DataJpaTestRepositoryImpl implements TestRepository {
    private static final Sort SORT_NAME = new Sort("text");

    @Autowired
    CrudTestRepository crudTestRepository;

    @Override
    public Test getTest(int id) {
        return crudTestRepository.findById(id).orElse(null);
    }

    @Override
    public Test save(Test test) {
        return crudTestRepository.save(test);
    }

    @Override
    public boolean delete(int id) {
        return crudTestRepository.delete(id) != 0;
    }

    @Override
    public Test get(int id) {
        return crudTestRepository.findById(id).orElse(null);
    }

    @Override
    public List<Test> getAll() {
        return crudTestRepository.findAll(SORT_NAME);
    }
}
