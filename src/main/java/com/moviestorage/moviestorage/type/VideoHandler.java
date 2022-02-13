package com.moviestorage.moviestorage.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoHandler implements TypeHandler<VideoType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, VideoType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getVideoType());
    }

    @Override
    public VideoType getResult(ResultSet rs, String columnName) throws SQLException {
        return VideoType.valueOf(rs.getString(columnName));
    }

    @Override
    public VideoType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return VideoType.valueOf(rs.getString(columnIndex));
    }

    @Override
    public VideoType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return VideoType.valueOf(cs.getString(columnIndex));
    }
}
