package org.raksti.csv2docxzip;

import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.raksti.csv2docxzip.model.IndexFile;
import org.raksti.csv2docxzip.model.SingleRow;
import org.raksti.csv2docxzip.utils.Converter;
import org.raksti.csv2docxzip.utils.Extractor;
import org.raksti.csv2docxzip.utils.Zipper;

import java.io.*;

public class Controller {
    public static int EXPECTED_FIELDS_COUNT = 28;

    public void process(@NotNull File file) throws Exception {

        Converter converter = new Converter();
        IndexFile indexFile = new IndexFile();

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
             Zipper zipper = new Zipper(new FileOutputStream(FilenameUtils.removeExtension(file.getAbsolutePath()) + ".zip"))) {
            Extractor extractor = new Extractor(inputStream);

            while (extractor.hasNext()) {
                SingleRow row = extractor.next();
                if (row != null) {
                    zipper.add("" + row.getId() + ".docx", converter.convert(row));
                    System.out.println("Added: " + row.getId() + ".docx");
                    indexFile.add(row);
                } else {
                    break;
                }
            }

            zipper.add("index.xlsx", indexFile.getBytes());
        }
    }

}
