package com.mydomain.tests.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleTest {

/*  @Test - аннотация это - специальная метка, может быть над классом, методом, параметром, для возвращаемых значений методом, у конструкторов
    в junit аннотации стоят над классами и методами
*/

    @Test
    void test() {
        Assertions.assertTrue(3 > 2);
    }
    void test2() {

    }
}
