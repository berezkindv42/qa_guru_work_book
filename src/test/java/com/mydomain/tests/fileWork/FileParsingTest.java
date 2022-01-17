package com.mydomain.tests.fileWork;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selectors.byText;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParsingTest {

    private ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void parsPdfTest() throws Exception {
        Selenide.open("https://junit.org/junit5/docs/current/user-guide/"); // открываем страницу
        File pdfDownload = Selenide.$(byText("PDF download")).download(); // скачиваем файл и кладем путь к нему в переменную типа File
        PDF parsedPDF = new PDF(pdfDownload); // создаем объект класса PDF (для этого в билд.гредле подключаем специальную библиотеку) и передаем в него нашу переменную с файлом
        assertThat(parsedPDF.author).contains("Marc Philipp"); // проверяем специальное для PDF формата поле author по средствам метода .author на содержание текста с именем автора
    }

    @Test
    void parsXlsFileTest() throws Exception {
//        InputStream stream = cl.getResourceAsStream("sample-xlsx-file.xlsx");//берем наш ранее созданный input stream, передаем в него наш xls файл и кладем все это в локальную переменную
        // НО так делать не надо потому, что стримы за собой надо закрывать посредствам конструкции try
        try (InputStream stream = cl.getResourceAsStream("files_Lesson_7/sample-xlsx-file.xlsx")) {
            XLS parsedXLS = new XLS(stream); // правильный код с закрытием стрима
            assertThat(parsedXLS.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue()).isEqualTo("Abril"); // проверяем содержание конкретной ячейки
                                                                                                                                         // последовательно выбирая таблицу, столбец и ячейку
        }
    }

    @Test
    void parseCsvFileTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("fileWorkLesson7Files/example.csv")) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream)); // создаем объект класса CSVReader и передаем в него созданный объект класса InputStreamReader, а в него передаем наш стрим
            // CSVReader - класс библиотеки openCSV ('com.opencsv:opencsv:5.5.2') предварительно подключенной в билд.гредле
            // CSVReader принемает на вход абстракцию Reader // Reader - это стандартный класс java, который может читать файлы
            List<String[]> list = reader.readAll(); // у нашего объекта reader вызываем метод .readAll(), который возвращает лист массивов строк
            assertThat(list)
                    .hasSize(3) // проверяем количество строк в листе
                    .contains(
                            new String[] {"Author", "Book"},
                            new String[] {"Block", "Apteka"},
                            new String[] {"Esenin", "Cherniy Chelovek"}
                    ); // проверяем содержание массивов по тексту // в ассерте создаем новые объекты класса String для того, что б было с чем сравнивать массивы из листа
        }
    }

    void zipTest() throws Exception {
        // для работы с zip архивами в джаве есть встроенный механизм, библиотеки подключат не потребуется
        try (InputStream stream = cl.getResourceAsStream("fileWorkLesson7Files/sample-zip-file.zip");
             ZipInputStream zis = new ZipInputStream(stream)) { // добавляем два стрима
            // у ZipInputStream() можно читать так называемые ZipEntry // ZipEntry - это упакованные zip-архивы, файлы или папки
            ZipEntry zipEntry; // объявляем переменную zipEntry
            while ((zipEntry = zis.getNextEntry()) != null) { // здесь происходит две вещи: 1. мы присваиваем в переменную zipEntry значение полученное их стрима
                                                                                         // 2. проверяем, что следующая ZipEntry не равна null
                                                                                         // то есть, покуда следующая ZipEntry не равна null она присваивается в zipEntry
                assertThat(zipEntry.getName()).isEqualTo("sample.txt"); // проверяем наличие в архиве файла сравнивая по тексту
            }
        }

        ZipFile zf = new ZipFile(new File(cl.getResource("fileWorkLesson7Files/sample-zip-file.zip").toURI()));

    }

}
