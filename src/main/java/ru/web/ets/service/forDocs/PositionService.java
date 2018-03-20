package ru.web.ets.service.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.ets.model.forDocs.PositionInTheOrganization;
import ru.web.ets.repository.datajpa.PositionRepository;
import ru.web.ets.util.exception.NotFoundException;


import java.util.List;
import static ru.web.ets.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PositionService {

    @Autowired
    private PositionRepository repository;

    public void setRepository(PositionRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "organizations", allEntries = true)
    public PositionInTheOrganization create(PositionInTheOrganization student) {
        Assert.notNull(student, "student must not be null");
        return repository.save(student);
    }

    @CacheEvict(value = "organizations", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public PositionInTheOrganization get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable("organizations")
    public List<PositionInTheOrganization> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "organizations", allEntries = true)
    public void update(PositionInTheOrganization Student) {
        Assert.notNull(Student, "Organization must not be null");
        checkNotFoundWithId(repository.save(Student), Student.getId());
    }
}
