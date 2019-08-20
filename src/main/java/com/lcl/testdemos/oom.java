package com.lcl.testdemos;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class oom {
    public static void main(String[] args) {
        ThreadLocal<String> name = new ThreadLocal<>();
        name.set("name");
    }
}
