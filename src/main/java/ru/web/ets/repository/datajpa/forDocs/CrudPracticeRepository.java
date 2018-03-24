package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.forDocs.Practice;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudPracticeRepository extends JpaRepository<Practice,Integer> {
    @Transactional
    @Modifying
//    @Query(name = Practice.DELETE)
    @Query("DELETE FROM Practice p WHERE p.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Practice save(Practice practice);

    @Override
    Optional<Practice> findById(Integer id);

    @Override
    List<Practice> findAll(Sort sort);
}
