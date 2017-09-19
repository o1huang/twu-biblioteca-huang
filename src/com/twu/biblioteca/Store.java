package com.twu.biblioteca;

import com.sun.istack.internal.Nullable;
import com.twu.biblioteca.auth.Account;
import com.twu.biblioteca.auth.User;

import java.util.ArrayList;
import java.util.List;
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
    private Account currentUser=null;



    //store filed
    public ArrayList<BookInfo> books=new ArrayList<>();

    public ArrayList<User> users=new ArrayList<>(); //

    public ArrayList<MovieInfo> movies=new ArrayList<>();

    public ArrayList<Loan> loans=new ArrayList();


    //methods

    //loan infos
    private void addLoan(User u,Item i){
        loans.add(new Loan(u.getID(),i.getName()));
    }
    private void delLoan(User u,Item i){
        loans = (ArrayList<Loan>)loans.stream()
                .filter(l->!(l.userID.equals(u.getID()) && l.itemName.equals(i.getName())))
                .collect(Collectors.toList());
    }
    public List<String> getLoan(){}

    public boolean checkoutItem(Item item){
        Optional<BookInfo> storeBook = books.stream().filter(x->x.equals(item)).findFirst();
        if(storeBook.isPresent()){
            storeBook.get().rent();
            addLoan((User)this.getCurrentUser(),item);
            return true;
        }
        Optional<MovieInfo> storeMovie = movies.stream().filter(x->x.equals(item)).findFirst();
        if(storeMovie.isPresent()){
            storeMovie.get().rent();
            addLoan((User)this.getCurrentUser(),item);
            return true;
        }
        return false;
    }
    //book
    public ArrayList<BookInfo> getAvailableBooks(){
        ArrayList<BookInfo> availableBooks =(ArrayList<BookInfo> )books.stream()
                                .filter(b->!b.isBeenRented())
                                .collect(Collectors.toList());
        return availableBooks;
    }

    public void addBook(String name,String author,String year){
        books.add(new BookInfo(name,author,year));
    }



    public boolean returnBook(BookInfo b){
        Optional<BookInfo> storeBook = books.stream().filter(x->x.equals(b)).findFirst();
        if(storeBook.isPresent()){
            storeBook.get().returns();
            delLoan((User)this.getCurrentUser(),b);
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

    @Nullable
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

