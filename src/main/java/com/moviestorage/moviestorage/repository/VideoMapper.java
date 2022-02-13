package com.moviestorage.moviestorage.repository;

import com.moviestorage.moviestorage.vo.VideoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoMapper {

    public List<VideoVO> selectAllVideos();

}
