package com.oes.handlers;

import com.oes.model.enums.RegistState;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@MappedJdbcTypes({JdbcType.TINYINT})
@MappedTypes({RegistState.class})
public class RegistStateHandler extends BaseTypeHandler<RegistState> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, RegistState registState, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,registState.getValue());
    }

    @Override
    public RegistState getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return RegistState.getTypeByInt(resultSet.getInt(s));
    }

    @Override
    public RegistState getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return RegistState.getTypeByInt(resultSet.getInt(i));
    }

    @Override
    public RegistState getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return RegistState.getTypeByInt(callableStatement.getInt(i));
    }
}
