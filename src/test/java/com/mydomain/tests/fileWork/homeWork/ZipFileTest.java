package com.mydomain.tests.fileWork.homeWork;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFileTest {

    @Test
    void zipFileTest() throws Exception {

        ZipFile zipFile = new ZipFile("src/test/resources/fileWorkLesson7Files/zip_sample.zip");
        ZipEntry zipEntry;

        zipEntry = zipFile.getEntry("csv_sample.csv");
        try (InputStream stream = zipFile.getInputStream(zipEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list).hasSize(3).contains(
                    new String[] {"author", "j.r.r.tolkien"},
                    new String[] {"book","the lord of the rings"},
                    new String[] {"volume 1", "the fellowship of the ring"}
            );
        }

        zipEntry = zipFile.getEntry("xls_sample.xlsx");
        try (InputStream stream = zipFile.getInputStream(zipEntry)) {
            XLS parsedXLS = new XLS(stream);
            assertThat(parsedXLS.excel
                    .getSheetAt(0)
                    .getRow(4)
                    .getCell(1)
                    .getStringCellValue())
                    .isEqualTo("Kathleen");
        }

        zipEntry = zipFile.getEntry("pdf_sample.pdf");
        try (InputStream stream = zipFile.getInputStream(zipEntry)) {
            PDF parsedPDF = new PDF(stream);
            assertThat(parsedPDF.title).contains("JUnit 5 User Guide");
        }
    }
}
