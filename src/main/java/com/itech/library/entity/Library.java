package com.itech.library.entity;

public class Library {

    private Integer id;
    private String title;
    private Integer year;
    private Integer count;

    public Library() {
    }

    public Library(Integer id, String title, Integer year, Integer count) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.count = count;
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
}
