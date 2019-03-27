package com.itech.library.pojo;

public class BookPojo {

    private Integer id;
    private String title;
    private Integer year;
    private Integer count;

    private AuthorPojo author;

    public BookPojo() {
    }

    public BookPojo(Integer id, String title, Integer year, Integer count, AuthorPojo author) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.count = count;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public AuthorPojo getAuthor() {
        return author;
    }

    public void setAuthor(AuthorPojo author) {
        this.author = author;
    }
}
