package ru.web.ets.repository.datajpa.forDocs;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.forDocs.PositionInTheOrganization;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudPositionRepository extends JpaRepository<PositionInTheOrganization,Integer> {
    @Transactional
    @Modifying
//    @Query(name = PositionInTheOrganization.DELETE)
    @Query("DELETE FROM PositionInTheOrganization u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    PositionInTheOrganization save(PositionInTheOrganization student);

    @Override
    Optional<PositionInTheOrganization> findById(Integer id);

    @Override
    List<PositionInTheOrganization> findAll(Sort sort);
}
