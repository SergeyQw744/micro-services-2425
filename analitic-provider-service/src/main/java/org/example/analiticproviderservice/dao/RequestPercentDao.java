package org.example.analiticproviderservice.dao;

import lombok.RequiredArgsConstructor;
import org.example.analiticproviderservice.model.RequestPercent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RequestPercentDao {

    private final JdbcTemplate jdbcTemplate;

    public RequestPercent save(RequestPercent requestPercent){
        KeyHolder generatedIdKeyHolder = new GeneratedKeyHolder();
        String sql = "insert into request_percent_table(post, get, delete) into (?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[] { "id" });
            statement.setDouble(1, requestPercent.getRequestPostPercent());
            statement.setDouble(2, requestPercent.getRequestGetPercent());
            statement.setDouble(3, requestPercent.getRequestDeletePercent());
            return statement;
        }, generatedIdKeyHolder);
        requestPercent.setId(Objects.requireNonNull(generatedIdKeyHolder.getKey()).intValue());
        return requestPercent;
    }
}
