package com.ittalents.mynotice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ASUS on 18.3.2017 г..
 */

public class User implements Serializable{

    private String username;
    private String password;
    private String email;
    private boolean isSeller;
    private ArrayList<Notice> notices;

    public User(String username, String password, String email, boolean isSeller) {
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        this.isSeller = isSeller;
        this.notices = new ArrayList<Notice>();
    }

    public void addNotice (Notice n) {
        if (n != null) {
            this.notices.add(n);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public List<Notice> getNotices() {
        return Collections.unmodifiableList(notices);
    }


}
