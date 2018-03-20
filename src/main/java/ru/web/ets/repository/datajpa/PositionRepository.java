package ru.web.ets.repository.datajpa;

import ru.web.ets.model.forDocs.PositionInTheOrganization;

import java.util.List;

public interface PositionRepository {

    PositionInTheOrganization save(PositionInTheOrganization user);

    // false if not found
    boolean delete(int id);

    // null if not found
    PositionInTheOrganization get(int id);

    List<PositionInTheOrganization> getAll();
}
