package com.twu.biblioteca.auth;

import com.twu.biblioteca.Store;

import java.util.Optional;

public class Auth {
    public static Optional<User> authorize(String name,String password){
        Store store= Store.getInstance();
        Optional<User> ou=findUser(name);
        if(ou.isPresent()){
            if (ou.get().verifyPassWord(password)){
                return ou;   //not empty
            }
            else return Optional.empty();
        }
        return Optional.empty(); // empty value
    }

    /**
     *
     * @return false:user already exists
     */
    public static boolean userSignUp(String name, String password, Role role){
        Optional<User> ou=findUser(name);
        if( ou.isPresent()){
            return false;           // user exists
        }
        return Store.getInstance().users.add(new User(name,password,role));
    }

    protected static Optional<User> findUser(String name){
        Store store= Store.getInstance();
        return store.users.stream().filter( x->x.sameName(name)).findFirst();
    }
}
