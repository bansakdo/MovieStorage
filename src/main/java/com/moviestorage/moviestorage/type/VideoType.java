package com.moviestorage.moviestorage.type;

public enum VideoType {
    TV("TV"),
    MOVIE("MOVIE");

    private String videoType;

    VideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoType() {
        return videoType;
    }
}
