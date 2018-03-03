package ru.web.ets.service.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.repository.datajpa.StudentRepository;
import ru.web.ets.util.exception.NotFoundException;

import java.util.List;
import static ru.web.ets.util.ValidationUtil.checkNotFound;
import static ru.web.ets.util.ValidationUtil.checkNotFoundWithId;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    public void setRepository(StudentRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "students", allEntries = true)
    @Override
    public Student create(Student student) {
        Assert.notNull(student, "student must not be null");
        return repository.save(student);
    }

    @CacheEvict(value = "students", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Student get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Student getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("students")
    @Override
    public List<Student> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "students", allEntries = true)
    @Override
    public void update(Student Student) {
        Assert.notNull(Student, "Student must not be null");
        checkNotFoundWithId(repository.save(Student), Student.getId());
    }
}
