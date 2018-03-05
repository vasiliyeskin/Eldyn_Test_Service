package ru.web.ets.service.forDocs;

import org.apache.poi.hwpf.HWPFDocument;
import org.junit.Test;
import ru.web.ets.service.datajpa.AbstractServiceTest;

public class WordReplaceTextTest extends AbstractServiceTest {

    @Test
    public void replaceText() throws Exception {
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
    }
}