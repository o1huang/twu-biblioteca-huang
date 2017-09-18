package com.twu.biblioteca.auth;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void verifyPassWord() throws Exception {
        User u =new User("wang","123456",Role.COSTUMER);
        assertEquals(true,u.verifyPassWord("123456"));
        assertEquals(false,u.verifyPassWord("xxxxxx"));
    }

}