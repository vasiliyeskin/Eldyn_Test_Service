package ru.web.ets.web.forDocs;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.web.ets.model.forDocs.Student;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/students")
public class AdminAjaxStudentController extends AbstractStudentController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("firstname") String firstname,
                               @RequestParam("midlename") String middlename,
                               @RequestParam("lastname") String lastname,
                               @RequestParam("course") Integer course,
                               @RequestParam("adviser") Integer adviserID,
                               @RequestParam("curator") Integer curatorID,
                               @RequestParam("trainingDirection") Integer tdID) {

        Student student = new Student(id, firstname, middlename, lastname, course, "", "");
        student.setAdviser(this.getAdviser(adviserID));
        student.setCurator(this.getAdviser(curatorID));
        student.setTrainingDirection(this.getTrainingDirection(tdID));
        if (student.isNew()) {
            super.create(student);
        } else {
            super.update(student, student.getId());
        }
    }

    @Override
    @PostMapping(value = "/{id}")
    public void active(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.active(id, enabled);
    }
}
