package com.lcl.Crawler;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoginSimulateTest {
    @Test
    void buildsLoginDataFromCallerSuppliedCredentials() {
        Map<String, String> data = LoginSimulate.buildLoginData("learner@example.test", "secret");
        assertEquals("learner@example.test", data.get("name"));
        assertEquals("secret", data.get("password"));
    }

    @Test
    void rejectsBlankCredentialsBeforeNetworkAccess() {
        assertThrows(IllegalArgumentException.class,
                () -> LoginSimulate.buildLoginData(" ", "secret"));
        assertThrows(IllegalArgumentException.class,
                () -> LoginSimulate.buildLoginData("learner@example.test", ""));
    }
}
