package com.moviestorage.moviestorage.controller;


import com.moviestorage.moviestorage.service.VideoService;
import com.moviestorage.moviestorage.service.UserService;
import com.moviestorage.moviestorage.vo.VideoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class Domain {


    @Autowired
    UserService userService;
    @Autowired
    VideoService videoService;

    @GetMapping("/userList")
    public String userList(Model model) throws Exception {

        log.debug("userList");
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @GetMapping("/videoList")
    public String videoList(Model model) throws Exception {
        log.debug("videoList");
        model.addAttribute("videos", videoService.getAllVideos());
        List<VideoVO> list =  videoService.getAllVideos();
        for (VideoVO vo: list) {
            log.info(vo.getTitle());
            log.info(String.valueOf(vo.getId()));
            log.info(String.valueOf(vo.getCasting()));
            log.info(String.valueOf(vo.getDirectors()));
            log.info(String.valueOf(vo.getScore()));
            log.info(String.valueOf(vo.getAgeRate()));
            log.info(String.valueOf(vo.getMediaType()));
            log.info(vo.getMediaType().getClass().getName());
            log.info(vo.getMediaType().getVideoType());
            log.info(vo.getCast_actor().toString());
        }
        return "videoList";
    }


}
