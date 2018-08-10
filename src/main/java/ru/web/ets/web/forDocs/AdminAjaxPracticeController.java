package ru.web.ets.web.forDocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.web.ets.model.forDocs.Practice;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.service.forDocs.PracticeService;
import ru.web.ets.service.forDocs.StudentServiceImpl;
import ru.web.ets.util.DateTimeUtil;

import java.time.LocalDate;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/practice")
public class AdminAjaxPracticeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PracticeService service;

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Practice> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Practice get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("nameDirection") String nameDirection,
                               @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
            ,
                               @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Practice practice = new Practice(id, name, nameDirection, startDate.atStartOfDay(), endDate.atStartOfDay());

        if (practice.isNew()) {
            log.info("create {}", practice);
            service.create(practice);
        } else {
            log.info("update {} with id={}", practice, practice.getId());
            service.update(practice);
        }
    }

    @PostMapping(value = "/studentsByCuratorIdCourseAndTD", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudentsByCuratorIdCourseAndTD(@RequestParam("curatorId") Integer curatorID,
                                                           @RequestParam("course") Integer course,
                                                           @RequestParam("trainingDirectionID") Integer trainingDirectionID)
    {
        return studentService.getByCuratorIdTDIdCourseId(curatorID, course, trainingDirectionID);
    }
}
