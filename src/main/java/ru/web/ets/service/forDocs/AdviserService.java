package ru.web.ets.service.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.repository.datajpa.AdviserRepository;
import ru.web.ets.util.exception.NotFoundException;

import static ru.web.ets.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

@Service
public class AdviserService {
    @Autowired
    private AdviserRepository repository;

    public void setRepository(AdviserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "advisers", allEntries = true)
    public ScientificAdviser create(ScientificAdviser adviser) {
        Assert.notNull(adviser, "Adviser must not be null");
        return repository.save(adviser);
    }

    @CacheEvict(value = "advisers", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public ScientificAdviser get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable("advisers")
    public List<ScientificAdviser> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "advisers", allEntries = true)
    public void update(ScientificAdviser Student) {
        Assert.notNull(Student, "Adviser must not be null");
        checkNotFoundWithId(repository.save(Student), Student.getId());
    }

    public ScientificAdviser getByLastname(String lastname)
    {
        return repository.getByLastname(lastname);
    }

    public List<ScientificAdviser> getCurator()
    {
        return repository.getByOrganizationId(2);
    }
}
