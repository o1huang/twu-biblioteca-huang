package com.twu.biblioteca;

public interface Item {
    public void rent();
    public void returns();
    public String getName();
    public boolean isBeenRented();
}
