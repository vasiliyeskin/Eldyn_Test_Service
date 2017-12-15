package ru.web.ets.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.Answer;

import java.util.List;
import java.util.Optional;

public interface CrudAnswerRepository extends JpaRepository<Answer, Integer> {
    @Transactional
    @Modifying
//    @Query(name = Answer.DELETE)
    @Query("DELETE FROM Answer a WHERE a.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Answer save(Answer a);

    @Override
    Optional<Answer> findById(Integer id);

    @Override
    List<Answer> findAll(Sort sort);
}
