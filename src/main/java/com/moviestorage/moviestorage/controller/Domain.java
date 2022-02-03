package com.moviestorage.moviestorage.controller;


import com.moviestorage.moviestorage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class Domain {

    @Autowired
    UserService userService;

    @GetMapping("/userList")
    public String userList(Model model) throws Exception {
        log.debug("userList");
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }


}
