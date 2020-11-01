package org.raksti.csv2docxzip.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TextSplitterTest {

    @Test
    public void testParagraphs() {
        String line = "one\ntwo<p>three</p>\nfour\n\nfive<p></p>";

        List<String> result = TextSplitter.extractParagraphs(line);

        assertEquals("one|two|three|four|five", StringUtils.join(result, "|"));
    }

    @Test
    public void testItalic() {
        String line = "one<em>two</em>three<em>four</em> ";

        List<TextSplitter.StyledChunk> styledChunks = TextSplitter.extractItalic(line);

        assertEquals(5, styledChunks.size());
        assertEquals("one", styledChunks.get(0).text);
        assertFalse(styledChunks.get(0).italic);
        assertEquals("two", styledChunks.get(1).text);
        assertTrue(styledChunks.get(1).italic);
        assertEquals("three", styledChunks.get(2).text);
        assertFalse(styledChunks.get(2).italic);
        assertEquals("four", styledChunks.get(3).text);
        assertTrue(styledChunks.get(3).italic);
        assertEquals(" ", styledChunks.get(4).text);
        assertFalse(styledChunks.get(4).italic);
    }

}