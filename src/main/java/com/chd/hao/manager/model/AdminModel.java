package com.chd.hao.manager.model;

import java.util.List;

public class AdminModel {

    private int aid;
    private String adminName;
    private String adminpwd;
    private String phone;
    private String email;
    private String registertime;
    private List<ParkModel> parks; //创建的所有车位

    public int getId() {
        return aid;
    }

    public void setId(int aid) {
        this.aid = aid;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
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

    public void setParks(List<ParkModel> parks) {
        this.parks = parks;
    }

    public List<ParkModel> getParks() {
        return parks;
    }
}
