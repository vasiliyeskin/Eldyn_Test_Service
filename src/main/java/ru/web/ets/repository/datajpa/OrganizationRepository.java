package ru.web.ets.repository.datajpa;

import ru.web.ets.model.forDocs.Organization;

import java.util.List;

public interface OrganizationRepository {

    Organization save(Organization user);

    // false if not found
    boolean delete(int id);

    // null if not found
    Organization get(int id);

    List<Organization> getAll();
}
