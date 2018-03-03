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
}
