package com.oes.handlers;


import com.oes.model.enums.PictureType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes({JdbcType.TINYINT})
@MappedTypes({PictureType.class})
public class PictureTypeHandler extends BaseTypeHandler<PictureType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PictureType pictureType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, pictureType.getValue());
    }

    @Override
    public PictureType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return PictureType.getTypeByInt(resultSet.getInt(s));
    }

    @Override
    public PictureType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return PictureType.getTypeByInt(resultSet.getInt(i));
    }

    @Override
    public PictureType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return PictureType.getTypeByInt(callableStatement.getInt(i));
    }
}
