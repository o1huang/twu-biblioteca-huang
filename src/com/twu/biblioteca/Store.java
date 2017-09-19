package com.twu.biblioteca;

import com.twu.biblioteca.auth.Account;
import com.twu.biblioteca.auth.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
public class Store {
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
    private Item selectedItem;
    public Account currentUser;



    //store filed
    public ArrayList<BookInfo> books=new ArrayList<>();

    public ArrayList<User> users=new ArrayList<>(); //

    public ArrayList<MovieInfo> movies=new ArrayList<>();


    //methods
    public ArrayList<BookInfo> getAvailableBooks(){
        ArrayList<BookInfo> availableBooks =(ArrayList<BookInfo> )books.stream()
                                .filter(b->!b.isBeenRented())
                                .collect(Collectors.toList());
        return availableBooks;
    }

    public void addBook(ArrayList<BookInfo> books){
        this.books.addAll(books);
    }
    public void addBook(String name,String author,String year){
        books.add(new BookInfo(name,author,year));
    }


    public boolean checkoutItem(Item item){
        Optional<BookInfo> storeBook = books.stream().filter(x->x.equals(item)).findFirst();
        if(storeBook.isPresent()){
            storeBook.get().rent();
            return true;
        }
        Optional<MovieInfo> storeMovie = movies.stream().filter(x->x.equals(item)).findFirst();
        if(storeMovie.isPresent()){
            storeMovie.get().rent();
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
        return books.stream().filter(x->x.isBeenRented()&&x.getName().equals(bookName)).findFirst();
    }



    //movie

    public ArrayList<MovieInfo> getAvailableMovies(){
        ArrayList<MovieInfo> availableMovies =(ArrayList<MovieInfo> )this.movies.stream()
                .filter(b->!b.isBeenRented())
                .collect(Collectors.toList());
        return availableMovies;
    }

    public void addMovie(String name, String year, String director, String rating){
        this.movies.add(new MovieInfo(name,year,director,rating));
    }


    public boolean checkoutMovie(MovieInfo m){
        Optional<MovieInfo> o_movie = movies.stream().filter(x->x.equals(m)).findFirst();
        if(o_movie.isPresent()){
            o_movie.get().rent();
            return true;
        }
        return false;
    }

    public boolean returnMovie(MovieInfo m){
        Optional<MovieInfo> o_movie = movies.stream().filter(x->x.equals(m)).findFirst();
        if(o_movie.isPresent()){
            o_movie.get().returns();
            return true;
        }
        return false;
    }
    public Optional<MovieInfo> findRentedMovie(String name){
        return movies.stream().filter(x->x.isBeenRented()&&x.getName().equals(name)).findFirst();
    }


    //get,set
    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
}

