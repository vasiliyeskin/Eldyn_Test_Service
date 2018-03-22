package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.forDocs.ScientificAdviser;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudAdviserRepository extends JpaRepository<ScientificAdviser, Integer> {
    @Transactional
    @Modifying
//    @Query(name = ScientificAdviser.DELETE)
    @Query("DELETE FROM ScientificAdviser sa WHERE sa.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    ScientificAdviser save(ScientificAdviser adviser);

    @Override
    Optional<ScientificAdviser> findById(Integer id);

    @Override
    List<ScientificAdviser> findAll(Sort sort);

    ScientificAdviser getByEmail(String email);

    //@Query("SELECT FROM ScientificAdviser sa WHERE sa.lastname=:lastname")
    ScientificAdviser getByLastname(String lastname);
}
