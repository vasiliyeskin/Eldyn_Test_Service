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

    List<Student> getByAdviserId(Integer id);

    List<Student> getByCuratorId(Integer id);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT s FROM Student s WHERE s.curator.id=:curatorId AND s.trainingDirection.id=:trainingDirectionId AND s.course=:courseId")
    List<Student> getByCuratorIdAndTrainingDirectionIdAndCourseId(@Param("curatorId")Integer curatorId, @Param("trainingDirectionId") Integer trainingDirectionId, @Param("courseId") Integer courseId);
}
