package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.forDocs.Organization;
import ru.web.ets.repository.datajpa.OrganizationRepository;

import java.util.List;

@Repository
public class DataJpaOrganizationRepositoryImpl implements OrganizationRepository {

    private static final Sort SORT_NAME_EMAIL = new Sort("name");

    @Autowired
    private CrudOrganizationRepository crudOrganizationRepository;

    @Override
    public Organization save(Organization student) {
        return crudOrganizationRepository.save(student);
    }

    @Override
    public boolean delete(int id) {
        return crudOrganizationRepository.delete(id) != 0;
    }

    @Override
    public Organization get(int id) {
        return crudOrganizationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Organization> getAll() {
        return crudOrganizationRepository.findAll(SORT_NAME_EMAIL);
    }

}
