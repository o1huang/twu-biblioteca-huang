package com.twu.biblioteca;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Core {
    final private static int WELCOME_TIME=800;
    private static Object read(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher((CharSequence) str);
        if (matcher.matches()) return Integer.parseInt(str);
        else return str;
    }

    private static void screenFoze() {
        try {
            Thread.sleep(WELCOME_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static State welcomeView(){
        out.println(asciiPic.welcome);
        screenFoze();
        return State.MAIN_MENU;
    }

    static State mainMenuView(){
        out.println("====================Main Menu=================");
        out.println("Enter number to select an option;\nOr enter \"quit\" to quit biblioteca");
        out.println("1. display the list of available books");
        out.println("2. return a book");
        Object res = read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                return State.BOOK_LIST;
            }
            else if(cmd==2){
                return State.RETURN_BOOK;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid command!");
        screenFoze();
        return State.MAIN_MENU;

    }
    static State bookListView(){
        Store store=Store.getInstance();
        ArrayList<BookInfo> availableBooks=store.getAvailableBooks();
        out.println("------------------------------------------");
        out.println("\nBook list:");
        out.println("#\tname\t\tauthor\tyear\t");
        for (int i=1;i<=availableBooks.size();i++){
            out.println(Integer.toString(i)+"\t"+availableBooks.get(i-1).toString());
        }
        out.println("------------------------------------------");
        out.println("Enter number to select a book;\nOr enter \"quit\" to quit biblioteca");
        Object res = read();
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
        out.println("------------------------------------------");
        out.println("your selected book: \n"+selectedBook.toString());
        out.println("Enter number to select an option;");
        out.println("1. checkout this book");
        out.println("2. cancel and back to main menu");

        Object res = read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                store.checkoutBook(selectedBook);
                out.println("Checkout succeed!");
                screenFoze();
                return State.MAIN_MENU;
            }
            else if(cmd==2){
                out.println("Canceled!");
                screenFoze();
                return State.MAIN_MENU;
            }
        }else {
            if (((String)res).equals("quit")) return State.QUIT;
        }
        //no option matched!
        out.println("Invalid option!");
        screenFoze();
        return State.BOOK_INFO;
    }
    static State returnBookView(){
        Store store=Store.getInstance();
        out.println("------------------------------------------");
        out.println("Input book's name:");
        String name = new Scanner(System.in).nextLine();
        if(name.equals("quit")) return State.QUIT;
        Optional<BookInfo> ob=store.findRentedBook(name);
        if(ob.isPresent()){
            BookInfo b=ob.get();
            out.println("Are you sure to return the book: "+b.toString()+"\t?");
            out.println("1.yes");
            out.println("2.no");
            Object res = read();
            if(res instanceof Integer){
                int cmd = (int)res;
                if(cmd==1){
                    store.returnBook(b);
                    out.println("Return book succeed!");
                    screenFoze();
                    return State.MAIN_MENU;
                }
                else if(cmd==2){
                    out.println("Return book canceled!");
                    screenFoze();
                    return State.MAIN_MENU;
                }
            }else {
                String cmd =(String)res;
                if (cmd.equals("quit")) return State.QUIT;
            }
            out.println("Invalid option!");
            screenFoze();
            return State.MAIN_MENU;
        }
        else{
            out.println("No such a book, please check your spelling!");
            screenFoze();
            return State.RETURN_BOOK;
        }
    }
    static void run(){
        State next_state=State.STATE;
        while(true){
            switch (next_state){
                case STATE:
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
    STATE, WELCOME, MAIN_MENU, BOOK_LIST, BOOK_INFO, CHECKOUT_BOOK, RETURN_BOOK, QUIT
}