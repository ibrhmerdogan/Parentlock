package com.example.ibrhm.parentlock;

/**
 * Created by ibrhm on 2.03.2017.
 */


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Class ChatUse is a Java Bean class that represents a single user.
 */
@IgnoreExtraProperties
public class User implements Serializable {

    public String id;
    public String username;
    public String email;
    public Boolean online;
    public ArrayList<String> room;
    public String selection;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String id, String username, String email, Boolean online, ArrayList<String> room,String selection) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.online = online;
        this.room = room;
        this.selection = selection;
    }

    public String getUsername() {
        return username;
    }

    public String getSelection() {
        return selection;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isOnline() {
        return online;
    }

    public ArrayList<String> getRoom() {
        return room;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSelection(String selection){this.selection = selection;}
    public void setOnline(Boolean online) {
        this.online = online;
    }

    public void setRoom(ArrayList<String> room) {
        this.room = room;
    }

}