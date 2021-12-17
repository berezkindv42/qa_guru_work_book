package com.nightkonngmail.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentsRegistrationFormTests {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

    }

    @Test
    void runTests() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form")); // проверяем название формы, не суть важно, но в целом хороший тон

        $("#firstName").setValue("Vasiliy");
        $("#lastName").setValue("Pupkin");
        $("#userEmail").setValue("vasiliy.p@email.com");

//        $("#gender-radio-3").click(); // не работает, судя по логу нижний элемент залез на искомый и перехватывает клик
//        $("#gender-radio-3").doubleClick(); // работает, но зачем? мы эмулируем поведение пользователя, пользователь не даблкликает на радио кнопки и чекбоксы
//        $("#gender-radio-3").parent().click(); // работает
//        $("label['for=gender-radio-3']").click(); // одинарные ковычки нужны, если в строке есть пробел! работает
//        $(byText("Other")).click; работает, но эт не корректный поиск
        $("#genterWrapper").$(byText("Other")).click(); // работает, ищем текст в корневом локаторе, так уже близко к истине
        $("#userNumber").setValue("4951234567");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July"); // для стандартного
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $$(".react-datepicker__day react-datepicker__day--030")
                .filter(not(cssClass()))
                .first() // или .get(0)
                .clic();

//        $("[aria-label=Choose Wednesday, July 30th, 2008]").click(); // NOT WORKING
//        $("[aria-label=\"Choose Wednesday, July 30th, 2008\"]").click();
//        $("[aria-label='Choose Wednesday, July 30th, 2008']").click();
        $("[aria-label$='July 30th, 2008']").click();
//        $x("//*[contains(@aria-label, \"July 30th, 2008\")]").click();

// <div class="react-datepicker__day--030 react-datepicker__day--outside-month"  aria-label="Choose Monday, June 30th, 2008">30</div>
// <div class="react-datepicker__day--030"                                       aria-label="Choose Wednesday, July 30th, 2008">30</div>

        $("#subjectsInput").setValue("Math").pressEnter();

//        $("#subjectsInput").setValue("M"); // todo Why not working
//        $("#subjectsWrapper").$(byText("Math")).click();

        $("#hobbiesWrapper").$(byText("Sports")).click();

//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png"));
//        File someFile = new File("src/test/resources/img/1.png");
//        $("#uploadPicture").uploadFile(someFile);
        $("#uploadPicture").uploadFromClasspath("img/1.png");

        $("#currentAddress").setValue("Some address 1");

        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();

        $("#submit").click();

        // todo Asserts
    }
}