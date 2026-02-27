package com.moviestorage.moviestorage.repository;

import com.moviestorage.moviestorage.vo.ActorVO;
import com.moviestorage.moviestorage.vo.VideoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoVO, Long> {

    public VideoVO findById(long id);

    public List<VideoVO> findByTitle(String title);

    public List<VideoVO> findAllByTitleContaining(String title);

    public List<VideoVO> findByCastActorsContaining(ActorVO actorVO);
}
