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
        if (matcher.matches()) {
            return Integer.parseInt(str);
        } else {
            return str;
        }
    }

    static State welcomeView(){
        out.println(asciiPic.welcome);
        screenFoze();
        return State.mainMenu;
    }

    private static void screenFoze() {
        try {
            Thread.sleep(WELCOME_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static State mainMenuView(){
        out.println("====================Main Menu=================");
        out.println("Enter number to select an option;\nOr enter \"quit\" to quit biblioteca");
        out.println("1. display the book list");
        out.println("2. return a book");
        Object res = read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                return State.bookList;
            }
            else if(cmd==2){
                return State.returnBook;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) {
                return State.quit;
            }
        }
        //no option matched!
        out.println("Invalid command!");
        screenFoze();
        return State.mainMenu;

    }
    static State bookListView(){
        Store store=Store.getInstance();
        ArrayList<BookInfo> availablebooks=store.getAvailableBooks();
        out.println("------------------------------------------");
        out.println("\nBook list:");
        out.println("#\tname\t\tauthor\tyear\t");
        for (int i=1;i<=availablebooks.size();i++){
            out.println(Integer.toString(i)+"\t"+availablebooks.get(i-1).toString());
        }
        out.println("------------------------------------------");
        out.println("Enter number to select a book;\nOr enter \"quit\" to quit biblioteca");
        Object res = read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd>0&&cmd<=availablebooks.size()){
                store.selectedBookInfo=availablebooks.get(cmd-1);
                return State.bookInfo;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) {
                return State.quit;
            }
        }
        //no option matched!
        out.println("Invalid option!");
        return State.bookList;
    }
    static State bookInfoView(){
        Store store=Store.getInstance();
        BookInfo selectedBook=store.selectedBookInfo;
        out.println("------------------------------------------");
        out.println("your selected book: \n"+selectedBook.toString());
        out.println("Enter number to select an option;");
        out.println("1. checkout this book");

        Object res = read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                store.checkoutBook(selectedBook);
                out.println("Checkout succeed!");
                screenFoze();
                return State.mainMenu;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) {
                return State.quit;
            }
        }
        //no option matched!
        out.println("Invalid option!");
        screenFoze();
        return State.bookInfo;
    }
    static State returnBookView(){
        Store store=Store.getInstance();
        out.println("------------------------------------------");
        out.println("Input book's name:");
        String name = new Scanner(System.in).nextLine();
        if(name.equals("quit")) return State.quit;
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
                    return State.mainMenu;
                }
                else if(cmd==2){
                    out.println("Return book canceled!");
                    screenFoze();
                    return State.mainMenu;
                }
            }else {
                String cmd =(String)res;
                if (cmd.equals("quit")) {
                    return State.quit;
                }
            }
            out.println("Invalid option!");
            screenFoze();
            return State.mainMenu;
        }
        else{
            out.println("No such a book, please check your spelling!");
            screenFoze();
            return State.returnBook;
        }
    }
    static void run(){
        State s=State.init;
        while(true){
            switch (s){
                case init:
                    s= State.welcome;
                    break;
                case welcome:
                    s= welcomeView();
                    break;
                case mainMenu:
                    s= mainMenuView();
                    break;
                case bookList:
                    s= bookListView();
                    break;
                case bookInfo:
                    s= bookInfoView();
                    break;
                case checkoutBook:
                    break;
                case returnBook:
                    s=returnBookView();
                    break;
                case quit:
                    break;
            }
            if(s==State.quit){
                return;
            }
        }
    }
}
enum State{
    init,welcome, mainMenu,bookList,bookInfo,checkoutBook,returnBook,quit
}