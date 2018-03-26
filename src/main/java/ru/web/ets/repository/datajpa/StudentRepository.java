package ru.web.ets.repository.datajpa;

import ru.web.ets.model.forDocs.Student;

import java.util.List;

public interface StudentRepository {

        Student save(Student user);

        // false if not found
        boolean delete(int id);

        // null if not found
        Student get(int id);

        // null if not found
        Student getByEmail(String email);

        List<Student> getAll();

        List<Student> getByAdviserId(Integer id);

        List<Student> getByCuratorId(Integer id);

    List<Student> getByCuratorIdTDIdCourseId(Integer curatorId, Integer trainingDirectionId, Integer courseId);
}
