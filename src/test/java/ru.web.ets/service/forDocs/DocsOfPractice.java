package ru.web.ets.service.forDocs;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.service.datajpa.AbstractServiceTest;

public class DocsOfPractice extends AbstractServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    AdviserService adviserService;

    @Test
    public void getDocs()
    {
   //     ScientificAdviser scientificAdviser = jpaAdviserRepository.getByEmail("Еськин");
        ScientificAdviser adviser = adviserService.getByLastname("Еськин");
    }


}
