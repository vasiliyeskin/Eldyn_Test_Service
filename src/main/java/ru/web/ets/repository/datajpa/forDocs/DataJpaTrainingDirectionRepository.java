package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.forDocs.TrainingDirection;

import java.util.List;

@Repository
public class DataJpaTrainingDirectionRepository{
    private static final Sort SORT_NAME = new Sort("id");

    @Autowired
    private CrudTrainingDirectionRepository crudTrainingDirectionRepository;

    public TrainingDirection save(TrainingDirection student) {
        return crudTrainingDirectionRepository.save(student);
    }

    public boolean delete(int id) {
        return crudTrainingDirectionRepository.delete(id) != 0;
    }

    public TrainingDirection get(int id) {
        return crudTrainingDirectionRepository.findById(id).orElse(null);
    }

    public List<TrainingDirection> getAll() {
        return crudTrainingDirectionRepository.findAll(SORT_NAME);
    }
}
