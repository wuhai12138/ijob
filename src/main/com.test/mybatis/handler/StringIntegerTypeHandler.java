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
public class StringIntegerTypeHandler implements TypeHandler<String>{

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException{
        preparedStatement.setInt(i,Integer.valueOf(s));
    }

    @Override
    public String getResult(ResultSet resultSet,String s) throws SQLException{
        return resultSet.getString(s);
    }

    public String getResult(ResultSet resultSet,int i) throws SQLException{
        return resultSet.getString(i);
    }

    public String getResult(CallableStatement callableStatement,int i) throws SQLException{
        return callableStatement.getString(i);
    }
}
