package com.lcl.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Test8Test {

    @Test
    void readsTheIntegerPartOfADecimalNumber() {
        assertEquals("壹", new Test8().handleNumbers("1.0"));
    }
}
