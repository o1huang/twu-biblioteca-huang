package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class CoreTest {
    @Test
    public void testJava() throws Exception {
        assertEquals(Integer.parseInt("-03"),-3);
        assertEquals(new BookInfo("1","1","1").equals(new BookInfo("1","1","1")),true);
    }
}