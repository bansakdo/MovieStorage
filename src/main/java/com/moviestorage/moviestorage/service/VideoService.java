package com.moviestorage.moviestorage.service;


import com.moviestorage.moviestorage.repository.VideoRepository;
import com.moviestorage.moviestorage.vo.VideoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VideoService {
//    private final VideoMapper videoMapper;
    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<VideoVO> findAll() {
        return new ArrayList<>(videoRepository.findAll());
    }

    public VideoVO findById(long id) {
        return videoRepository.findById(id);
    }

    public List<VideoVO> findByTitle(String title) {
        return new ArrayList<>(videoRepository.findAllByTitleContaining(title));
    }

    public VideoVO save(VideoVO videoVO) {
        return videoRepository.save(videoVO);
    }



    //    @Autowired
//    public VideoService(VideoMapper videoMapper) {
//        this.videoMapper = videoMapper;
//    }

/*
    public List<VideoVO> getAllVideos() {

        List<VideoVO> videoList = videoMapper.selectAllVideos();

//        for (VideoVO vo : videoList) {
//            vo.createActorList();
//        }

        return videoList;
    }

    public List<VideoVO> searchVideos(String keyword) {

        List<VideoVO> videoList = videoMapper.selectVideos();

        return videoList;
    }
*/

}
