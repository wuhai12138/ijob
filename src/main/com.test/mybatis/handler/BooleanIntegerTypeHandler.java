package mybatis.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shaojw on 2016/1/13.
 */
public class BooleanIntegerTypeHandler implements TypeHandler<Boolean> {

    public void setParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        if (aBoolean) {
            preparedStatement.setInt(i, 1);
        } else {
            preparedStatement.setInt(i, 0);
        }
    }

    public Boolean getResult(ResultSet resultSet, String column) throws SQLException {
        int dbValue = resultSet.getInt(column);
        if (dbValue == 1) {
            return true;
        }
        return false;
    }

    public Boolean getResult(ResultSet resultSet, int i) throws SQLException {
        int dbValue = resultSet.getInt(i);
        if (dbValue == 1) {
            return true;
        }
        return false;
    }

    public Boolean getResult(CallableStatement callableStatement,int i) throws SQLException{
        int dbValue = callableStatement.getInt(i);
        if (dbValue == 1){
            return  true;
        }
        return false;
    }
}

