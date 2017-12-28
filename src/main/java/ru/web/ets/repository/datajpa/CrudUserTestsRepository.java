package ru.web.ets.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.UserTest;

import java.util.Optional;

public interface CrudUserTestsRepository extends JpaRepository<UserTest,Integer> {

    @Override
    @Transactional
    UserTest save(UserTest userTest);

    @Override
    Optional<UserTest> findById(Integer userTestid);
}
