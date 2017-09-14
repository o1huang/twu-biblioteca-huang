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

    public void start(){
        Core.run();
    }
    public static void main(String[] args) {
        BibliotecaApp app=new BibliotecaApp();
        app.initialize();
        app.start();
    }
}
