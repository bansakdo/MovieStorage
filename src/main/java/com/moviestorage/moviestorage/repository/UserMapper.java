package com.moviestorage.moviestorage.repository;

import com.moviestorage.moviestorage.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    public List<UserVO> selectAllUsers() throws Exception;

    public boolean isExistUser(UserVO userVO) throws Exception;

    public UserVO retrieveUser(String username) throws Exception;

}
