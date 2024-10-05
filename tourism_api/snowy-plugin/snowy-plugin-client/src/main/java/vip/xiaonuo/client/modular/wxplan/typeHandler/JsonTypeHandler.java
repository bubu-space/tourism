package vip.xiaonuo.client.modular.wxplan.typeHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import vip.xiaonuo.client.modular.wxplan.entity.Content;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JsonTypeHandler implements TypeHandler<List<Content>> {
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public void setParameter(PreparedStatement ps, int i, List<Content> parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (Exception e) {
            throw new SQLException("Error converting List<content> to JSON", e);
        }
    }

    @Override
    public List<Content> getResult(ResultSet rs, String columnName) throws SQLException {
        return parseJson(rs.getString(columnName));
    }

    @Override
    public List<Content> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJson(rs.getString(columnIndex));
    }

    @Override
    public List<Content> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJson(cs.getString(columnIndex));
    }

    private List<Content> parseJson(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<Content>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to List<content>", e);
        }
    }
}
