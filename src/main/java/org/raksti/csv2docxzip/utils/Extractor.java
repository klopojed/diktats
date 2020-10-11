package org.raksti.csv2docxzip.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.jetbrains.annotations.NotNull;
import org.raksti.csv2docxzip.model.SingleRow;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Extractor implements Iterator<SingleRow> {
    private final CSVReader csvReader;

    public Extractor(@NotNull InputStream inputStream) {
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(';')
                .withQuoteChar('\"')
                .build();

        this.csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream))
                .withCSVParser(csvParser)
                .withKeepCarriageReturn(true)
                .build();
    }

    @Override
    public boolean hasNext() {
        return csvReader.iterator().hasNext();
    }

    @Override
    public SingleRow next() {
        String[] next = csvReader.iterator().next();
        while (!next[0].matches("^\\d*$")) {
            System.out.println("Skipping id = " + next[0]);
            if (hasNext()) {
                next = csvReader.iterator().next();
            } else {
                throw new IllegalStateException("No more data");
            }
        }
        return new SingleRow(next);
    }

}
