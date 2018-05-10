package com.chd.hao.manager.util;

public enum Type {

    Location("网络定位", "allowLocationList"),
    IPLocation("IP定位", "allowIpLocationList"),
    ReverseGeocoderLocation("逆地理定位", "allowReverseGeocoderLocationList");

    private String key;
    private String value;

    private Type(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
