package com.example.kakashi.checkyourtodos;

/**
 * Created by Kakashi on 2018-02-06.
 */

public class User {
    private int userID;
    private String userPassword;
    private String userName;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public User (){

    }
    public User(String userName, String userPassword, int userID){
        this.userName = userName;
        this.userPassword = userPassword;
        this.userID = userID;
    }
    public User(String userName, String userPassword){
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


}
