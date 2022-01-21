package com.mydomain.tests.junit5.homeWork;

import com.mydomain.tests.junit5.homeWork.pages.TestPages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.mydomain.tests.junit5.homeWork.TestData.gooSearch;

public class ParametrizedGoogleTestWithCsvSource {

    TestPages testPages = new TestPages();


    @CsvSource(value = {
            "Selenide, это фреймворк для автоматизированного тестирования",
            "Allure, Allure Report is a flexible",
            "Junit, JUnit 5 is the next generation of"
    })
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void commonSearchTest(String testData, String expectedResult) {
        testPages.openPage(gooSearch)
                .gooSearchData(testData)
                .pushGooSubmitButton()
                .checkGooResult(expectedResult);
    }
}
