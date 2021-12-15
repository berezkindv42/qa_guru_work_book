package com.nightkonngmail.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TExtBoxTests {

    @BeforeAll
    static void beforeAllMethod() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successTest() {
        open("https://demoqa.com/text-box");

        $("#userName").setValue("Alex");
        $("#userEmail").setValue("email@email.com");
        $("#currentAddress").setValue("this current address");
        $("#permanentAddress").setValue("this permanent address");
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text("Alex"));
        $("#output #email").shouldHave(text("email@email.com"));
        $("#output #currentAddress").shouldHave(text("this current address"));
        $("#output #permanentAddress").shouldHave(text("this permanent address"));

    }
}
