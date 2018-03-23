package ru.web.ets.repository.datajpa.forDocs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.repository.datajpa.StudentRepository;

import java.util.List;

@Repository
public class DataJpaStudentRepositoryImpl implements StudentRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort("lastname");

    @Autowired
    private CrudStudentRepository crudRepository;

    @Override
    public Student save(Student student) {
        return crudRepository.save(student);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Student get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public Student getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    @Override
    public List<Student> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public List<Student> getByAdviserId(Integer id)
    {
        return crudRepository.getByAdviserId(id);
    }

    @Override
    public List<Student> getByCuratorId(Integer id)
    {
        return crudRepository.getByCuratorId(id);
    }

}
