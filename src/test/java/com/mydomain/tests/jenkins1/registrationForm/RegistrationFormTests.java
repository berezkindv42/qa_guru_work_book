package com.mydomain.tests.jenkins1.registrationForm;

import com.mydomain.tests.jenkins1.registrationForm.pages.RegistrationPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mydomain.tests.jenkins1.registrationForm.TestData.*;


public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Owner("berezkindv")
    @DisplayName("Тест заполнения формы регистрации студента")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Форма регистрации", url = "https://demoqa.com/automation-practice-form")
    void fillFormTests() {

        registrationPage
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .typeUserNumber(userNumber)
                .typeCurrentAddress(currentAddress)
                .fillSubjectField(subject)
                .genderSelectGender(gender)
                .hobbiesCheckBoxSelect(hobbiesSports)
                .hobbiesCheckBoxSelect(hobbiesReading)
                .hobbiesCheckBoxSelect(hobbiesMusic)
//                .uploadFile("img/1.png")
                .fillStateField()
                .fillCityField();
        registrationPage.calendarComponent.setBirthDate("12", "April", "1961");
        registrationPage.pushSubmitButton();
//        Asserts
        registrationPage
                .assertFormTitle("Thanks for submitting the form")
                .assertsForm("Student Name", firstName + " " + lastName)
                .assertsForm("Student Email", userEmail)
                .assertsForm("Gender", gender)
                .assertsForm("Mobile",userNumber)
                .assertsForm("Date of Birth", "12 April,1961")
                .assertsForm("Subjects", subject)
                .assertsForm("Hobbies", hobbiesSports + "," + " " + hobbiesReading + "," + " " + hobbiesMusic)
//                .assertsForm("Picture", "1.png")
                .assertsForm("Address", currentAddress)
                .assertsForm("State and City", state + " " + city)
                .closeTable();
        registrationPage.assertFormTitleNegative();
    }
}
