package ru.web.ets.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.QuestionForTest;
import ru.web.ets.model.Test;

import java.util.List;
import java.util.Optional;

public interface CrudQuestionForTest extends JpaRepository<QuestionForTest, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM QuestionForTest qft WHERE qft.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    QuestionForTest save(QuestionForTest q);

    @Override
    Optional<QuestionForTest> findById(Integer id);

    List<QuestionForTest> findByTest(Test test);

    @Override
    List<QuestionForTest> findAll(Sort sort);
}

