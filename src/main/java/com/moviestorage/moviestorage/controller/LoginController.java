package com.moviestorage.moviestorage.controller;

import com.moviestorage.moviestorage.service.UserService;
import com.moviestorage.moviestorage.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public String login (UserVO loginUser, HttpServletRequest request) throws Exception{

        if (loginUser.getUsername().isEmpty() || loginUser.getPassword().isEmpty())
            return "/login";

        if (!userService.isExistUser(loginUser))
            return "/login";


        loginUser = userService.retrieveUser(loginUser.getUsername());
        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_USER", loginUser);

        return "redirect:/";

    }

}
