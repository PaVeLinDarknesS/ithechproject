package com.itech.library;

public class Constant {
    private Constant() {
    }

    public static class View {
        public static class Book {
            public static final String ALL = "/book/allBook";
            public static final String ONE = "/book/oneBook";
        }
        public static class Error {
            public static final String FINDBOOK = "/error/error";

        }

        public static final String HELLO = "/hello";
    }
}
