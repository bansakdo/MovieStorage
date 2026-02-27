package com.moviestorage.moviestorage.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoHandler implements TypeHandler<MediaType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, MediaType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getVideoType());
    }

    @Override
    public MediaType getResult(ResultSet rs, String columnName) throws SQLException {
        return MediaType.valueOf(rs.getString(columnName));
    }

    @Override
    public MediaType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return MediaType.valueOf(rs.getString(columnIndex));
    }

    @Override
    public MediaType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return MediaType.valueOf(cs.getString(columnIndex));
    }
}
