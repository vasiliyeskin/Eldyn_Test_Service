package ru.web.ets.repository.datajpa.forDocs;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.forDocs.Organization;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudOrganizationRepository extends JpaRepository<Organization, Integer> {
    @Transactional
    @Modifying
//    @Query(name = Organization.DELETE)
    @Query("DELETE FROM Organization u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Organization save(Organization student);

    @Override
    Optional<Organization> findById(Integer id);

    @Override
    List<Organization> findAll(Sort sort);

}
