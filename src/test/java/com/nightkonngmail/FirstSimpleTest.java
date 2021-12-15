package com.nightkonngmail;

import org.junit.jupiter.api.*;

@DisplayName("Это наш первый простой тест")
public class FirstSimpleTest {

    @BeforeAll
    static void beforeAllMethod() {
        System.out.println("@BeforeAll method!");
    }

    @BeforeEach
    void beforeEachMethod() {
        System.out.println("    @BeforeEach method!");
    }

    @DisplayName("Простой тест на assertEquals")
    @Test
    void firstTest() {
        System.out.println("        Простой тест на assertEquals");
        Assertions.assertEquals(1,1);
    }

    @DisplayName("Простой тест на assertTrue")
    @Test
    void secondTest() {
        System.out.println("        Простой тест на assertTrue");
        Assertions.assertTrue(7 > 6);
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("    @AfterEach method!");
    }

    @AfterAll
    static void afterAllMethod() {
        System.out.println("@AfterAll method!");
    }

}
