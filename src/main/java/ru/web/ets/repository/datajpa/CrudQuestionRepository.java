package ru.web.ets.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.Question;

import java.util.List;
import java.util.Optional;

public interface CrudQuestionRepository extends JpaRepository<Question, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Question t WHERE t.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Question save(Question q);

    @Override
    Optional<Question> findById(Integer id);

    @Override
    List<Question> findAll(Sort sort);
}
