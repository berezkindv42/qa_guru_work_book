package com.mydomain.tests.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }
    @Step("Изем репозиторий {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }
    @Step("Переходим в репозиторий")
    public void openRepositoryPage(String repository) {
        $(linkText(repository)).click();
    }
    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $(partialLinkText("Issue")).click();
    }
    @Step("Проверяем наличие issue с именем {name}")
    public void shouldSeeIssueWithName(String name) {
//        attachPageSource(); // добавление аттачмента к отчету руками, отдельно для каждого степа
        $(withText(name)).should(Condition.visible);
    }

    @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);
        // конструкция добавления аттачмента к отчету
        // по приложенному сорсу можно искать эмементы девтулзом прямо из аллюра(!)
    }



}
