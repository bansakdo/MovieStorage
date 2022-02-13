package com.moviestorage.moviestorage.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexHandler implements TypeHandler<SexType> {
    @Override
    public void setParameter(PreparedStatement ps, int i, SexType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getSex());
    }

    @Override
    public SexType getResult(ResultSet rs, String columnName) throws SQLException {
        return SexType.valueOf(rs.getString(columnName));
    }

    @Override
    public SexType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SexType.valueOf(rs.getString(columnIndex));
    }

    @Override
    public SexType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SexType.valueOf(cs.getString(columnIndex));
    }

}
