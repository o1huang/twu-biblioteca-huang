package com.twu.biblioteca;

import com.twu.biblioteca.auth.Auth;
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
        return State.MAIN_MENU;
    }

    static State LoginView(){
        out.println("-----------------log in-----------------");
        Console cons = System.console();
        String ID = cons.readLine("ID: ");
        String passwd = String.valueOf( cons.readPassword("password: "));
        Optional<User> ou = Auth.authorize(ID,passwd);
        if(ou.isPresent()){
            out.println("Log in succeeded!");
        }
        else{
            out.println("ID and password doesn't match, please try again!");
            Utils.screenFoze();
            return State.SIGN_IN;
        }
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
            if(cmd==1){
                return State.BOOK_LIST;
            }
            else if(cmd==2){

            }
            else if(cmd==3){
                return State.RETURN_BOOK;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid command!");
        Utils.screenFoze();
        return State.MAIN_MENU;

    }
    static State bookListView(){
        Store store=Store.getInstance();
        ArrayList<BookInfo> availableBooks=store.getAvailableBooks();
        out.println("-------------- Book list -----------------");
        out.println("#\tname\tauthor\tyear\t");
        for (int i=1;i<=availableBooks.size();i++){
            out.println(Integer.toString(i)+"\t"+availableBooks.get(i-1).toString());
        }
        out.println("Enter number to select a book;\nOr enter \"quit\" to quit biblioteca");
        Object res = Utils.read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd>0&&cmd<=availableBooks.size()){
                store.selectedBookInfo=availableBooks.get(cmd-1);
                return State.BOOK_INFO;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid option!");
        return State.BOOK_LIST;
    }
    static State bookInfoView(){
        Store store=Store.getInstance();
        BookInfo selectedBook=store.selectedBookInfo;
        out.println("----------------book info-----------------");
        out.println("your selected book: \n"+selectedBook.toString());
        out.println("Enter number to select an option;");
        out.println("1. checkout this book");
        out.println("2. cancel and back to main menu");

        Object res = Utils.read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                store.checkoutBook(selectedBook);
                out.println("Checkout succeed!");
                Utils.screenFoze();
                return State.MAIN_MENU;
            }
            else if(cmd==2){
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
        return State.BOOK_INFO;
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
                else if(cmd==2){
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
                case WELCOME:
                    next_state= welcomeView();
                    break;
                case MAIN_MENU:
                    next_state= mainMenuView();
                    break;
                case BOOK_LIST:
                    next_state= bookListView();
                    break;
                case BOOK_INFO:
                    next_state= bookInfoView();
                    break;
                case CHECKOUT_BOOK:
                    break;
                case RETURN_BOOK:
                    next_state=returnBookView();
                    break;
                case QUIT:
                    break;
            }
            if(next_state==State.QUIT) return;
        }
    }
}
enum State{
    INIT,SIGN_IN,SIGN_UP, WELCOME, MAIN_MENU, BOOK_LIST, BOOK_INFO, CHECKOUT_BOOK, RETURN_BOOK, QUIT
}