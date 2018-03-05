package ru.web.ets.service.forDocs;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WordReplaceText {
    private String sourceFile;
    private String outputFile;



/*    public static void main(String[] args) throws Exception {
        String SOURCE_FILE = "lipsum.doc";
        String OUTPUT_FILE = "new-lipsum.doc";

        WordReplaceText instance = new WordReplaceText(
                "G:\\JAVA\\JAVA_EE\\Eldyn_Test_Service\\src\\main\\resources\\AppTemplate.docx",
                "G:\\JAVA\\JAVA_EE\\Eldyn_Test_Service\\src\\main\\resources\\AppTemplate_stud.docx");
        HWPFDocument doc = instance.openDocument();
        if (doc != null) {
            doc = instance.replaceText(doc, "$fio", "StudentFIO");
            instance.saveDocument(doc);
        }
    }*/


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
                    }
                }
            }
        }
        return doc;
    }

    public HWPFDocument openDocument() throws Exception {
        URL res = getClass().getClassLoader().getResource(sourceFile);
        HWPFDocument document = null;
        if (res != null) {
            document = new HWPFDocument(new POIFSFileSystem(
                    new File(res.getPath())));
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