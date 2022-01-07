package com.mydomain.pageObjectsAndRandomUtils.mydomain.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            redultsTable = $(".table-responsive");

    public CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this; // сценарный метод, в метод завернут целый сценарий действий
    }


    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this; // степовой метод, каждое действие обернуто в отдельный метод, используется на крупных проектах, где много переиспользований
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
        return this;
    }


    /*
    public AnotherPage clockOnAnotherPage() {
        $("").click();
        return new AnotherPage;
    }
     */

    public RegistrationPage checkResultValue(String key, String value) {
        redultsTable.$(byText(key))
                .parent()
                .shouldHave(text(value));
        firstNameInput.setValue(value);
        return this;
    }
}
