package com.nightkonngmail.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchSelenideRepo {

    // стандартная структура теста выглядит так:
    // ARRANGE (optional)
    // ACT

    // или так: с проверками после каждого теста    или так: с промежуточной проверкой в важных местах
    // ARRANGE (optional)                           // ARRANGE (optional)
    // ACT                                          // ACT
    // ASSERT                                       // ACT
    // ACT                                          // ACT
    // ASSERT                                       // ASSERT
    // ACT                                          // ACT
    // ASSERT                                       // ASSERT

    // вначале готовимся к тесту и проходим сценарий руками

    @Test
    void shoudFindSelenideRepositoryInGithub() throws InterruptedException {
        // открыть страницу github.com
        open("https://github.com/");
        // ввести в поле поиска selenide и нажать Enter
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        // нажимаем на линк от первого результата поиска
//        $$("ul.repo-list Li").first().click(); // selenide всегда кликает в самый центр элемента по этому не работает
        $$("ul.repo-list Li").first().$("a").click();
        // check: в заголовке встречается selenide/selenide
//        $("h1").shouldHave(text("selenide/selenide")); // не работает потому, что по логу текст selenide/selenide находится на трех строках
        // можно добавить символы пропуска строк selenide\n/\nselenide или просто проставить пробелы
        $("h1").shouldHave(text("selenide / selenide"));

//        sleep(5000);

//        Все проверки НУЖНО делать через .should'ы!

    }

}
