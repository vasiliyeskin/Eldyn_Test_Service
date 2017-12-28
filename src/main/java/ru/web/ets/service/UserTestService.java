package ru.web.ets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.web.ets.model.UserTest;
import ru.web.ets.repository.datajpa.UserTestsRepository;

@Service
public class UserTestService {
    @Autowired
    private UserTestsRepository userTestsRepository;

    public UserTest get(int id) {
        return userTestsRepository.getUserTest(id);
    }

    public UserTest create(UserTest test, int userId) {
        return userTestsRepository.save(test, userId);
    }

    public UserTest update(UserTest userTest, int userId) {
        return userTestsRepository.save(userTest, userId);
    }
}
