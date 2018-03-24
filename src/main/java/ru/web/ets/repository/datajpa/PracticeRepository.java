package ru.web.ets.repository.datajpa;

import ru.web.ets.model.forDocs.Practice;
import java.util.List;

public interface PracticeRepository {
    Practice save(Practice practice);

    // false if not found
    boolean delete(int id);

    // null if not found
    Practice get(int id);

    List<Practice> getAll();
}
