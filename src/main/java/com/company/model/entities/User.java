package com.company.model.entities;

import java.util.Objects;

public class User {
    private int id;
    private String nickname;
    private String login;
    private String password;
    private Role role;

    public enum Role {
        GUEST, USER, ADMIN
    }

    public static class Builder {
        protected int id = 0;
        private String nickname;
        private String login;
        private String password;
        private Role role;

        public Builder(String nickname, String login, String password, Role role){
            this.nickname = nickname;
            this.login = login;
            this.password = password;
            this.role = role;
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    User(Builder builder) {
        this.id = builder.id;
        this.nickname = builder.nickname;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return nickname.equals(user.nickname) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return ((nickname.hashCode() * 31) + login.hashCode() * 31) + password.hashCode();
    }
}
