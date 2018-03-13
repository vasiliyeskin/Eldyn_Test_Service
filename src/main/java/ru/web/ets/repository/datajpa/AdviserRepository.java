package ru.web.ets.repository.datajpa;

import ru.web.ets.model.forDocs.ScientificAdviser;

import java.util.List;

public interface AdviserRepository {

    ScientificAdviser save(ScientificAdviser user);

    // false if not found
    boolean delete(int id);

    // null if not found
    ScientificAdviser get(int id);

    // null if not found
    ScientificAdviser getByEmail(String email);

    List<ScientificAdviser> getAll();
}