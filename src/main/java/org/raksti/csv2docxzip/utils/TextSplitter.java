package org.raksti.csv2docxzip.utils;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Input row is a regular string that can contain HTML tags "p" and "em".
 * No other tags are supported.
 * Assumption is that rows are always valid as well as their HTML structure
 */
public class TextSplitter {
    public static class StyledChunk {
        public final boolean italic;
        public final String text;

        public StyledChunk(boolean italic, String text) {
            this.italic = italic;
            this.text = text;
        }
    }

    @NotNull
    public static List<String> extractParagraphs(@NotNull String text) {
        text = StringUtils.replace(text, "<p>", "\n");
        text = StringUtils.replace(text, "</p>", "\n");
        String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(text, "\n");
        return Arrays.stream(split).filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }

    @NotNull
    public static List<StyledChunk> extractItalic(@NotNull String text) {
        boolean initialItalic = text.toLowerCase().startsWith("<em>");
        List<StyledChunk> result = new ArrayList<>();
        String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(text, "<em>");
        for (String part: split) {
            String[] split1 = StringUtils.splitByWholeSeparatorPreserveAllTokens(part, "</em>");
            for (String chunk: split1) {
                result.add(new StyledChunk(initialItalic, chunk));
                initialItalic = !initialItalic;
            }
        }

        return result;
    }
}
