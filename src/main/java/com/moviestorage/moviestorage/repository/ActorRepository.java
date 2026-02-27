package com.moviestorage.moviestorage.repository;

import com.moviestorage.moviestorage.vo.ActorVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<ActorVO, Long> {

    public ActorVO findActorById(long id);

    public List<ActorVO> findActorByName(String name);



}
