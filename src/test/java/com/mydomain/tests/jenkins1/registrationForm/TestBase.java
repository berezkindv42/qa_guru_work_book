package com.mydomain.tests.jenkins1.registrationForm;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.mydomain.tests.jenkins1.helpers.Attach;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    @Step("Конфигурируем браузер и удаленный запуск")
    static void beforeAllMethod() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()); // для лямбда тестов

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub"; // запуск тестов на удаленной платформе // комментирование этой строки отключает четыре строки снизу
//        Configuration.remote = System.getProperty("remote_driver_url", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities; // четыре строки конфигурирования удаленной платформы
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
