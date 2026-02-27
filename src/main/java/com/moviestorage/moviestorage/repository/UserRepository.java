package com.moviestorage.moviestorage.repository;

import com.moviestorage.moviestorage.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {

    public UserVO findUserById(long id);

    public List<UserVO> findUserByName(String name);


}
