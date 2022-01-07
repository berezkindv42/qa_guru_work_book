package com.mydomain.pageObjectsAndRandomUtils.mydomain.testsExamples;

import com.codeborne.selenide.Configuration;
import com.mydomain.pageObjectsAndRandomUtils.mydomain.pages.components.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.mydomain.pageObjectsAndRandomUtils.mydomain.testsExamples.TestData.userEmail;

public class PracticeFormTestsWithDescription {

    // Можно вынести данный в переменные и применять их вместо строк заполнения формы
//        String firstName = "Vasiliy";
//        String lastName = "Pupkin";
    // Далее в коде все строки вынесенные в переменные должны быть заменены на имена переменных
    // Либо можно эти переменные вынести в отдельный класс (TestData) к userEmail например

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAllMethod() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void runTests() {
//        registrationPage.openPage();
//        registrationPage.typeFirstName("Vasiliy");
//        registrationPage.typeLastName("Pupkin"); // можно так

        registrationPage.openPage()
                .typeFirstName("Vasiliy")
                .typeLastName("Pupkin"); // а можно вот так

        $("#userEmail").setValue(userEmail);

        $("label.custom-control-label").shouldHave(text("Male")).click();
        $("#userNumber").setValue("4951234567");
        registrationPage.calendarComponent.setDate("12", "April", "1961"); // вызов из отдельного класса // можно так
//        registrationPage.setBirthDate("12", "April", "1961"); // вызов из класса registrationPage // или можно так

//        $("#dateOfBirthInput").click();
//        $(".react-datepicker__month-select").selectOption("April");
//        $(".react-datepicker__year-select").selectOption("1961");
//        //$(".react-datepicker__day--012:not(.react-datepicker__day--outside-month)").click();
//        $("[aria-label$='April 12th, 1961']").click();

        $("#subjectsInput").setValue("e");
        $(".subjects-auto-complete__menu #react-select-2-option-0").click();
        $("label[for=hobbies-checkbox-1]").click();
        $("label[for=hobbies-checkbox-2]").click();
        $("label[for=hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFromClasspath("img/testUploadFile1.png");
        $("#currentAddress").setValue("Random address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

//        Asserts
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
//        registrationPage.checkResultValue("Student Name", "Vasiliy Pupkin");
//        registrationPage.checkResultValue("Gender", "Male");
//        registrationPage.checkResultValue("Some Text", "Some Text");
//        registrationPage.checkResultValue("Some Text", "Some Text"); // можно так
        registrationPage.checkResultValue("Student Name", "Vasiliy Pupkin") // а можно вот так, подход вызова метода у метода называется chain или DSL или fluent
                .checkResultValue("Student Email", "vasiliy.p@email.com")
                .checkResultValue("Gender", "Male")
                .checkResultValue("Mobile", "4951234567")
                .checkResultValue("Subjects", "English")
                .checkResultValue("Hobbies", "Sports, Reading, Music")
                .checkResultValue("Picture", "testUploadFile1.png")
                .checkResultValue("Address", "Random address")
                .checkResultValue("State and City", "NCR Delhi");
        $("#closeLargeModal").click();

//        $(".table-respondive").$(byText("Student Name"))
//                .parent().shouldHave(text("Vasiliy Pupkin"));

//        $(".modal-body").shouldHave(text("Vasiliy Pupkin"),
//                text("vasiliy.p@email.com"),
//                text("Male"),
//                text("4951234567"),
//                text("12 April,1961"),
//                text("English"),
//                text("Sports, Reading, Music"),
//                text("testUploadFile1.png"),
//                text("Random address"),
//                text("NCR Delhi"));
//        $("#closeLargeModal").click();
    }
}
