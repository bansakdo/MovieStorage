package com.moviestorage.moviestorage.service;


import com.moviestorage.moviestorage.repository.VideoMapper;
import com.moviestorage.moviestorage.vo.VideoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VideoService {
    @Autowired
    VideoMapper videoMapper;

    public List<VideoVO> getAllVideos() {

        List<VideoVO> videoList = videoMapper.selectAllVideos();

        for (VideoVO vo : videoList) {
            vo.createActorList();
        }

        return videoList;
    }

}
