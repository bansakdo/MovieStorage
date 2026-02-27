package com.moviestorage.moviestorage.type;

public enum MediaType {
    TV("TV"),
    MOVIE("MOVIE")
    ;

    private String videoType;

    MediaType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoType() {
        return videoType;
    }
}
