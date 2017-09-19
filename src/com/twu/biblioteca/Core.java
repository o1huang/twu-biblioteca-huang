package com.twu.biblioteca;

import com.twu.biblioteca.auth.Auth;
import com.twu.biblioteca.auth.Role;
import com.twu.biblioteca.auth.User;
import com.twu.biblioteca.util.Utils;
import com.twu.biblioteca.util.AsciiPic;

import static java.lang.System.out;

import java.io.Console;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Core {

    static State welcomeView(){
        out.println(AsciiPic.welcome);
        Utils.screenFoze();
        return State.SIGN_IN;
    }

    static State signInView(){
        out.println("-----------------log in-----------------");
        Console cons = System.console();
        String ID = cons.readLine("ID: ");
        String passwd = String.valueOf( cons.readPassword("password: "));
        Optional<User> ou = Auth.login(ID,passwd);
        if(ou.isPresent()){
            out.println("Log in succeeded!");
            User u=ou.get();
            Store store = Store.getInstance();
            store.setCurrentUser(u);
            Utils.screenFoze();

            if(u.getRole()== Role.COSTUMER) return State.MAIN_MENU;
            if(u.getRole() == Role.LIBRARIAN) return State.ADMIN_MAIN_MENU;

        }
        else{
            out.println("ID and password doesn't match, please try again!");
            Utils.screenFoze();
            return State.SIGN_IN;
        }
        return State.QUIT; //user is not either costumer or admin
    }

    static State adminMainMenuView() {
        out.println("====================Main Menu=================");
        out.println("Enter number to select an option;\nOr enter \"quit\" to quit biblioteca");
        out.println("1. display Lending information of books");
        out.println("#\tID\tname\tbook");


        return null;
    }


    static State mainMenuView(){
        out.println("====================Main Menu=================");
        out.println("Enter number to select an option;\nOr enter \"quit\" to quit biblioteca");
        out.println("1. display the list of available books");
        out.println("2. display the list of available movies");
        out.println("3. return a book");
        Object res = Utils.read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(1 == cmd) return State.BOOK_LIST;
            if(2 == cmd) return State.MOVIE_LIST;
            if(3 == cmd) return State.RETURN_BOOK;

        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid command!");
        Utils.screenFoze();
        return State.MAIN_MENU;

    }
    static State movieListView(){
        Store store=Store.getInstance();
        ArrayList<MovieInfo> availableMovies=store.getAvailableMovies();
        out.println("---------------- movie list -------------------");
        out.println("#\tname\t\tyear\tdirector\t\trating");
        for (int i=1;i<=availableMovies.size();i++){
            out.println(Integer.toString(i)+"\t"+availableMovies.get(i-1).toString());
        }
        out.println("Enter number to select a item;\nOr enter \"quit\" to quit biblioteca");
        Object res = Utils.read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd>0&&cmd<=availableMovies.size()){
                store.setSelectedItem(availableMovies.get(cmd-1));
                return State.ITEM_INFO;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid option!");
        return State.BOOK_LIST;
    }
    static State bookListView(){
        Store store=Store.getInstance();
        ArrayList<BookInfo> availableBooks=store.getAvailableBooks();
        out.println("-------------- book list -----------------");
        out.println("#\tname\tauthor\tyear\t");
        for (int i=1;i<=availableBooks.size();i++){
            out.println(Integer.toString(i)+"\t"+availableBooks.get(i-1).toString());
        }
        out.println("Enter number to select a item;\nOr enter \"quit\" to quit biblioteca");
        Object res = Utils.read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd>0&&cmd<=availableBooks.size()){
                store.setSelectedItem(availableBooks.get(cmd-1));
                return State.ITEM_INFO;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid option!");
        return State.BOOK_LIST;
    }
    static State itemInfoView(){
        Store store=Store.getInstance();
        Item selectedItem= store.getSelectedItem();
        out.println("----------------item info-----------------");
        out.println("your selected item: \n"+selectedItem.toString());
        out.println("Enter number to select an option;");
        out.println("1. checkout this item");
        out.println("2. cancel and back to main menu");

        Object res = Utils.read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                store.checkoutItem(selectedItem);
                out.println("Checkout succeed!");
                Utils.screenFoze();
                return State.MAIN_MENU;
            }
            if(cmd==2){
                out.println("Canceled!");
                Utils.screenFoze();
                return State.MAIN_MENU;
            }
        }else {
            if (((String)res).equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid option!");
        Utils.screenFoze();
        return State.ITEM_INFO;
    }
    static State returnBookView(){
        Store store=Store.getInstance();
        out.println("---------------return book----------------");
        out.println("Input book's name:");
        String name = new Scanner(System.in).nextLine();
        if(name.equals("quit")) return State.QUIT;
        Optional<BookInfo> ob=store.findRentedBook(name);
        if(ob.isPresent()){
            BookInfo b=ob.get();
            out.println("Are you sure to return the book: "+b.toString()+"\t?");
            out.println("1.yes");
            out.println("2.no");
            Object res = Utils.read();
            if(res instanceof Integer){
                int cmd = (int)res;
                if(cmd==1){
                    store.returnBook(b);
                    out.println("Return book succeed!");
                    Utils.screenFoze();
                    return State.MAIN_MENU;
                }
                if(cmd==2){
                    out.println("Return book canceled!");
                    Utils.screenFoze();
                    return State.MAIN_MENU;
                }
            }else {
                String cmd =(String)res;
                if (cmd.equals("quit")) return State.QUIT;
            }
            out.println("Invalid option!");
            Utils.screenFoze();
            return State.MAIN_MENU;
        }
        else{
            out.println("No such a book, please check your spelling!");
            Utils.screenFoze();
            return State.RETURN_BOOK;
        }
    }


    static void run(){
        State next_state=State.INIT;
        while(true){
            switch (next_state){
                case INIT:
                    next_state= State.WELCOME;
                    break;
                case SIGN_IN:
                    next_state = signInView();
                    break;
                case SIGN_UP:
                    break;
                case USER_INFO:
                    break;
                case WELCOME:
                    next_state= welcomeView();
                    break;
                case MAIN_MENU:
                    next_state= mainMenuView();
                    break;
                case ADMIN_MAIN_MENU:
                    next_state = adminMainMenuView();
                    break;
                case MOVIE_LIST:
                    next_state = movieListView();
                    break;
                case BOOK_LIST:
                    next_state= bookListView();
                    break;
                case ITEM_INFO:
                    next_state= itemInfoView();
                    break;
                case CHECKOUT_BOOK:
                    break;
                case RETURN_BOOK:
                    next_state=returnBookView();
                    break;
                case QUIT:
                    break;
            }
            if(Auth.authorize(next_state)){
                return;
            }
            if(next_state==State.QUIT) return;
        }
    }
}

