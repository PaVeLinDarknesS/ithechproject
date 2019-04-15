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

    private Integer authorId;

    public BookDto() {
    }

    public BookDto(Integer id) {
        this.id = id;
    }

    private BookDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.year = builder.year;
        this.count = builder.count;
        this.authorId = builder.authorId;
    }

    // Get Set
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    //Builder
    public static final class Builder {
        private Integer id;
        private String title;
        private Integer year;
        private Integer count;
        private Integer authorId;

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

        public Builder setAuthorId(Integer authorId) {
            this.authorId = authorId;
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
                Objects.equals(authorId, bookDto.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, count, authorId);
    }

    @Override
    public String toString() {
        return title + ", " + year;
    }
}
