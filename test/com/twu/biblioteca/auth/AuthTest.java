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
        store.users.add(new User("wang","123456", "000-1110", "Z@bil.com", "112112212"));
        store.users.add(new User("xi","000000", "000-1114", "Z@bil.com", "112112212"));
    }

    @Test
    public void lgoin() throws Exception {
        assertEquals(
                new User("wang","123456", "000-1110", "Z@bil.com", "112112212")
                ,Auth.login("000-1110","123456").get());
        assertEquals(Optional.empty()
                ,Auth.login("xxxxxxNoSuchName----","123456"));
    }

    @Test
    public void userSignUp() throws Exception {
        assertEquals(true,Auth.userSignUp("poo","000000", "000-1130", "Z@bil.com", "112112212"));
        assertEquals(new User("poo","000000", "000-1130", "Z@bil.com", "112112212")
                                ,Auth.findUser("000-1130").get());

        assertEquals(false,Auth.userSignUp("poo","000000", "000-1130", "Z@bil.com", "112112212"));
    }

}