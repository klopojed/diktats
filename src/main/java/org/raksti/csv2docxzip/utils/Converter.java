package org.raksti.csv2docxzip.utils;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.P;
import org.docx4j.wml.Text;
import org.jetbrains.annotations.NotNull;
import org.raksti.csv2docxzip.model.SingleRow;

import javax.xml.bind.JAXBElement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts input lines to M$ Word documents.
 */
public class Converter {

    public byte[] convert(@NotNull SingleRow row) throws Docx4JException, IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        String text = row.getText();
        List<String> paragraphs = TextSplitter.extractParagraphs(text);

        try (InputStream resource = Converter.class.getResourceAsStream("/paraugs.docx")) {
            WordprocessingMLPackage wordPackage = WordprocessingMLPackage.load(resource);
            MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

            P template = findTemplate(mainDocumentPart);
            for (String paragraph : paragraphs) {
                P copy = XmlUtils.deepCopy(template);
                List<?> texts = findByType(copy, Text.class);
                if (texts.size() > 0) {
                    ((Text) (texts.get(0))).setValue(paragraph);
                }

                mainDocumentPart.getContent().add(copy);
            }

            ((ContentAccessor) (template.getParent())).getContent().remove(template);

            wordPackage.save(result);
        }

        return result.toByteArray();
    }

    private P findTemplate(Object object) {
        List<Object> paragraphs = findByType(object, P.class);
        for (Object p : paragraphs) {
            List<Object> texts = findByType(p, Text.class);

            for (Object text : texts) {
                Text content = (Text) text;
                if ("Paraugs".equals(content.getValue())) {
                    return (P) p;
                }
            }
        }

        throw new IllegalStateException("Failed to find templated part of the document");
    }

    private List<Object> findByType(Object object, Class<?> type) {
        List<Object> result = new ArrayList<>();

        if (object instanceof JAXBElement) {
            object = ((JAXBElement<?>) object).getValue();
        }

        if (object.getClass().equals(type)) {
            result.add(object);
        } else {
            if (object instanceof ContentAccessor) {
                List<?> children = ((ContentAccessor) object).getContent();
                for (Object child : children) {
                    result.addAll(findByType(child, type));
                }
            }
        }

        return result;
    }

}
