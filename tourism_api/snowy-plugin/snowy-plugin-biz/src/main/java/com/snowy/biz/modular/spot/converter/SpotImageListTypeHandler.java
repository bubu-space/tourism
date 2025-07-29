package com.snowy.biz.modular.spot.converter;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * 用于处理景点图片列表类型的映射。通过继承 BaseTypeHandler 类，并指定泛型为 List<SpotImageUrlPair>，实现了对数据库中 JSON 格式的数据与 Java 对象的映射
 *
 */
public class SpotImageListTypeHandler extends BaseTypeHandler<List<SpotImageUrlPair>> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<SpotImageUrlPair> parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, JSONUtil.toJsonStr(parameter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SpotImageUrlPair> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJson(rs.getString(columnName));
    }

    @Override
    public List<SpotImageUrlPair> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJson(rs.getString(columnIndex));
    }

    @Override
    public List<SpotImageUrlPair> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJson(cs.getString(columnIndex));
    }

    private List<SpotImageUrlPair> parseJson(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return JSONUtil.toList(json, SpotImageUrlPair.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
