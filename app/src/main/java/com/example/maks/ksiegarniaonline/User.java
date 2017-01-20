package com.example.maks.ksiegarniaonline;

/**
 * Created by MAKS on 19.01.2017.
 */

public class User {

    private String id;
    private String userLogin;
    private String userPassword;

    public User(String id, String userLogin, String userPassword) {
        this.id = id;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", login: " + getUserLogin() + ", pass: " + getUserPassword();
    }
}
