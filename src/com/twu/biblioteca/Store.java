package com.twu.biblioteca;

import jdk.nashorn.internal.runtime.options.Option;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Optional;
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
    public String infoMassage;
    ArrayList<BookInfo> books=new ArrayList<>();
    public int optionNum;
    public int bookIndex;
    public String bookName;
    public BookInfo selectedBookInfo;

    public ArrayList<BookInfo> getAvailableBooks(){
        ArrayList<BookInfo> availableBooks =(ArrayList<BookInfo> )books.stream()
                                .filter(b->!b.beenRented)
                                .collect(Collectors.toList());
        return availableBooks;
    }

    public void addBook(ArrayList<BookInfo> vbooks){
        books.addAll(vbooks);
    }
    public void addBook(String name,String author,String year){
        books.add(new BookInfo(name,author,year));
    }
    public boolean checkoutBook(BookInfo b){
        Optional<BookInfo> storeBook = books.stream().filter(x->x.equals(b)).findFirst();
        if(storeBook.isPresent()){
            storeBook.get().rent();
            return true;
        }
        return false;
    }
    public boolean returnBook(BookInfo b){
        Optional<BookInfo> storeBook = books.stream().filter(x->x.equals(b)).findFirst();
        if(storeBook.isPresent()){
            storeBook.get().returns();
            return true;
        }
        return false;
    }
    public Optional<BookInfo> findRentedBook(String bookName){
        return books.stream().filter(x->x.beenRented&&x.name.equals(bookName)).findFirst();
    }
}

