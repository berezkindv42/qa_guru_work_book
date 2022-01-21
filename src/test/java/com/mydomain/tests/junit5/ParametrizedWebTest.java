package com.mydomain.tests.junit5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class ParametrizedWebTest {

    @BeforeEach
    void beforEach() {
        System.out.println("@BeforeEach");
    }

    // @ValueSource
    // пример
    @ValueSource(strings = {"Selenide", "Junit"}) // запятая разделяет не аргументы, а запуски теста
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void commonSearchTest(String testData) {
        Selenide.open("https://ya.ru");
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item")
                .first()
                .shouldHave(Condition.text(testData));
    }
    // пример
    @ValueSource(strings = {"Selenide_Вышла Selenide", "Junit_5 is the next generation of"}) // через нижнее подчеркивание пишем проверочные данные
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void commonSearchTestVariant(String testData) {
        String[] split = testData.split("_"); // .split делит строку testData на две в месте символа "_"
        Selenide.open("https://ya.ru");
        Selenide.$("#text").setValue(split[0]); // в поиск идет первая часть строки
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item")
                .first()
                .shouldHave(Condition.text(split[1])); // а в поверку идет вторая часть строки
    } // в целом это плохой вариант, грязный код, лучше так не делать потому, что есть аннотация с двумя параметрами @CsvSource



    // @CsvSource
    // пример
    @CsvSource(value =  {
            "Selenide; это фреймворк для автоматизированного тестирования, и не только лишь!",
            "Junit; 5 is the next generation of, framework" // оранжевая запятая разделяет запуски тестов, зеленая аргументы
            // если в строке аргументов будет запятая, эту ситуацию Junit воспримет как ошибку (будет думать, что аргумента три)
            // что бы это победить мы можем переопределить delimiter // delimiter = ';' это заменит запятую, разделяющую аргументы на точку с запятой.
    }, delimiter = ';'
    )
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void common2SearchTest(String testData, String expectedResult) {
        Selenide.open("https://ya.ru");
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type='submit'").click();
        Selenide.$$("li.serp-item")
                .first()
                .shouldHave(Condition.text(expectedResult));
    }
    // пример // в случае если нам нужен один параметр String, а другой int, мы можем написать следующим образом:
    @CsvSource(value =  {
            "Selenide, 1", // так же мы можем использовать любой тип данных: boolean, double и т. д.
            "Junit, 5"
    }) // Junit сам определит для какой тип данных // так же нужно изменить тип аргументов в параметрах теста
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void common2SearchTestVariant(String testData, int expectedResult) {
        Selenide.open("https://ya.ru");
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type='submit'").click();
        Selenide.$$("li.serp-item")
                .first()
                .shouldHave(Condition.text(testData)); // здесь должен быть expectedResult // testData тут только потому, что бы не было идея не подсвечивала ошибку
    }


    // @MethodSource
    // пример
    // этот метод называется Data Provider // он может в скобках Argument.of() возвращать любые объекты любых типов
    static Stream<Arguments> commonSearchTestDataProvider() {
        return Stream.of(
                Arguments.of("Selenide", false, List.of("1", "2")),
                Arguments.of("Junit", true, List.of("3", "4"))
        );
    }
    @MethodSource("commonSearchTestDataProvider")
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}")
    void common3SearchTest(String testData, boolean flag, List list) {
        System.out.println("Flag: " + flag);
        System.out.println("List: " + list.toString());
        Selenide.open("https://ya.ru");
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type='submit'").click();
        Selenide.$$("li.serp-item")
                .first()
                .shouldHave(Condition.text(testData));
    }


    // В результатах поиска яндекс есть Selenide
    // * повторить для тестовых данных: [Selenide, Junit]

    // Открыть яндекс
    // Вбить в поле поиска {test_data}
    // Кликнуть кнопку найти

    // в первом результате есть слово {test_data}
}
