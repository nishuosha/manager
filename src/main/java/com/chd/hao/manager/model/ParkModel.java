package com.chd.hao.manager.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanghao68 on 2018/4/24
 */
public class ParkModel {

    private int pid;
    private String name; //名称
    private String address; //地址
    private Integer enterCount;  //入口数量
    private Map<String, String> coordinate; // 入口坐标
    private int length; //横
    private int width; //竖
    private int count; //车位总数
    private int free; //空闲车位
    private int[][] num; //车位编号
    private String time; //创建时间
    private String description; //描述
    private AdminModel sponsor; //创建者


    public void init() {

        this.count = length * width;
        this.free = count;
    }

    public int getId() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEnterCount() {
        return enterCount;
    }

    public void setEnterCount(Integer enterCount) {
        this.enterCount = enterCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCount() {
        return count;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getFree() {
        return free;
    }

    public int[][] getNum() {
        return num;
    }

    public void setNum(int[][] num) {
        this.num = num;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSponsor(AdminModel sponsor) {
        this.sponsor = sponsor;
    }

    public AdminModel getSponsor() {
        return sponsor;
    }

    public Map<String, String> getCoordinate() {
        this.coordinate = new HashMap<>();
        coordinate.put("A", "0,0");
        coordinate.put("B", "0," + (length - 1));
        coordinate.put("C", width - 1 + ",0");
        if(enterCount == 4) {
            coordinate.put("D", (width - 1) + "," + (length - 1));
        }
        return coordinate;
    }
}
