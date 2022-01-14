package com.mydomain.tests.allure;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;


public class ParametersTest {

    @Test
    public void lambdaLabelsTest() {
        Allure.parameter("Регион", "Москва");
        Allure.parameter("Область", "Московская");
        // параметры помогают выделить тесты в отчете
    }
}
