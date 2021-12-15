package com.nightkonngmail.docs;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class CssXpathExamples {
    void cssXpathExamples() {

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">

        $("[data-testid=email]");
        $(by("data-testid", "email"));

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">

        $("[data-testid=email]");
        $(by("data-testid", "email"));
    }

}
