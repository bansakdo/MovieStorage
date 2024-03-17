package com.moviestorage.moviestorage.controller;


import com.moviestorage.moviestorage.service.VideoService;
import com.moviestorage.moviestorage.service.UserService;
import com.moviestorage.moviestorage.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class Domain {


    UserService userService;
    VideoService videoService;

    @Autowired
    public Domain(
            UserService userService
            , VideoService videoService
    ) {
        this.userService = userService;
        this.videoService = videoService;
    }


    @GetMapping({"/", "/index"})
    public String index(Model model) throws Exception {
        return "index";
    }
    @GetMapping("/index1")
    public String index1(Model model) throws Exception {
        log.debug("layoutMain");
//        model.addAttribute("users", userService.getAllUsers());
        return "index1";
    }
    @GetMapping("/layoutMain")
    public String layoutMain(Model model) throws Exception {
        log.debug("layoutMain");
//        model.addAttribute("users", userService.getAllUsers());
        return "layoutMain";
    }
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
//        List<VideoVO> list = videoService.getAllVideos();
//        for (VideoVO vo: list) {
//            log.info(vo.getTitle());
//            log.info(String.valueOf(vo.getId()));
//            log.info(String.valueOf(vo.getCasting()));
//            log.info(String.valueOf(vo.getDirectors()));
//            log.info(String.valueOf(vo.getScore()));
//            log.info(String.valueOf(vo.getAgeRate()));
//            log.info(String.valueOf(vo.getMediaType()));
//            log.info(vo.getMediaType().getClass().getName());
//            log.info(vo.getMediaType().getVideoType());
//            log.info(vo.getCast_actor().toString());
//        }
        return "videoList";
    }


    @GetMapping("/search")
    public String search(Model model) throws Exception {
        log.debug("search");



        return "searchResult";
    }


    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {


        UserVO userVO = userService.retrieveUser("user1");
        log.debug("USERNAME: " + userVO.getUsername());
        log.debug("NAME: " + userVO.getName());
        log.debug("AGE: " + userVO.getAge());



        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("LOGIN_USER") == null || session.getAttribute("LOGIN_USER").equals("")) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

}
