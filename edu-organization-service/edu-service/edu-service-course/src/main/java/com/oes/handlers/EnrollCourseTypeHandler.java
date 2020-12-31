package com.oes.handlers;

import com.oes.model.enums.EnrollCourseStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes({JdbcType.TINYINT})
@MappedTypes({EnrollCourseStatus.class})
public class EnrollCourseTypeHandler extends BaseTypeHandler<EnrollCourseStatus> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, EnrollCourseStatus enrollCourseStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, enrollCourseStatus.getCode());
    }

    @Override
    public EnrollCourseStatus getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return EnrollCourseStatus.getByInt(resultSet.getInt(s));
    }

    @Override
    public EnrollCourseStatus getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return EnrollCourseStatus.getByInt(resultSet.getInt(i));
    }

    @Override
    public EnrollCourseStatus getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return EnrollCourseStatus.getByInt(callableStatement.getInt(i));
    }
}
