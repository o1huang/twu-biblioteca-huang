package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class StoreTest {

    Store store;
    @Before
    public void before(){
        store=Store.getNewInstance();
        store.addBook("xxxn","Crack","1990");
        store.addBook("nop jj","moel","1887");
        store.addMovie("Dragon Lore","2014","Gabe Newell","7");
        store.addMovie("Friend Request","2001","Simon Verhoeven","5");
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

    @Test
    public void checkoutAndReturnBook() throws Exception {
        store.checkoutBook(new BookInfo("xxxn","Crack","1990"));
        assertEquals("[nop jj\tmoel\t1887]",store.getAvailableBooks().toString());
        store.returnBook(new BookInfo("xxxn","Crack","1990"));
        assertEquals("[xxxn\tCrack\t1990, nop jj\tmoel\t1887]",store.getAvailableBooks().toString());
    }


    @Test
    public void checkoutAndReturnMovie() throws Exception {
        store.checkoutMovie(new MovieInfo("Dragon Lore","2014","Gabe Newell","7"));
        assertEquals( "[Friend Request\t2001\tSimon Verhoeven\t5]",store.getAvailableMovies().toString());
        store.returnMovie(new MovieInfo("Dragon Lore","2014","Gabe Newell","7"));
        assertEquals( "[Dragon Lore\t2014\tGabe Newell\t7, Friend Request\t2001\tSimon Verhoeven\t5]"
                        ,store.getAvailableMovies().toString());
    }


}