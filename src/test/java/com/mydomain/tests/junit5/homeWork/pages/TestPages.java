package com.mydomain.tests.junit5.homeWork.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestPages {

    private static final SelenideElement
            yaSearchFieldInput = $("#text"),
            yaSubmitButton = $("button[type='submit']"),
            gooSearchFieldInput = $("input[type='text']"),
            gooSubmitButton = $("input[type='submit']"),
            bingFieldInput = $("#sb_form_q"),
            bingSubmitButton = $("label[id='search_icon']");

    private static final ElementsCollection yaResultsCollection = $$("li.serp-item");
    private static final ElementsCollection gooResultsCollection = $$("div.g");
    private static final ElementsCollection bingResultsCollection = $$("li.b_algo");

    public TestPages openPage(String value) {
        open(value);
        return this;
    }

    public TestPages typeYaSearchData(String value) {
        yaSearchFieldInput.setValue(value);
        return this;
    }
    public TestPages pushYaSubmitButton() {
        yaSubmitButton.click();
        return this;
    }

    public void checkYaResult(String value) {
        yaResultsCollection
                .findBy(text(value))
                .shouldHave(text(value));
    }

    public TestPages gooSearchData(String value) {
        gooSearchFieldInput.setValue(value);
        return this;
    }

    public TestPages pushGooSubmitButton() {
        gooSubmitButton.click();
        return this;
    }

    public void checkGooResult(String value) {
        gooResultsCollection
                .findBy(text(value))
                .shouldHave(text(value));
    }

    public TestPages bingSearchData(String value) {
        bingFieldInput.setValue(value);
        return this;
    }

    public TestPages pushBingSubmitButton() {
        bingSubmitButton.click();
        return this;
    }

    public void checkBingResult(String value) {
        bingResultsCollection
                .findBy(text(value))
                .shouldHave(text(value));
    }

}
