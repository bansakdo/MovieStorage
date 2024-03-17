package com.moviestorage.moviestorage.service;

import com.moviestorage.moviestorage.repository.UserMapper;
import com.moviestorage.moviestorage.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserVO> getAllUsers() throws Exception {
        return userMapper.selectAllUsers();
    }

    public boolean isExistUser(UserVO userVO) throws Exception {
        return userMapper.isExistUser(userVO);
    }

    public UserVO retrieveUser(String username) throws Exception {
        return userMapper.retrieveUser(username);
    }



}
