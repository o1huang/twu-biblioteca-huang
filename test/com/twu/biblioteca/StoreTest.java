package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.awt.print.Book;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class StoreTest {
    Store store;
    @Before
    public void before(){
        store=new Store();
        store.addBook("xxxn","Crack","1990");
        store.addBook("nop jj","moel","1887");
    }

    @Test
    public void getAvailableBooks() throws Exception {
        assertEquals( new ArrayList<String>(asList("xxxn"+"\t"+"Crack"+"\t"+"1990", "nop jj"+"\t"+"moel"+"\t"+"1887")) , store.getAvailableBooks());
    }

    @Test
    public void addBook() throws Exception {
        BookInfo newBook=new BookInfo("newbook1","Tom","2000");
        store.addBook("newbook1","Tom","2000");
        assertEquals( newBook.name,store.books.get(store.books.size()-1).name);
    }

}