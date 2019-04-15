package com.itech.library.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

    private Integer id;

    @NotBlank(message = "Login doesn't be empty")
    private String login;
    @NotBlank(message = "Password doesn't be empty")
    private String password;

    public UserDto() {
    }

    private UserDto(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
    }

    // Get Set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Builder
    public static class Builder {
        private Integer id;
        private String login;
        private String password;


        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
