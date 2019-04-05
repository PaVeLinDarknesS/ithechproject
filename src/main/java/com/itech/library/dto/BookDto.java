package com.itech.library.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class BookDto {


    private Integer id;

    @NotBlank(message = "Title doesn't be empty")
    private String title;
    private Integer year;

    @NotNull(message = "Count not null")
    @Positive(message = "Count always more then 0")
    private Integer count;

    private AuthorDto author;

    public BookDto() {
    }

    public BookDto(Integer id) {
        this.id = id;
    }

    public BookDto(Builder builder) {
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

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public static class Builder {
        private Integer id;
        private String title;
        private Integer year;
        private Integer count;
        private AuthorDto author;

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

        public Builder setAuthor(AuthorDto author) {
            this.author = author;
            return this;
        }

        public BookDto build() {
            return new BookDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(year, bookDto.year) &&
                Objects.equals(count, bookDto.count) &&
                Objects.equals(author, bookDto.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, count, author);
    }
}
