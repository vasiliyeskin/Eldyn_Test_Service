package ru.web.ets.web.forDocs;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.web.ets.model.forDocs.Practice;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.model.forDocs.Student;
import ru.web.ets.service.forDocs.AdviserService;
import ru.web.ets.service.forDocs.PracticeService;
import ru.web.ets.service.forDocs.StudentService;
import ru.web.ets.service.forDocs.WordHandlerReplaceText;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/curator")
public class AdminAjaxCuratorController {

    @Autowired
    private AdviserService service;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PracticeService practiceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScientificAdviser> getAll() {
        return service.getCurator();
    }


    @PostMapping(value = "/getDocsForCurator")
    public String getDocsForCurator(@RequestParam("practice") Integer practiceId,
                                    @RequestParam("curator") Integer curatorId,
                                    @RequestParam("trainingDirection") Integer trainingDirectionId,
                                    @RequestParam("course") Integer courseId) throws IOException {

        List<Student> students = studentService.getByCuratorIdTDIdCourseId(curatorId, trainingDirectionId, courseId);
        Practice practice = practiceService.get(practiceId);
        ScientificAdviser curator = service.get(curatorId);

        String status = WordHandlerReplaceText.createStudentDocsAndZipper(students, practice, curator);

        return status;
    }

    //@RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = "application/force-download")
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String filename) throws IOException {

        File file = new File(System.getenv("ETS_ROOT") + "/TEMP/wordfiles/" + filename);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/force-download"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + file.getName());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        headers.setContentLength(file.length());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(new FileInputStream(file)), headers, HttpStatus.OK);
        return response;
    }
}