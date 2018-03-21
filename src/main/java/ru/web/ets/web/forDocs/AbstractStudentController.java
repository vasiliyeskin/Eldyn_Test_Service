package ru.web.ets.web.forDocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.model.forDocs.TrainingDirection;
import ru.web.ets.service.forDocs.AdviserService;
import ru.web.ets.service.forDocs.StudentService;
import ru.web.ets.service.forDocs.TrainingDirectionService;

import java.util.List;

public abstract class AbstractStudentController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StudentService service;

    @Autowired
    private AdviserService adviserService;

    @Autowired
    private TrainingDirectionService directionService;

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

    public void active(int id, boolean active)
    {
        log.info((active ? "enable " : "disable ") + id);
        service.active(id, active);
    }

    public ScientificAdviser getAdviser(Integer adivser_id)
    {
        return adviserService.get(adivser_id);
    }

    public TrainingDirection getTrainingDirection(Integer td_id)
    {
        return directionService.get(td_id);
    }
}
