package com.FanHA.mapper;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author HeTao
 * @data 2023/4/17
 **/
public class StringToDouble extends BaseTypeHandler<Double> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Double aDouble, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, aDouble.toString());
    }

    @Override
    public Double getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Double.parseDouble(resultSet.getString(s));
    }

    @Override
    public Double getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Double.parseDouble(resultSet.getString(i));
    }

    @Override
    public Double getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Double.parseDouble(callableStatement.getString(i));

    }
}
