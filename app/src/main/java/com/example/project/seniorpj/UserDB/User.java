package com.example.project.seniorpj.UserDB;

import android.provider.BaseColumns;

/**
 * Created by Smew on 15/9/2560.
 */

public class User {

    public static final String TABLE_NAME = "user";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String AGE = "age";
        public static final String GENDER = "gender";
    }

    private int id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String gender;

    // Constructor
    public User(String username, String password, String email, String age, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}