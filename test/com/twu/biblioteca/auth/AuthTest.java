package com.twu.biblioteca.auth;

import com.twu.biblioteca.Store;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AuthTest {

    Store store;
    @Before
    public void before(){
        store=Store.getNewInstance();
        store.users.add(new User("wang","123456",Role.COSTUMER));
        store.users.add(new User("xi","000000",Role.LIBRARIAN));
    }

    @Test
    public void authorize() throws Exception {
        assertEquals(
                new User("wang","123456",Role.COSTUMER)
                ,Auth.authorize("wang","123456").get());
        assertEquals(Optional.empty()
                ,Auth.authorize("xxxxxxNoSuchName----","123456"));
    }

    @Test
    public void userSignUp() throws Exception {
        assertEquals(true,Auth.userSignUp("poo","000000",Role.LIBRARIAN));
        assertEquals(new User("poo","000000",Role.LIBRARIAN)
                                ,Auth.findUser("poo").get());

        assertEquals(false,Auth.userSignUp("poo","000000",Role.LIBRARIAN));
    }

}