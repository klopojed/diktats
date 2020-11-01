package org.raksti.csv2docxzip.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.raksti.csv2docxzip.model.SingleRow;

import java.io.InputStream;
import java.util.Iterator;

public class Extractor implements Iterator<SingleRow> {
    private final CsvParser csvParser;

    public Extractor(@NotNull InputStream inputStream) {
        this.csvParser = new CsvParser(inputStream);
    }

    @Override
    public boolean hasNext() {
        return csvParser.hasNext();
    }

    @Override @Nullable
    public SingleRow next() {
        String[] next = csvParser.next();
        while (next == null || next.length < 24 || !next[0].matches("^\\d*$")) {
            if (hasNext()) {
                next = csvParser.next();
            } else {
                return null;
            }
        }
        return new SingleRow(next);
    }

}
