package com.moviestorage.moviestorage.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexHandler implements TypeHandler<GenderType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, GenderType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getGender());
    }

    @Override
    public GenderType getResult(ResultSet rs, String columnName) throws SQLException {
        return GenderType.valueOf(rs.getString(columnName));
    }

    @Override
    public GenderType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return GenderType.valueOf(rs.getString(columnIndex));
    }

    @Override
    public GenderType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return GenderType.valueOf(cs.getString(columnIndex));
    }

}
