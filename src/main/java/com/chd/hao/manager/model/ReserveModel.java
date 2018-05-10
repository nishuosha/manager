package com.chd.hao.manager.model;

/**
 * Created by zhanghao68 on 2018/4/28
 */
public class ReserveModel {

    private int rid;
    private String createtime; //创建时间
    private String reservetime; //预定时间
    private String description; //描述
    private UserModel user; //用户预定者
    private AdminModel admin; //商家预定者
    private ParkModel park; //车库
    private int parknum; //车位编号
    private String status; // 状态，已预定，已停车，已过期

    public int getId() {
        return rid;
    }

    public void setId(int rid) {
        this.rid = rid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getReservetime() {
        return reservetime;
    }

    public void setResevertime(String reservetime) {
        this.reservetime = reservetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public AdminModel getAdmin() {
        return admin;
    }

    public void setAdmin(AdminModel admin) {
        this.admin = admin;
    }

    public ParkModel getPark() {
        return park;
    }

    public void setPark(ParkModel park) {
        this.park = park;
    }

    public int getParknum() {
        return parknum;
    }

    public void setParknum(int parknum) {
        this.parknum = parknum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
