package com.twu.biblioteca;

public class BibliotecaApp {
    private Store store;
    private void initialize(){
        store=Store.getNewInstance();
        store.addBook("xxxn","Crack","1990");
        store.addBook("poo boo","moel","1887");
        store.addBook("tau ao","moel","1887");
        store.addBook("kazuya","moel","1887");
        store.addBook("WDFD","moel","1887");
        store.addBook("lastOfus","moel","1887");
        store.addMovie("Dragon Lore","2014","Gabe Newell","7");
        store.addMovie("Friend Request","2001","Simon Verhoeven","5");
        store.addMovie("Milo Francisco","2002","Vello Maxson","6");
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
