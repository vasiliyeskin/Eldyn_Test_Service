package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.forDocs.PositionInTheOrganization;
import ru.web.ets.repository.datajpa.PositionRepository;

import java.util.List;

@Repository
public class DataJpaPositionRepository implements PositionRepository {

    private static final Sort SORT_ID = new Sort("id");
    private static final Sort SORT_positionIO = new Sort("positionIO");

    @Autowired
    private CrudPositionRepository crudPositionRepository;

    @Override
    public PositionInTheOrganization save(PositionInTheOrganization student) {
        return crudPositionRepository.save(student);
    }

    @Override
    public boolean delete(int id) {
        return crudPositionRepository.delete(id) != 0;
    }

    @Override
    public PositionInTheOrganization get(int id) {
        return crudPositionRepository.findById(id).orElse(null);
    }

    @Override
    public List<PositionInTheOrganization> getAll() {
        return crudPositionRepository.findAll(SORT_positionIO);
    }

}
