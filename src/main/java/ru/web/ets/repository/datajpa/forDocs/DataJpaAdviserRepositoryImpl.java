package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.repository.datajpa.AdviserRepository;

import java.util.List;

@Repository
public class DataJpaAdviserRepositoryImpl implements AdviserRepository {
    private static final Sort SORT_ID = new Sort("id");
    private static final Sort SORT_Lastname = new Sort("lastname");

    @Autowired
    private CrudAdviserRepository crudRepository;

    @Override
    public ScientificAdviser save(ScientificAdviser adviser) {
        return crudRepository.save(adviser);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public ScientificAdviser get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public ScientificAdviser getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<ScientificAdviser> getAll() {
        return crudRepository.findAll(SORT_Lastname);
    }

    @Override
    public ScientificAdviser getByLastname(String lastname) {
        return crudRepository.getByLastname(lastname);
    }

    @Override
    public List<ScientificAdviser> getByOrganizationId(Integer id) {
        return crudRepository.getByOrganizationId(id);
    }

    @Override
    public List<ScientificAdviser> getCurators(){return crudRepository.getCurators(SORT_Lastname);}
}
