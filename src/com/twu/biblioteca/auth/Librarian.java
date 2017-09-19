package com.twu.biblioteca.auth;

import java.util.Objects;

public class Librarian implements Account {

    private String name;
    private String password;
    private Role role = Role.LIBRARIAN;
    private String ID;

    public Librarian(String name, String password, String ID) {
        this.name = name;
        this.password = password;
        this.ID = ID;
    }

    @Override
    public boolean verifyPassWord(String password) {
        return Objects.equals(password, this.password);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Role getRole() {
        return role;
    }

    @Override
    public boolean sameID(String ID) {
        return ID.equals(this.ID);
    }

    @Override
    public String getID() {
        return ID;
    }
}
