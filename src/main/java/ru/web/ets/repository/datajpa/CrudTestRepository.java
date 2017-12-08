package ru.web.ets.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.Test;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudTestRepository extends JpaRepository<Test, Integer>{

    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Test t WHERE t.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Test save(Test test);

    @Override
    Optional<Test> findById(Integer id);

    List<Test> findAll(Sort sort);
}
