package ru.web.ets.service.forDocs;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.service.datajpa.AbstractServiceTest;

import static ru.web.ets.service.StudentTestData.*;

import static org.junit.Assert.*;

public class StudentServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected StudentService studentService;

    @Test
    public void create() {
        Student newstudent = new Student("new","new","new",2,"new@new.ru");
        Student created = studentService.create(newstudent);
        newstudent.setId(created.getId());
        assertMatch(studentService.getAll(), newstudent);
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void active() {
    }
}