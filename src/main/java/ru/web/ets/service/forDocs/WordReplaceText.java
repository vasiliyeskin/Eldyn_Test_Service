package ru.web.ets.service.forDocs;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordReplaceText {
    private String sourceFile;
    private String outputFile;



    public static void main(String[] args) throws Exception {
        WordReplaceText instance = new WordReplaceText(
                "g:\\JAVA\\JAVA_EE\\Eldyn_Test_Service\\wordFiles\\appTemplate.doc",
                "g:\\JAVA\\JAVA_EE\\Eldyn_Test_Service\\wordFiles\\appTemplate_stud.doc");
        HWPFDocument doc = instance.openDocument();
        if (doc != null) {
            doc = instance.replaceText(doc, "$student$", "Фамилия Имя Отчество");
            instance.saveDocument(doc);
        }
    }


    public WordReplaceText(String SOURCE_FILE, String OUTPUT_FILE) {
        this.sourceFile = SOURCE_FILE;
        this.outputFile = OUTPUT_FILE;
    }

    public HWPFDocument replaceText(HWPFDocument doc, String findText, String replaceText) {
        Range r = doc.getRange();
        for (int i = 0; i < r.numSections(); ++i) {
            Section s = r.getSection(i);
            for (int j = 0; j < s.numParagraphs(); j++) {
                Paragraph p = s.getParagraph(j);
                for (int k = 0; k < p.numCharacterRuns(); k++) {
                    CharacterRun run = p.getCharacterRun(k);
                    String text = run.text();
                    if (text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                        run.setBold(true);
                        run.setUnderlineCode(1);
                        run.setColor(HSSFColor.RED.index);
                    }
                }
            }
        }
        return doc;
    }

    public HWPFDocument openDocument() throws Exception {
        File file = new File(sourceFile);
        HWPFDocument document = null;
        if (file != null) {
            document = new HWPFDocument(new POIFSFileSystem(file));
        }
        return document;
    }

    public void saveDocument(HWPFDocument doc) {
        try (FileOutputStream out = new FileOutputStream(outputFile)) {
            doc.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
