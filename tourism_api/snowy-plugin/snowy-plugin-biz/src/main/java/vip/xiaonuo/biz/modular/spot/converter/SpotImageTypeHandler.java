package vip.xiaonuo.biz.modular.spot.converter;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import vip.xiaonuo.biz.modular.spot.entity.SpotImageUrlPair;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpotImageTypeHandler extends BaseTypeHandler<SpotImageUrlPair> {



    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SpotImageUrlPair parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, JSONUtil.toJsonStr(parameter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SpotImageUrlPair getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJson(rs.getString(columnName));
    }

    @Override
    public SpotImageUrlPair getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJson(rs.getString(columnIndex));
    }

    @Override
    public SpotImageUrlPair getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJson(cs.getString(columnIndex));
    }

    private SpotImageUrlPair parseJson(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return JSONUtil.toBean(json, SpotImageUrlPair.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
