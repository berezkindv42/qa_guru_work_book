package com.mydomain.docs;

import com.codeborne.selenide.*;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class SelenideSnippets {

  void browser_command_examples() {
    // -Dselenide.baseUrl=http://github.com
    open("https://google.com"); // открываем страницу
    open("/customer/orders"); // открываем страницу оп частичному URL, base url открывается отдельно или возможно параметром запуска
    open("/", AuthenticationType.BASIC, "user", "password"); // Открытие сайта с базовой аутентификацией, редко используется.

    Selenide.back(); // кнопка "назад" в браузере
    Selenide.refresh(); // кнопка "обновить" в браузере

    Selenide.clearBrowserCookies(); // очистка cookies, используется перед вызовом команды open
    Selenide.clearBrowserLocalStorage(); // очистка local storage, используется перед вызовом команды open

    Selenide.confirm(); // OK в стандартном (встроенном в браузере) встроенном диалоге
    Selenide.dismiss(); // Cancel в стандартном (встроенном в браузере) встроенном диалоге

    Selenide.closeWindow(); // закрывает активную вкладку // таб(вкладка) и отдельное окно для селенида идентичны
    Selenide.closeWebDriver(); // закрывает все открытые окна браузера

    Selenide.switchTo().frame("new"); // переходим в нутрь фрейма // iframe это как frame, только без рамочки, selenium не может искать внутри iframe, туда сначала нужно перейти
    Selenide.switchTo().defaultContent(); // переход из iframe обратно на основную страничку
    // фрейм - это как бы страница внутри страницы.

    Selenide.switchTo().window("The Internet"); // переключение между несколькими окнами
  }

  void selectors_examples() {
    $("div").click();
    element("div").click(); // element идентично $ (котлин понимает только element ибо $ у него занят)

    $("div", 2).click(); // поиск третьего div (начинается с 0)

    $(byXpath("//h1/div")).click(); // поиск по Xpath
    $x("//h1/div").click(); // поиск по Xpath сокращенный вариант, работает в дев тулс в браузере

    $(byText("full text")).click(); // поиск по полному совпадению текста
    $(withText("ull tex")).click(); // поиск по частичному совпадению текста

    $("").parent(); // ищет родителя
    $("").sibling(1); // ищет соседний элемент вниз по дереву, число означает номер соседа (начинается с 0)
    $("").preceding(1); // ищет соседний элемент вверх по дереву, число означает номер соседа (начинается с 0)
    $("").closest("div"); // ищет вверх по дереву первый запрошенный элемент
    $("").ancestor("div"); // the same as closest
    $("div:last-child");


    $("div").$("h1").find(byText("abc")).click(); // пример: ищем div, внутри него ищем h1, внутри него ищем текст "abc" // всегда будет находиться первый элемент

    // very optional
    $(byAttribute("abc", "x")).click(); // ищем атрибут с именем "abc" и значением "x"
    $("[abc=x]").click(); // упрощение для поиска атрибута (квадратные скобки)

    $(byId("mytext")).click(); // ищем id "mytext"
    $("#mytext").click(); // упрощение для id (решетка - #)

    $(byClassName("red")).click(); // ищем класс "red"
    $(".red").click(); // упрощение для класса (точка - .)
  }

  void actions_examples() {
    $("").click();
    $("").doubleClick();
    $("").contextClick();

    $("").hover();

    $("").setValue("text");
    $("").append("text");
    $("").clear();
    $("").setValue(""); // clear


    $("div").sendKeys("c"); // hotkey c on element
    actions().sendKeys("c").perform(); //hotkey c on whole application
    actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
    $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

    $("").pressEnter();
    $("").pressEscape();
    $("").pressTab();


    // complex actions with keybord and mouse, example
    actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

    // old html actions don't work with many modern frameworks
    $("").selectOption("dropdown_option");
    $("").selectRadio("radio_options");

  }

  void assertions_examples() {
    $("").shouldBe(visible);
    $("").shouldNotBe(visible);
    $("").shouldHave(text("abc"));
    $("").shouldNotHave(text("abc"));
    $("").should(appear);
    $("").shouldNot(appear);


    //longer timeouts
    $("").shouldBe(visible, Duration.ofSeconds(30));
//    $("").waitUntil(visible, 30000);

  }

  void conditions_examples() {
    $("").shouldBe(visible);
    $("").shouldBe(hidden);

    $("").shouldHave(text("abc"));
    $("").shouldHave(exactText("abc"));
    $("").shouldHave(textCaseSensitive("abc"));
    $("").shouldHave(exactTextCaseSensitive("abc"));
    $("").should(matchText("[0-9]abc$"));

    $("").shouldHave(cssClass("red"));
    $("").shouldHave(cssValue("font-size", "12"));

    $("").shouldHave(value("25"));
    $("").shouldHave(exactValue("25"));
    $("").shouldBe(empty);

    $("").shouldHave(attribute("disabled"));
    $("").shouldHave(attribute("name", "example"));
    $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

    $("").shouldBe(checked); // for checkboxes

    // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
    $("").should(exist);

    // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
    $("").shouldBe(disabled);
    $("").shouldBe(enabled);
  }

  void collections_examples() {

    $$("div"); // does nothing!

    // selections
    $$("div").filterBy(text("123")).shouldHave(size(1));
    $$("div").excludeWith(text("123")).shouldHave(size(1));

    $$("div").first().click();
    elements("div").first().click();
    // $("div").click();
    $$("div").last().click();
    $$("div").get(1).click(); // the second! (start with 0)
    $("div", 1).click(); // same as previous
    $$("div").findBy(text("123")).click(); //  finds first

    // assertions
    $$("").shouldHave(size(0));
    $$("").shouldBe(CollectionCondition.empty); // the same

    $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
    $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

    $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
    $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

    $$("").shouldHave(itemWithText("Gamma")); // only one text

    $$("").shouldHave(sizeGreaterThan(0));
    $$("").shouldHave(sizeGreaterThanOrEqual(1));
    $$("").shouldHave(sizeLessThan(3));
    $$("").shouldHave(sizeLessThanOrEqual(2));

  }

  void file_operation_examples() throws FileNotFoundException {

    File file1 = $("a.fileLink").download(); // only for <a href=".."> links
    File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

    File file = new File("src/test/resources/readme.txt");
    $("#file-upload").uploadFile(file);
    $("#file-upload").uploadFromClasspath("readme.txt");
    // don't forget to submit!
    $("uploadButton").click();
  }

  void javascript_examples() {
    executeJavaScript("alert('selenide')");
    executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
    long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

  }

}

