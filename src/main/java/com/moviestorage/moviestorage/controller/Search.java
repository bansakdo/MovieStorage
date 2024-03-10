package com.moviestorage.moviestorage.controller;

import com.moviestorage.moviestorage.vo.SearchAllVO;
import com.moviestorage.moviestorage.vo.VideoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Search {

    @GetMapping("/All/{keyword}")
    public SearchAllVO searchAll(@PathVariable String Keyword){

        SearchAllVO result = new SearchAllVO();

        return result;
    }


    @GetMapping("video/{keyword}")
    public VideoVO searchVideo(String keyword) {

        VideoVO result = new VideoVO();

        return result;
    }
}
