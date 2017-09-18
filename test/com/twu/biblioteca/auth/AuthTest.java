package com.twu.biblioteca.auth;

import com.twu.biblioteca.Store;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class AuthTest {

    Store store;
    @Before
    public void before(){
        store=Store.getNewInstance();
        store.users.add(new User("wang","123456",Role.COSTUMER, "000-1110", "Z@bil.com", "112112212"));
        store.users.add(new User("xi","000000",Role.LIBRARIAN, "000-1114", "Z@bil.com", "112112212"));
    }

    @Test
    public void authorize() throws Exception {
        assertEquals(
                new User("wang","123456",Role.COSTUMER,"000-1110", "Z@bil.com", "112112212")
                ,Auth.authorize("wang","123456").get());
        assertEquals(Optional.empty()
                ,Auth.authorize("xxxxxxNoSuchName----","123456"));
    }

    @Test
    public void userSignUp() throws Exception {
        assertEquals(true,Auth.userSignUp("poo","000000",Role.LIBRARIAN,"000-1130", "Z@bil.com", "112112212"));
        assertEquals(new User("poo","000000",Role.LIBRARIAN,"000-1130", "Z@bil.com", "112112212")
                                ,Auth.findUser("poo").get());

        assertEquals(false,Auth.userSignUp("poo","000000",Role.LIBRARIAN,"000-1130", "Z@bil.com", "112112212"));
    }

}