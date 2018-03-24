package ru.web.ets.service.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.ets.model.forDocs.Practice;
import ru.web.ets.repository.datajpa.PracticeRepository;
import ru.web.ets.util.exception.NotFoundException;

import java.util.List;
import static ru.web.ets.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PracticeService {

    @Autowired
    private PracticeRepository repository;

    public void setRepository(PracticeRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "practices", allEntries = true)
    public Practice create(Practice practice) {
        Assert.notNull(practice, "practice must not be null");
        return repository.save(practice);
    }

    @CacheEvict(value = "practices", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Practice get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable("practices")
    public List<Practice> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "organizations", allEntries = true)
    public void update(Practice practice) {
        Assert.notNull(practice, "Organization must not be null");
        checkNotFoundWithId(repository.save(practice), practice.getId());
    }
}
