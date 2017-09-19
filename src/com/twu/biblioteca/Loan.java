package com.twu.biblioteca;

import com.twu.biblioteca.auth.User;

public class Loan {
    public User user;
    public Item item;


    public Loan(User user, Item item) {
        this.user = user;
        this.item = item;
    }

    @Override
    public String toString() {
        return user.getID()+"\t"+user.getName()+"\t"+item.getName();
    }
}
