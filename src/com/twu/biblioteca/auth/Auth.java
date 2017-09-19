package com.twu.biblioteca.auth;

import com.twu.biblioteca.State;
import com.twu.biblioteca.Store;

import java.util.Optional;

public class Auth {
    public static Optional<Account> login(String ID, String password){
        Optional<Account> ou=findUser(ID);
        if(ou.isPresent()){
            if (ou.get().verifyPassWord(password)){
                Store store = Store.getInstance();
                store.setCurrentUser(ou.get());
                return ou;   //not empty
            }
            else return Optional.empty();
        }
        return Optional.empty();
    }
    public static boolean authorize(State state){
        Store store=Store.getInstance();
        Account u=store.getCurrentUser();
        //TODO
        if (u==null){

        }
        else if (u.getRole()==Role.COSTUMER) {

        }
        else if (u.getRole()==Role.LIBRARIAN) {

        }
        return true;
    }

    /**
     *
     * @return false:user already exists
     */
    public static boolean userSignUp(String name, String password, String id, String email, String phoneNumber) {
        Optional<Account> ou = findUser(id);
        return !ou.isPresent() && Store.getInstance().users.add(new User(name, password, id, email, phoneNumber));
    }

    static Optional<Account> findUser(String ID){
        Store store= Store.getInstance();
        return store.users.stream().filter( x->x.sameID(ID)).findFirst();
    }
}
