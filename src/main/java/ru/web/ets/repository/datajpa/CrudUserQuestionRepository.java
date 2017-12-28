package ru.web.ets.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.UserQuestion;

import java.util.List;
import java.util.Optional;

public interface CrudUserQuestionRepository extends JpaRepository<UserQuestion, Integer> {
        @Transactional
        @Modifying
        @Query("DELETE FROM UserQuestion a WHERE a.id=:id")
        int delete(@Param("id") int id);

        @Override
        @Transactional
        UserQuestion save(UserQuestion a);

        @Override
        Optional<UserQuestion> findById(Integer id);

        @Override
        List<UserQuestion> findAll(Sort sort);
}
