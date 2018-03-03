package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.web.ets.model.forDocs.Student;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudStudentRepository extends JpaRepository<Student, Integer> {
    @Transactional
    @Modifying
//    @Query(name = Student.DELETE)
    @Query("DELETE FROM Student u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Student save(Student student);

    @Override
    Optional<Student> findById(Integer id);

    @Override
    List<Student> findAll(Sort sort);

    Student getByEmail(String email);
}
