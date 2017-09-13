package com.twu.biblioteca;


import static java.lang.System.*;
public class BibliotecaApp {
    private Store store;
    private void initialize(){
        store=Store.getNewInstance();
        store.addBook("xxxn","Crack","1990");
        store.addBook("nop jj","moel","1887");
        store.addBook("nop jj","moel","1887");
        store.addBook("nop jj","moel","1887");
        store.addBook("nop jj","moel","1887");
        store.addBook("nop jj","moel","1887");
    }
    final private static int WELCOMETIME=1200;
    void welcome(){
        out.println(asciiPic.welcome);
        try {
            Thread.sleep(WELCOMETIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void displayList(){
        out.println("\n\nbook list:\n");
        store.getAvailableBooks().forEach(out::println);
    }
    void displayActions(){
        out.println();
    }
    void actions(){

    }
    public void start(){
        welcome();
        displayList();
        actions();
    }
    public static void main(String[] args) {
        BibliotecaApp app=new BibliotecaApp();
        app.initialize();
        app.start();
    }
}
