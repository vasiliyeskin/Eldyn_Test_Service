package ru.web.ets.service;

import ru.web.ets.model.Test;
import ru.web.ets.util.exception.NotFoundException;

import java.util.List;

public interface TestService {


    Test create(Test test, int userId);

    Test get(int id) throws NotFoundException;

    Test update(Test test, int userId);

    List<Test> getAll();
}
