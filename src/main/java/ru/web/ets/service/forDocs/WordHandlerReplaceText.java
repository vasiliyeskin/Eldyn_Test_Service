package ru.web.ets.service.forDocs;

import org.apache.poi.hwpf.HWPFDocument;
import ru.web.ets.model.forDocs.Student;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WordHandlerReplaceText {

    public static void ReplaceTextInWordFileAndSave(Student s) {
        WordReplaceText instance = new WordReplaceText(
                System.getenv("ETS_ROOT") + "\\wordFiles\\appTemplate.doc",
                "E:\\JAVA\\JAVA_EE\\TEMP\\wordfiles\\" + s.getTrainingDirection().getShortname() + "_" + s.getLastname() + ".doc");

        HWPFDocument doc = null;
        try {
            doc = instance.openDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    .append(s.getCurator().getFirstname()).append(".")
                    .append(s.getCurator().getMiddlename()).append(".");
            doc = instance.replaceText(doc, "curatorfio", builder.toString());
            doc = instance.replaceText(doc, "curatorPosition ", s.getCurator().getPosition().getPositionIO());

            builder.setLength(0);
            builder.append(s.getAdviser().getLastname()).append(" ")
                    .append(s.getAdviser().getFirstname()).append(".")
                    .append(s.getAdviser().getMiddlename()).append(".");
            doc = instance.replaceText(doc, "adviserfio", builder.toString());
            doc = instance.replaceText(doc, "adviserPosition ", s.getAdviser().getPosition().getPositionIO());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
            doc = instance.replaceText(doc, "place", s.getAdviser().getOrganization().getName());
            doc = instance.replaceText(doc, "startDate", (dateFormat.format(new Date())).toString());
            doc = instance.replaceText(doc, "endDate", (dateFormat.format(new Date())).toString());

            instance.saveDocument(doc);
        }
    }
}