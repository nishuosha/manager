package com.chd.hao.manager.model;

public class UserModel {

    private int uid;
    private String username;
    private String userpwd;
    private String phone;
    private String email;
    private String registertime;

    public int getId() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public String getRegistertime() {
        return registertime;
    }
}
