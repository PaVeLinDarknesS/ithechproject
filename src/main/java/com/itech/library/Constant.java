package com.itech.library;

public class Constant {
    private Constant() {
    }

    public static class View {
        public static class Book {
            public static final String ALL = "/book/allBook";
            public static final String ONE = "/book/oneBook";
            public static final String UPDATE = "/book/updateBook";
            public static final String CREATE = "/book/createBook";
        }

        public static class Author {
            public static final String ALL = "/author/allAuthor";
            public static final String ONE = "/author/oneAuthor";
            public static final String UPDATE = "/author/updatAauthor";
            public static final String CREATE = "/author/createAuthor";
        }

        public static class Error {
            public static final String ERROR = "/error/error";
        }

        public static class Success {
            public static final String SUCCESS = "/success/success";
        }

        public static final String HELLO = "/hello";
    }
}
