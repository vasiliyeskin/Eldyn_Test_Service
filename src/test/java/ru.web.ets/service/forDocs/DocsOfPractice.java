package ru.web.ets.service.forDocs;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.forDocs.Practice;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.service.datajpa.AbstractServiceTest;

import java.util.List;

public class DocsOfPractice extends AbstractServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    AdviserService adviserService;

    @Autowired
    private PracticeService practiceService;

    @Test
    public void getDocs() {
        ScientificAdviser curator = adviserService.getByLastname("Еськин");
        List<Student> students = studentService.getByCuratorId(curator.getId());

        students.forEach(s -> WordHandlerReplaceText.ReplaceTextInWordFileAndSave(s));
    }

    @Test
    public void getDocsWithPractice() {
        List<Student> students2 = studentService.getByCuratorIdTDIdCourseId(6, 1, 3);
        Practice practice = practiceService.get(1);

        ScientificAdviser curator = adviserService.getByLastname("Еськин");
        List<Student> students = studentService.getByCuratorId(curator.getId());

        students.forEach(s -> WordHandlerReplaceText.ReplaceAndSaveTextInWordWithPractice(s, practice));
    }
}
