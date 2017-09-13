package com.twu.biblioteca;

class BookInfo {
    String name;
    String author;
    String year;
    boolean beenRented=false;
    BookInfo(String vname, String vauthor, String vyear){
        name=vname;
        author=vauthor;
        year=vyear;
    }
}
