package com.nightkonngmail.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.*;

public class StudentsRegistrationFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form")); // Проверяем название формы, не суть важно, но в целом хороший тон

        $("#firstName").setValue("Vasiliy");
        $("#lastName").setValue("Pupkin");
        $("#userEmail").setValue("vasiliy.p@email.com");

//        $("#gender-radio-3").click(); // Не работает, судя по логу нижний элемент залез на искомый и перехватывает клик
//        $("#gender-radio-3").doubleClick(); // Работает, но зачем? мы эмулируем поведение пользователя, пользователь не даблкликает на радио кнопки и чекбоксы
//        $("#gender-radio-3").parent().click(); // Работает
//        $(byText("Other")).click; Работает, но эт не корректный поиск, ибо other'ов может быть много
//        $("label['for=gender-radio-3']").click(); // Одинарные ковычки нужны, если в строке есть пробел! В данном случае они не нужны и тогда сработает
        $("#genterWrapper").$(byText("Other")).click(); // Работает, ищем текст в корневом локаторе, так уже близко к истине

        $("#userNumber").setValue("4951234567");

        $("#dateOfBirthInput").click(); // Работаем с полем "Дата рождения". Это классический HTML элемент и в Selenide для него есть специальный метод
        $(".react-datepicker__month-select").selectOption("July"); // Если ест Option'сы, можно заюзать метод .selectOption
        $(".react-datepicker__year-select").selectOption("2008");
//        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click(); // Первый вариант. Работает. Выбираем элемент в котором нет другого элемента, который после символа ":"

//        $$(".react-datepicker__day--030") // Второй вариант. Мы можем найти массив и отфильтровать. Тут мы находим все элементы отвечающие условию // $ - возвращает первый найденный элемент, $$ - возвращают все найденные элементы (массив)
//                .filter(not(cssClass(".react-datepicker__day--outside-month"))) // и отфильтровываем.
//                .first() // или .get(0) Выбираем самый первый (нулевой) индекс массива, в данном случае единственный
//                .click(); // и кликаем по нему. Выглядит громоздко, но работает.

//        $("[aria-label=Choose Wednesday, July 30th, 2008]").click(); // Не работает потому, что значение параметра имеет пробелы и не в кавычках
//        $("[aria-label=\"Choose Wednesday, July 30th, 2008\"]").click(); // Работает, вариант с экранированными кавычками
//        $("[aria-label='Choose Wednesday, July 30th, 2008']").click(); // Работает, вариант с одинарными кавычками
        $("[aria-label$='July 30th, 2008']").click(); // Работает. Игнорируем "Wednesday". $ тут означает - edn with - "заканчивается на" (* означает "содержит в себе")
//        $x("//*[contains(@aria-label, \"July 30th, 2008\")]").click(); // Работает XPath вариант. Ищем элемент, в котором aria-label содержит в себе кусок вот от этого "July 30th, 2008"

// <div class="react-datepicker__day--030 react-datepicker__day--outside-month"  aria-label="Choose Monday, June 30th, 2008">30</div>
// <div class="react-datepicker__day--030"                                       aria-label="Choose Wednesday, July 30th, 2008">30</div>

        $("#subjectsInput").setValue("Math").pressEnter(); // Работает, но мы игнорируем выпадающее меню. Просто вводим текст в поле.
//        $("#subjectsInput").setValue("M"); // Вводим в поле символ
//        $("#subjectsWrapper").$(byText("Math")).click(); // todo Why not working // Не работает хз почему.
//        $("#subjectsInput").setValue("M"); // Вводим в поле символ
//        $(".subjects-auto-complete__menu").$("#react-select-2-option-0").click(); // Работает. Выбираем первый пункт из выпавшего меню по номеру опции.

        $("#hobbiesWrapper").$(byText("Sports")).click(); // Работает. Ищем по тексту в корневом локаторе и кликаем по нему.

//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png")); // Первый способ. Рабобает. Должна существовать папка resourses (это стандартная папка ресурсов) содержащая файл.
//        File someFile = new File("src/test/resources/img/1.png"); // Здесь мы просто выносим файл в отдельную строку
//        $("#uploadPicture").uploadFile(someFile); // метод .uploadFile ждет на вход элемент с типом File, по этому раньше вы создаем этот элемент в коде посредством оператора new
        $("#uploadPicture").uploadFromClasspath("img/1.png"); // Работает. Здесь метод .uploadFromClasspath уже знает путь до папки resourses, по этому полный путь указывать не требуется

        $("#currentAddress").setValue("Some address 1");

        $("#state").scrollTo().click(); // Клик по полю.
        $("#stateCity-wrapper").$(byText("NCR")).click(); // Поиск в корневом локаторе по тексту и клик по нему.
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click(); // Аналогично предыдущему.

        $("#submit").click();

        // todo Asserts
    }

}