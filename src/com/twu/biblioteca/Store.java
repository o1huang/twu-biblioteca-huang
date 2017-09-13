package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.stream.Collectors;
public class Store {
    String infoMassage;
    ArrayList<BookInfo> books=new ArrayList<BookInfo>();
    BookInfo b=new BookInfo("zbbb","Franklin","1990");
    public ArrayList<String>  getAvailableBooks(){
        ArrayList<String> availableBooks =(ArrayList<String> )books.stream()
                                .filter(b->b.beenRented==false)
                                .map( b->b.name+"\t"+b.author+"\t"+b.year )
                                .collect(Collectors.toList());
        return availableBooks;
    }
    public void addBook(String name,String author,String year){
        books.add(new BookInfo(name,author,year));
    }
    Store(ArrayList<BookInfo> vbooks){
        for (BookInfo b : vbooks) books.add(b);
    }
    Store(){

    }
}

