package com.mydomain.pageObjectsAndRandomUtils.mydomain.testsExamples;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.mydomain.pageObjectsAndRandomUtils.mydomain.utils.RandomUtils.getRandomEmail;
import static com.mydomain.pageObjectsAndRandomUtils.mydomain.utils.RandomUtils.getRandomString;

public class PracticeFormWithRandomUtils {

    String firstName = getRandomString(7);
    String userEmail = getRandomEmail();

    @BeforeAll
    static void beforeAllMethod() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void runTests() {
        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue("Pupkin");
        $("#userEmail").setValue(userEmail);
        $("label.custom-control-label").shouldHave(text("Male")).click();
        $("#userNumber").setValue("4951234567");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1961");
//        $(".react-datepicker__day--012:not(.react-datepicker__day--outside-month)").click();
        $("[aria-label$='April 12th, 1961']").click();
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
        $(".modal-body").shouldHave(text(firstName + " Pupkin"),
                text(userEmail),
                text("Male"),
                text("4951234567"),
                text("12 April,1961"),
                text("English"),
                text("Sports, Reading, Music"),
                text("testUploadFile1.png"),
                text("Random address"),
                text("NCR Delhi"));
        $("#closeLargeModal").click();
    }
}
