package com.company.model.entities;

import java.util.Objects;

/**
 * Represents a simple web site user
 */
public class User {
    private int id;
    private String nickname;
    private String login;
    private String password;
    private Role role;

    /**
     * Describes a role of user
     */
    public enum Role {
        GUEST, USER, ADMIN
    }

    /**
     * Implementation of builder pattern for user
     */
    public static class Builder {
        protected int id = 0;
        private String nickname;
        private String login;
        private String password;
        private Role role;

        /**
         * Constructs a builder with mandatory parameters
         */
        public Builder(String nickname, String login, String password, Role role){
            this.nickname = nickname;
            this.login = login;
            this.password = password;
            this.role = role;
        }

        /**
         * Sets an optional id parameter
         * @return builder with specified id
         */
        public Builder id(int val) {
            id = val;
            return this;
        }

        /**
         * Builds a user object
         */
        public User build() {
            return new User(this);
        }
    }

    /**
     * Constructs user using builder
     */
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

    /**
     * Compares two objects for equality.
     * @return <code>true</code> if the objects are the same;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return nickname.equals(user.nickname) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    /**
     * Computes a hash code to user
     * @return hash code
     */
    @Override
    public int hashCode() {
        return ((nickname.hashCode() * 31) + login.hashCode() * 31) + password.hashCode();
    }
}
