package com.itech.library.pojo;

public class AuthorPojo {

    private Integer id;
    private String firstName;
    private String lastName;

    public AuthorPojo() {
    }

    public AuthorPojo(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

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

        public AuthorPojo build() {
            return new AuthorPojo(this);
        }
    }
}
