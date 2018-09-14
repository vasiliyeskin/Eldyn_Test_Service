package ru.web.ets.service.forDocs;

import org.apache.poi.hwpf.HWPFDocument;
import ru.web.ets.model.forDocs.Practice;
import ru.web.ets.model.forDocs.ScientificAdviser;
import ru.web.ets.model.forDocs.Student;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WordHandlerReplaceText {

    public static void ReplaceTextInWordFileAndSave(Student s) {
        WordReplaceText instance = new WordReplaceText(
                System.getenv("ETS_ROOT") + getTemplateForStudentAdviserFromUNN(s),
                System.getenv("ETS_ROOT") + "/TEMP/wordfiles/" + s.getTrainingDirection().getShortname() + "_" + s.getCourse() + "_" + s.getLastname() + ".doc");

        HWPFDocument doc = null;
        try {
            doc = instance.openDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        doc = replaceTextInDoc(doc, instance, s);
        instance.saveDocument(doc);
    }

    public static void ReplaceAndSaveTextInWordWithPractice(Student s, Practice practice) {
        WordReplaceText instance = new WordReplaceText(
                System.getenv("ETS_ROOT") + getTemplateForStudentAdviserFromUNN(s),
                System.getenv("ETS_ROOT") + "/TEMP/wordfiles/" + s.getTrainingDirection().getShortname() + "_" + s.getCourse() + "_" + s.getLastname() + ".doc");

        HWPFDocument doc = null;
        try {
            doc = instance.openDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        doc = replaceTextInDoc(doc, instance, s);
        doc = instance.replaceText(doc, "practice", practice.getNameDirection());
        doc = instance.replaceText(doc, "PRACTICENAME", practice.getName().toLowerCase());
        doc = instance.replaceText(doc, "PRACTICEROD", practice.getNameRod().toLowerCase());

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        doc = instance.replaceText(doc, "startDate", (dateFormat.format(practice.getStartDate().toLocalDate())));
        doc = instance.replaceText(doc, "endDate", (dateFormat.format(practice.getEndDate().toLocalDate())));

        instance.saveDocument(doc);
    }

    private static String getTemplateForStudentAdviserFromUNN(Student s)
    {
        if(isStudentAdviserFromUNN(s))
            return "/wordFiles/appTemplate_adviser_unn.doc";

        return "/wordFiles/appTemplate.doc";
    }

    private static boolean isStudentAdviserFromUNN(Student s)
    {
        return s.getAdviser().getOrganization().getShortname().toUpperCase().contains("ННГУ");
    }


    private static HWPFDocument replaceTextInDoc(HWPFDocument doc, WordReplaceText instance, Student s) {
        if (doc != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(s.getLastname()).append(" ")
                    .append(s.getFirstname()).append(" ").append(s.getMidlename());
            doc = instance.replaceText(doc, "studentfio", builder.toString());

            doc = instance.replaceText(doc, "course", s.getCourse().toString());

            doc = instance.replaceText(doc, "td", s.getTrainingDirection().getName());

            builder.setLength(0);
            builder.append(s.getLastname()).append(" ")
                    .append(s.getFirstname().substring(0, 1)).append(".")
                    .append(s.getMidlename().substring(0, 1)).append(".");
            doc = instance.replaceText(doc, "studentshort", builder.toString());

            builder.setLength(0);
            builder.append(s.getCurator().getLastname()).append(" ")
                    .append(s.getCurator().getFirstname().substring(0, 1)).append(".")
                    .append(s.getCurator().getMiddlename().substring(0, 1)).append(".");
            doc = instance.replaceText(doc, "curatorshort", builder.toString());

            builder.setLength(0);
            builder.append(s.getAdviser().getLastname()).append(" ")
                    .append(s.getAdviser().getFirstname().substring(0, 1)).append(".")
                    .append(s.getAdviser().getMiddlename().substring(0, 1)).append(".");
            doc = instance.replaceText(doc, "advisershort", builder.toString());

            builder.setLength(0);
            builder.append(s.getCurator().getLastname()).append(" ")
                    .append(s.getCurator().getFirstname()).append(" ")
                    .append(s.getCurator().getMiddlename()).append(" ");
            doc = instance.replaceText(doc, "curatorfio", builder.toString());
            doc = instance.replaceText(doc, "curatorPosition ", s.getCurator().getPosition().getPositionIO());

            builder.setLength(0);
            builder.append(s.getAdviser().getLastname()).append(" ")
                    .append(s.getAdviser().getFirstname()).append(" ")
                    .append(s.getAdviser().getMiddlename()).append(" ");
            doc = instance.replaceText(doc, "adviserfio", builder.toString());
            doc = instance.replaceText(doc, "adviserPosition ", s.getAdviser().getPosition().getPositionIO());

//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
            doc = instance.replaceText(doc, "place", s.getAdviser().getOrganization().getName());
//            doc = instance.replaceText(doc, "startDate", (dateFormat.format(new Date())).toString());
//            doc = instance.replaceText(doc, "endDate", (dateFormat.format(new Date())).toString());
        }

        return doc;
    }

    public static String createStudentDocsAndZipper(List<Student> students, Practice practice, ScientificAdviser curator) throws IOException {
        String filename = curator.getLastname() + ".zip";
        FileOutputStream fos = new FileOutputStream(System.getenv("ETS_ROOT") + "/TEMP/wordfiles/" + filename);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (Student s : students) {
            ReplaceAndSaveTextInWordWithPractice(s, practice);
            File fileToZip = new File(System.getenv("ETS_ROOT") + "/TEMP/wordfiles/" + s.getTrainingDirection().getShortname() + "_" + s.getCourse() + "_" + s.getLastname() + ".doc");
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();

        return filename;
    }
}
