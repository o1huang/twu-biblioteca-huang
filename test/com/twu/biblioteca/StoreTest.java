package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class StoreTest {
    Store store;
    @Before
    public void before(){
        store=Store.getNewInstance();
        store.addBook("xxxn","Crack","1990");
        store.addBook("nop jj","moel","1887");
    }

    @Test
    public void getAvailableBooks() throws Exception {
        assertEquals(2 , store.getAvailableBooks().size());
    }

    @Test
    public void addBook() throws Exception {
        BookInfo newBook=new BookInfo("newbook1","Tom","2000");
        store.addBook("newbook1","Tom","2000");
        assertEquals( newBook.name,store.books.get(store.books.size()-1).name);
    }

}