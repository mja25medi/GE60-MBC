package egovframework.edutrack.comm.code;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

public abstract class CodeTypeHandler<E extends Enum<E>> implements TypeHandler<CodeType> {
	
	@Override
	public void setParameter(PreparedStatement ps, int i, CodeType parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getCodeTypeCd());
	}

	@Override
	public CodeType getResult(ResultSet rs, String columnName) throws SQLException {
		return getCodeEnum(rs.getString(columnName));
	}

	@Override
	public CodeType getResult(ResultSet rs, int columnIndex) throws SQLException {
		return getCodeEnum(rs.getString(columnIndex));
	}

	@Override
	public CodeType getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getCodeEnum(cs.getString(columnIndex));
	}
	
    public abstract CodeType getCodeEnum(String name);
}
