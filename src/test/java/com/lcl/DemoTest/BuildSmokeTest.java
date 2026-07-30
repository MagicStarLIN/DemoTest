package com.lcl.DemoTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildSmokeTest {
    @Test
    void runsOnJava21OrNewer() {
        assertEquals(21, Runtime.version().feature());
    }
}
