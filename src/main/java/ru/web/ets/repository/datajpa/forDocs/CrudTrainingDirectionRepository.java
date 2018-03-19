package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.forDocs.TrainingDirection;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudTrainingDirectionRepository extends JpaRepository<TrainingDirection, Integer> {

    @Transactional
    @Modifying
//    @Query(name = Organization.DELETE)
    @Query("DELETE FROM TrainingDirection u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    TrainingDirection save(TrainingDirection student);

    @Override
    Optional<TrainingDirection> findById(Integer id);

    @Override
    List<TrainingDirection> findAll(Sort sort);

}
