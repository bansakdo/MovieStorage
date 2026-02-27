package com.moviestorage.moviestorage.controller;

import com.moviestorage.moviestorage.service.UserService;
import com.moviestorage.moviestorage.service.VideoService;
import com.moviestorage.moviestorage.vo.UserVO;
import com.moviestorage.moviestorage.vo.VideoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class HomeController {

    UserService userService;
    VideoService videoService;

    @Autowired
    public HomeController(
            UserService userService,
            VideoService videoService
    ) {
        this.userService = userService;
        this.videoService = videoService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserVO>> userList() throws Exception {
        log.debug("userList");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/videos")
    public ResponseEntity<List<VideoVO>> videoList() throws Exception {
        log.debug("videoList");
        return ResponseEntity.ok(videoService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideoVO>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "all") String searchType
    ) {
        log.debug("search keyword: {}, type: {}", keyword, searchType);
        List<VideoVO> results = videoService.findByTitle(keyword);
        return ResponseEntity.ok(results);
    }
}
