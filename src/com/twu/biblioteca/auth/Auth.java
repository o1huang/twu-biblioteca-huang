package com.twu.biblioteca.auth;

import com.twu.biblioteca.State;
import com.twu.biblioteca.Store;

import java.util.Optional;

public class Auth {
    public static Optional<User> login(String ID, String password){
        Optional<User> ou=findUser(ID);
        if(ou.isPresent()){
            if (ou.get().verifyPassWord(password)){
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
        Optional<User> ou = findUser(id);
        return !ou.isPresent() && Store.getInstance().users.add(new User(name, password, id, email, phoneNumber));
    }

    static Optional<User> findUser(String ID){
        Store store= Store.getInstance();
        return store.users.stream().filter( x->x.sameID(ID)).findFirst();
    }
}
