package org.raksti.csv2docxzip.utils;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * Unfortunately OpenCSV parser fails on our files. This one shouldn't.
 * Not many checks since the assumption is that file is always valid.
 */
public class CsvParser implements Iterator<String[]> {
    private final BufferedReader reader;
    private String line = null;

    public CsvParser(@NotNull InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public boolean hasNext() {
        if (line == null) {
            readLine();
        }
        return line != null;
    }

    @SuppressWarnings("StringConcatenationInLoop")
    private void readLine() {
        try {
            String read = "";
            while (!read.endsWith("\"") && !read.endsWith("\\t") && !read.endsWith(";")) {
                read = reader.readLine();
                if (read == null) {
                    return;
                }
                if (line == null) {
                    line = read;
                } else {
                    line += read + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override @Nullable
    public String[] next() {
        if (line == null) {
            readLine();
        }
        if (line == null) {
            return null;
        }
        String[] result = line.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < result.length; i++) {
            result[i] = StringUtils.removeStart(result[i], "\"");
            result[i] = StringUtils.removeEnd(result[i], "\"");
            if ("\\t".equals(result[i])) {
                result[i] = "";
            }
            if (i == 1) { // text
                result[i] = StringUtils.replace(result[i], "\"\"", "\"");
            }
        }
        line = null;
        return result;
    }
}
