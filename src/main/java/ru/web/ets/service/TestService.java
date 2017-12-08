package ru.web.ets.service;

import ru.web.ets.model.Test;
import ru.web.ets.util.exception.NotFoundException;

public interface TestService {


    Test create(Test testAndQuestions);

    Test get(int id) throws NotFoundException;

    void update(Test test);
}
