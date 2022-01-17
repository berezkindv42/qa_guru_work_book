package com.mydomain.tests.fileWork;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {

    // добавить класс лоадер можно следующими способами:
    private ClassLoader cl = SelenideFilesTest.class.getClassLoader(); // мы получим тот класс лоадер, которым был загружен данный класс
//    private ClassLoader cl = getClass().getClassLoader(); // getClass() - это метод, который есть во всех классах за счет того, что они наследуются от класса Object
//    private static ClassLoader cl = getClass().getClassLoader(); // если нам нужен здесь статический лоадер, метод getClass() использовать не получится потому, что метод не статический
    // Объект cl (класс лоадер) это тот самый механизм джавы, который ищет файлы по пути по умолчанию (в src/test/java, в src/test/resources, так же в src/main/java и в src/main/resources)

    @Test
//    void downloadTest() {
//        try {
//            File downloadedFile = $("#raw-url").download();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } // в тестах try/catch быть не должно(!) это может привести к тому, что даже упавшие тесты будут зелеными
//    }

    void downloadTest() throws Exception { // throws Exception заставляет Junit обработать исключения
        // это все работает, если в кнопке для скачивания файла в коде страницы есмть href
        open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");
        File downloadedFile = $("#raw-url").download(); // скачиваем файл по указанному id, получаем путь к нему и помещаем в объект класса File
//        new FileReader(downloadedFile); // ридер из скачанного файла.
        try (InputStream is = new FileInputStream(downloadedFile)) { // создаем из полученного объекта InputStream (передаем в конструктор переменную с объектом File)
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8)) // из стрима читаем все байты в кодировне UTF-8 и создаем из этих байтов строку
                    .contains("Eclipse Public License - v 2.0"); // проверяем, что в полученной строке есть искомый текст
            // Любой файл - это массив байт // метод .readAllBytes возвращает нам массив байт
            // Stream - это поток байт, из которых состоит файл
            // InputStream - это чтение байт из какого-то файла
            // OutputStream - это запись каких-то байт в какой-то файл
        }
    }

    void downloadTest2() throws Exception {
        Configuration.proxyEnabled = true; // эсли в коде кнопки для скачки файла на странице нет хрефа (href)
        Configuration.fileDownload = FileDownloadMode.PROXY; // эти две строки пускают весь трафик между тестом и браузеров через прокси, селенид видит, что трафик пошел через прокси, заберет файл оттуда и даст его нам
        // но всегда так делать не стоит, ибо это уменьшает стабильность тестов, используем только когда нет хрефа

        open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");
        File downloadedFile = $("#raw-url").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            assertThat(new String(is.readAllBytes(), StandardCharsets.UTF_8)) // assertThat - это метод специальной библиотеки 'org.assertj:assertj-core:3.22.0', подключенной в бтлд.гредле
                    .contains("Eclipse Public License - v 2.0");

        }
    }

    @Test
    void uploadFile() {
        open("https://the-internet.herokuapp.com/upload");

//        $("inpue[type='file'").uploadFile(new File("")); // загрузка файла в браузере всегда осуществляется через input с тайпом file <input type="file"> // если есть несколько инпутов с тайпом файл, можно уже поискать по другим локаторам, по id например

//        $("inpue[type='file'").uploadFile(new File("D:\\Installed\\Programming\\IntelliJ IDEA projects\\qa_guru_7_files_hw\\src\\test\\resources\\upload.txt"));
//        $("inpue[type='file'").uploadFile(new File("src\\test\\resources\\upload.txt"));
        // верхние две строки будет работать только в данном тесте, условной java программе это работать не будет, потому, что адрес будет другим

        // чтобы сделать правильно, нужно добавить класс лоадер
//        $("inpue[type='file'").uploadFile(cl.); // и здесь мы можем вызвать два метода .getResources - абстракция над URL и .getResourcesAsStream - абстракция над путем на диске

        $("input[type='file'").uploadFromClasspath("files_Lesson_7/upload.txt"); // в селениде есть метод, который сразу ищет файл в папках по умолчанию (по ClassPath, ClassPath - это корень)
        // внутри данного метода так же лежит класс лоадер, просто за нас этот код уже написали разработчики селенида
        // если наш файл лежит в нутри какой-то папки в папке resources, то нам с этого момента нужно прописывать путь в аргументах к методу .uploadFromClasspath, например наш файл лежит в папке files - .uploadFromClasspath("files/upload.txt")

        $("#file-submit").click();
        $("#uploaded-files").shouldHave(Condition.text("files_Lesson_7/upload.txt"));
    }
}
