package com.nightkonngmail.tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Без этой аннотации и Order'ов тесты будут запускаться в разнобой, так и должно быть. Если мы хотим запускать тесты по порядку следует добавить эту аннотацию и ордеры с нумерацией.
// так же существует аннотация для запуска тестов в разных классах - @TestMethodOrder(ClassOrderer.OrderAnnotation.class // но это нехороший подход!
public class OrderedTests {
    @Test
//    @Order(1)
    void firstTest() {
        System.out.println("test 1");
    }

    @Test
//    @Order(2)
    void secondTest() {
        System.out.println("test 2");
    }

    @Test
//    @Order(3)
    void thirdTest() {
        System.out.println("test 3");
    }

    @Test
//    @Order(4)
    void forthTest() {
        System.out.println("test 4");
    }
}