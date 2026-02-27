package com.moviestorage.moviestorage.type;

import lombok.Getter;

// Male, Female, Non_binary
@Getter
public enum GenderType {
    M("MALE"),
    F("FEMALE"),
    N("NON_BINARY");

    private String gender;

    GenderType(String gender) {
        this.gender = gender;
    }

//    public String getSex() {
//        return sex;
//    }
}
