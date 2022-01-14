package com.mydomain.tests.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LabelsTest {

    @Test
    public void lambdaLabelsTest() {
        Allure.label("owner", "berezkindv");
        Allure.feature("Issue");
        Allure.story("Создание issue для авторизованного пользователя");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("Github", "https://github.com");
        // вариант написания лейблов прямо в коде // менее наглядно, более гибко
    }

    @Test
    @Owner("berezkindv") // создатель теста, для идентификации
    @Feature("Issue") // название фичи
    @Stories({
            @Story("Создание issue со страницы репозитория"),
            @Story("Создание issue для авторизованного пользователя")
    })
//    @Story("Создание issue со страницы репозитория") // описание сценария
    @DisplayName("Создание issue для авторизованного пользователя")
    @Severity(SeverityLevel.BLOCKER) // уровень критичность теста
    @Link(value = "Github", url = "https://github.com") // ссылка
    // вариант написания лейблов в аннотациях  // более наглядно, менее гибко
    public void annotatedLabelsTest() {
    }
}
