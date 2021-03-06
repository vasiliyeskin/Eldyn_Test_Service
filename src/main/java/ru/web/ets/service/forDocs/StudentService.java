package ru.web.ets.service.forDocs;

import ru.web.ets.model.forDocs.Student;
import ru.web.ets.util.exception.NotFoundException;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    void delete(int id) throws NotFoundException;

    Student get(int id) throws NotFoundException;

    Student getByEmail(String email) throws NotFoundException;

    void update(Student student);

    List<Student> getAll();

    void active(int id, boolean active);

    List<Student> getByAdviserId(Integer id);

    List<Student> getByCuratorId(Integer id);

    List<Student> getByCuratorIdTDIdCourseId(Integer curatorId, Integer trainingDirectionId, Integer courseId);
}
