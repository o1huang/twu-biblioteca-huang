package com.twu.biblioteca.auth;

import java.util.Objects;

public class User {
    /**
     * user's name, also used as login key
     */
    private String name;
    private String password;
    private Role role;

    public User(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
    public boolean verifyPassWord(String password){
        return Objects.equals(password, this.password);
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public boolean sameName(String name){
        return name.equals(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }


}
