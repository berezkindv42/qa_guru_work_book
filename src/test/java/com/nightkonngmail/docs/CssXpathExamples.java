package com.nightkonngmail.docs;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExamples {
    void cssXpathExamples() {

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("[data-testid=email]");
        $(by("data-testid", "email"));

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">
        $("[id=email]");
        $("#email"); // для id работает решетка
        $(byId("email"));
        $x("//*[@id='email']");
        $(byXpath("//*[@id='email']"));

        // <input type="email" class="inputtext login_form_input_box" name="email">
        $("[name=email]");
        $(byName("email"));

        // <input type="email" class="inputtext login_form_input_box">
        $("class=login_form_input_box");
        $(".login_form_input_box"); // class= можно заменить на точку .
        $(".inputext.login_form_input_box"); // так же можно добавить .inputext
        $(byClassName("login_form_input_box")); // зачем-то можно сделать так
        $("input.inputext.login_form_input_box"); // так же можно указать тип элемента
        $(byXpath("//input[@class='login_form_input_box']")); // обращение в случае использования xpath


        $(".inputext. login_form_input_box");
        // с пробелом будет работать в следующем случае:
        // <div class="inputtext">
        //      <input type="email" class="login_form_input_box">
        // </div>
        // с пробелом мы как бы ищем элемент .inputtext и внутри него элемент login_form_input_box
        // без пробела это один элемент .inputext.login_form_input_box

        // <div>Hello qa.guru!</div>        подобный кусок кода можно поискать по тексту
        $(byText("Hello qa.guru!")); // по полному тексту
        $(withText("llo qa.gu"));  // или по куску текста


        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("input#email.inputext.login_form_input_box[data-testid=email]");
        // можно сложить все и это будет супер конкретное обращение, но это избыточно

    }
}
