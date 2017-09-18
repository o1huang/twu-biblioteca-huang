package com.twu.biblioteca.auth;

import com.twu.biblioteca.Store;

import java.util.Optional;

public class Auth {
    public static Optional<User> authorize(String name,String password){
        Optional<User> ou=findUser(name);
        if(ou.isPresent()){
            if (ou.get().verifyPassWord(password)){
                return ou;   //not empty
            }
            else return Optional.empty();
        }
        return Optional.empty();
    }

    /**
     *
     * @return false:user already exists
     */
    public static boolean userSignUp(String name, String password, Role role,String id, String email, String phoneNumber) {
        Optional<User> ou = findUser(name);
        return !ou.isPresent() && Store.getInstance().users.add(new User(name, password, role, id, email, phoneNumber));
    }

    static Optional<User> findUser(String name){
        Store store= Store.getInstance();
        return store.users.stream().filter( x->x.sameName(name)).findFirst();
    }
}
