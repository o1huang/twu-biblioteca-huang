package com.twu.biblioteca.auth;

public interface Account {
    public boolean verifyPassWord(String password);
    public String getName();
    public Role getRole();
    public boolean sameID(String ID);
    public String getID();
}
