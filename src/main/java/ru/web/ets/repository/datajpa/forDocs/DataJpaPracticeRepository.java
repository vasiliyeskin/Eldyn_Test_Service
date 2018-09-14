package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.forDocs.Practice;
import ru.web.ets.repository.datajpa.PracticeRepository;

import java.util.List;

@Repository
public class DataJpaPracticeRepository implements PracticeRepository {
    private static final Sort SORT_ID = new Sort("id");
    private static final Sort SORT_name = new Sort("name");

    @Autowired
    CrudPracticeRepository repository;

    @Override
    public Practice save(Practice practice) {
        return repository.save(practice);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Practice get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Practice> getAll() {
        return repository.findAll(SORT_name);
    }
}
