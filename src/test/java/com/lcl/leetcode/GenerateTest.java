package com.lcl.leetcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateTest {

    @Test
    void returnsTheRequestedPascalRow() {
        assertEquals(List.of(1, 4, 6, 4, 1), new Generate().solution(5).get(4));
    }
}
