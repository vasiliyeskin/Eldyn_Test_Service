package ru.web.ets.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.UserTest;

@Repository
public class UserTestsRepository {

    @Autowired
    CrudUserTestsRepository crudUserTestsRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    public UserTest getUserTest(int id) {
        UserTest userTest = crudUserTestsRepository.findById(id)
                .orElse(null);
        return userTest;
    }

    public UserTest save(UserTest userTest, int userId) {
        if (!userTest.isNew() && get(userTest.getId(), userId) == null) {
            return null;
        }
        userTest.setUser(crudUserRepository.getOne(userId));
        return crudUserTestsRepository.save(userTest);
    }

    public UserTest get(int id, int userId) {
        return crudUserTestsRepository.findById(id).filter(t -> t.getUser().getId() == userId).orElse(null);
    }
}
