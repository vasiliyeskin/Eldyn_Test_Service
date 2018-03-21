package ru.web.ets.service.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.ets.model.forDocs.TrainingDirection;
import ru.web.ets.repository.datajpa.forDocs.DataJpaTrainingDirectionRepository;
import ru.web.ets.util.exception.NotFoundException;

import java.util.List;

import static ru.web.ets.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TrainingDirectionService {
    @Autowired
    private DataJpaTrainingDirectionRepository repository;

    public void setRepository(DataJpaTrainingDirectionRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "td", allEntries = true)
    public TrainingDirection create(TrainingDirection adviser) {
        Assert.notNull(adviser, "Training Direction must not be null");
        return repository.save(adviser);
    }

    @CacheEvict(value = "td", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public TrainingDirection get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable("td")
    public List<TrainingDirection> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "td", allEntries = true)
    public void update(TrainingDirection Student) {
        Assert.notNull(Student, "Training Direction must not be null");
        checkNotFoundWithId(repository.save(Student), Student.getId());
    }
}
