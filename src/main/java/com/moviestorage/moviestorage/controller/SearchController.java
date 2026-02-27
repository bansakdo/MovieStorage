package com.moviestorage.moviestorage.controller;

import com.moviestorage.moviestorage.vo.SearchAllVO;
import com.moviestorage.moviestorage.vo.VideoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SearchController {

    @GetMapping("/search/all/{keyword}")
    public SearchAllVO searchAll(@PathVariable String keyword){

        SearchAllVO result = new SearchAllVO();

        return result;
    }


    @GetMapping("/search/video/{keyword}")
    public VideoVO searchVideo(@PathVariable String keyword) {

        VideoVO result = new VideoVO();

        return result;
    }
}
