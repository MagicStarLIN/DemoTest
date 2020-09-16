package com.lcl.testdemos;

public class oom {
    public static void main(String[] args) {
        ThreadLocal<String> name = new ThreadLocal<>();
        name.set("name");
    }
}
