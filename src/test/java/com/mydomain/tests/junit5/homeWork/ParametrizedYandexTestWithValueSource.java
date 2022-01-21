package com.mydomain.tests.junit5.homeWork;

import com.mydomain.tests.junit5.homeWork.pages.TestPages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.mydomain.tests.junit5.homeWork.TestData.yaSearch;

public class ParametrizedYandexTestWithValueSource {

    TestPages testPages = new TestPages();

    @ValueSource(strings = {"Selenide", "Allure", "Junit"})
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void commonSearchTest(String testData) {
        testPages.openPage(yaSearch)
                .typeYaSearchData(testData)
                .pushYaSubmitButton()
                .checkYaResult(testData);
    }

}
