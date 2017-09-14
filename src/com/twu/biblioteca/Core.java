package com.twu.biblioteca;

import static java.lang.System.out;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Core {
    final private static int WELCOME_TIME=1200;
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
        Object res = read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                return State.bookList;
            }
        }else {
            String cmd =(String)res;
            if (cmd.equals("quit")) {
                return State.quit;
            }
        }
        //no option matched!
        out.println("Invalid command!");
        return State.optionList;

    }
    static State displayBookList(){
        out.println("\nbook list:");
        out.println("#\tname\t\tauthor\tyear\t");
        Store store=Store.getInstance();
        store.getAvailableBooks().forEach(out::println);
        out.println("------------------------------------------");
        out.println("Enter number to select a book;\nOr enter \"quit\" to quit biblioteca");
        Object res = read();
        if(res instanceof Integer){
            int cmd = (int)res;
            if(cmd==1){
                return State.bookList;
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