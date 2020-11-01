package org.raksti.csv2docxzip.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CsvParserTest {

    @Test
    public void testParser() {
        String input1 = "\n\"2\";\"Rudens rīts aug\n silts un valgs. \";\"name1 \";\"surname1\";\"n1s1@hotmail.com\";\"23\";\"1\";\"\";\"0\";\"3\";\"1\";\"0\";\"land1\";\"city1\";\"1\";\"0\";\"2019-11-09 12:18:30\";\"2019-11-09 12:18:30\";\\t;\\t;\"0037126661642\";\"m\";\"Latviešu\";\"Augstākā izglītība\";\"Bakalaurs\"\n";
        String input2 = "\"7\";\"Sēņu diktāts.\n" +
                "Rudens rīts aust silts un valgs. Celdamās virs meža, klusi kūp migla. \";\"name2\";\"surname2\";\"n2s2@inbox.lv\";\"43\";\"1\";\"\";\"0\";\"2\";\"0\";\"0\";\"Latvija\";\"Ventspils\";\"1\";\"0\";\"2019-11-09 12:21:25\";\"2019-11-09 12:21:25\";\\t;\\t;\"26518572\";\"f\";\"Latviešu\";\"Vidējā izglītība\";\\t\n";

        InputStream inputStream = new ByteArrayInputStream((input1 + input2).getBytes());

        CsvParser csvParser = new CsvParser(inputStream);

        assertTrue(csvParser.hasNext());
        System.out.println(Arrays.toString(csvParser.next()));
        assertTrue(csvParser.hasNext());
        System.out.println(Arrays.toString(csvParser.next()));
        assertFalse(csvParser.hasNext());
    }

}