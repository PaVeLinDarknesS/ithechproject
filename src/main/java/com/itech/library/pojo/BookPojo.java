package com.itech.library.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class BookPojo {


    private Integer id;

    @NotBlank
    private String title;
    private Integer year;
    @Min(0)
    private Integer count;

    private AuthorPojo author;

    public BookPojo() {
    }

    public BookPojo(Integer id) {
        this.id = id;
    }

    public BookPojo(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.year = builder.year;
        this.count = builder.count;
        this.author = builder.author;
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

    public static class Builder {
        private Integer id;
        private String title;
        private Integer year;
        private Integer count;
        private AuthorPojo author;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setYear(Integer year) {
            this.year = year;
            return this;
        }

        public Builder setCount(Integer count) {
            this.count = count;
            return this;
        }

        public Builder setAuthor(AuthorPojo author) {
            this.author = author;
            return this;
        }

        public BookPojo build() {
            return new BookPojo(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookPojo bookPojo = (BookPojo) o;
        return Objects.equals(id, bookPojo.id) &&
                Objects.equals(title, bookPojo.title) &&
                Objects.equals(year, bookPojo.year) &&
                Objects.equals(count, bookPojo.count) &&
                Objects.equals(author, bookPojo.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, count, author);
    }
}
