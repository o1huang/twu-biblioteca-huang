package com.twu.biblioteca;

import static java.lang.System.out;
import java.util.Scanner;
public class Core {
    final private static int WELCOME_TIME=1200;
    private static String read(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    static State welcome(){
        out.println(asciiPic.welcome);
        try {
            Thread.sleep(WELCOME_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return State.optionList;
    }
    static State displayMainMenu(){
        out.println("====================Main Menu=================");
        out.println("Enter number to select an option;\nOr enter \"quit\" to quit biblioteca");
        out.println("1. Display the book list");
        String cmd = read();
        if(cmd.equals("quit")){
            return State.quit;
        }
        else if(cmd.equals("1")){
            return State.bookList;
        }
        else {
            out.println("Invalid option!");
            return State.optionList;
        }

    }
    static State displayBookList(){
        out.println("\nbook list:");
        out.println("#\tname\t\tauthor\tyear\t");
        Store store=Store.getInstance();
        store.getAvailableBooks().forEach(out::println);
        out.println("------------------------------------------");
        out.println("Enter number to select a book;\nOr enter \"quit\" to quit biblioteca");
        String cmd = read();
        if(cmd.equals("quit")){
            return State.quit;
        }
        else if(cmd.equals("1")){
            return State.bookList;
        }
        else {
            out.println("Invalid option!");
            return State.optionList;
        }
    }
    static void checkoutBooks(){
        out.println();
    }
    static void run(){
        State s=State.init;
        while(true){
            switch (s){
                case init:
                    s= State.welcome;
                    break;
                case welcome:
                    s=welcome();
                    break;
                case optionList:
                    s=displayMainMenu();
                    break;
                case bookList:
                    s=displayBookList();
                    break;
                case checkoutBook:
                    break;
                case returnBook:
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
    init,welcome,optionList,bookList,checkoutBook,returnBook,quit
}