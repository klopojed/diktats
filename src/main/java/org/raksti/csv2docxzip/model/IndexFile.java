package org.raksti.csv2docxzip.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class IndexFile {
    //private String list = "";

    private final Workbook workbook = new XSSFWorkbook();
    private final Sheet sheet;
    private int index = 0;

    public IndexFile() {
        sheet = workbook.createSheet();
        addRow(Arrays.asList("id", "first names", "last name", "age", "country", "city", "e-mail", "phone", "language", "education"));
    }

    public void add(SingleRow singleRow) {
        //list += singleRow.getUuid() + "," + singleRow.getId() + "," + singleRow.getFirstNames() + ","
        //        + singleRow.getLastName() + "," + singleRow.getEmail() + "\n";
        addRow(Arrays.asList(Long.toString(singleRow.getId()), singleRow.getFirstNames(), singleRow.getLastName(), singleRow.getAge(),
                singleRow.getCountry(), singleRow.getCity(), singleRow.getEmail(), singleRow.getPhone(),
                singleRow.getLanguage(), singleRow.getEducation()));
    }

    public byte[] getBytes() throws IOException {
        //return list.getBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }

    private void addRow(List<String> values) {
        Row row = sheet.createRow(index++);
        int j = 0;
        for (String value : values) {
            Cell cell = row.createCell(j++);
            cell.setCellValue(value);
        }
    }
}
