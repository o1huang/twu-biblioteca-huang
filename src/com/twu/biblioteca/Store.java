package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.stream.Collectors;
class Store {
    //singleton instance
    private static Store singleton=new Store();

    public static Store getInstance() {
        return singleton;
    }
    public static Store getNewInstance() {
        singleton=new Store();
        return singleton;
    }

    //state filed
    String infoMassage;
    ArrayList<BookInfo> books=new ArrayList<>();

    public ArrayList<String>  getAvailableBooks(){
        ArrayList<String> availableBooks =(ArrayList<String> )books.stream()
                                .filter(b->!b.beenRented)
                                .map( b->b.name+"\t"+b.author+"\t"+b.year )
                                .collect(Collectors.toList());
        return availableBooks;
    }

    public void addBook(ArrayList<BookInfo> vbooks){
        books.addAll(vbooks);
    }
    public void addBook(String name,String author,String year){
        books.add(new BookInfo(name,author,year));
    }


}

