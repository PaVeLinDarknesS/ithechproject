package com.itech.library.pojo;

public class UserPojo {

    private Integer id;
    private String login;
    private String password;

    public UserPojo() {
    }

    public UserPojo(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
    }

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

        public UserPojo build() {
            return new UserPojo(this);
        }
    }
}
