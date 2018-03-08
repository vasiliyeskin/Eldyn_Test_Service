package ru.web.ets.web.forDocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.service.forDocs.StudentService;

import java.util.List;

public abstract class AbstractStudentController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentService service;

    public List<Student> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Student get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Student create(Student student) {
        log.info("create {}", student);
        return service.create(student);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Student Student, int id) {
        log.info("update {} with id={}", Student, id);
        service.update(Student);
    }

    public Student getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
