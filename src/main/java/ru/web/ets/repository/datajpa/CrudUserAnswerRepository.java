package ru.web.ets.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.UserAnswer;

import java.util.List;
import java.util.Optional;

public interface CrudUserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
        @Transactional
        @Modifying
        @Query("DELETE FROM UserAnswer a WHERE a.id=:id")
        int delete(@Param("id") int id);

        @Override
        @Transactional
        UserAnswer save(UserAnswer a);

        @Override
        Optional<UserAnswer> findById(Integer id);

        @Override
        List<UserAnswer> findAll(Sort sort);
    }
