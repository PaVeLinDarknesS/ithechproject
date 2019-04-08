package com.itech.library.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class AuthorDto {

    private Integer id;

    @NotBlank(message = "First name doesn't be empty")
    private String firstName;
    @NotBlank(message = "Last name doesn't be empty")
    private String lastName;

    public AuthorDto() {
    }

    public AuthorDto(Integer id) {
        this.id = id;
    }

    private AuthorDto(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    // Get Set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Builder
    public static class Builder {
        private Integer id;
        private String firstName;
        private String lastName;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AuthorDto build() {
            return new AuthorDto(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto that = (AuthorDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
