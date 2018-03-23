package ru.web.ets.service.forDocs;

import org.apache.poi.hwpf.HWPFDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.service.datajpa.AbstractServiceTest;

import java.util.List;

public class DocsOfPractice extends AbstractServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    AdviserService adviserService;

    @Test
    public void getDocs() throws Exception {
   //     ScientificAdviser scientificAdviser = jpaAdviserRepository.getByEmail("Еськин");
        ScientificAdviser curator = adviserService.getByLastname("Еськин");
        List<Student> students = studentService.getByCuratorId(curator.getId());
        Student s = students.get(0);

        WordReplaceText instance = new WordReplaceText(
                "g:\\JAVA\\JAVA_EE\\Eldyn_Test_Service\\wordFiles\\appTemplate.doc",
                "g:\\JAVA\\JAVA_EE\\Eldyn_Test_Service\\wordFiles\\" + s.getTrainingDirection().getShortname() + "_" + s.getLastname() + ".doc");
        HWPFDocument doc = instance.openDocument();
        if (doc != null) {
            doc = instance.replaceText(doc, "studentfio", s.getLastname() + " " + s.getMidlename() + " " + s.getFirstname());
            doc = instance.replaceText(doc, "studentshort", s.getLastname() + " " + s.getMidlename().substring(0,1) + "." + s.getFirstname().substring(0,1) + ".");
            instance.saveDocument(doc);
        }
    }


}
