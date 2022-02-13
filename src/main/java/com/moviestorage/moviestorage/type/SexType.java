package com.moviestorage.moviestorage.type;

// Male, Female, Non_binary
public enum SexType {
    M("MALE"),
    F("FEMALE"),
    N("NON_BINARY");

    private String sex;

    SexType(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
