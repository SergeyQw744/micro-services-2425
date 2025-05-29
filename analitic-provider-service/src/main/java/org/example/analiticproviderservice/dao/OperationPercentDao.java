package org.example.analiticproviderservice.dao;

import lombok.RequiredArgsConstructor;
import org.example.analiticproviderservice.model.OperationPercent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OperationPercentDao {

    private final JdbcTemplate jdbcTemplate;

    public OperationPercent save(OperationPercent operationPercent){
        String sql = "insert into operation_percent_table(save, find_all, delete) values(?, ?, ?)";
        KeyHolder generatedIdKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{ "id" });
            statement.setDouble(1, operationPercent.getOperationSavePercent());
            statement.setDouble(2, operationPercent.getOperationFindAllPercent());
            statement.setDouble(3, operationPercent.getOperationDeletePercent());
            return statement;
        }, generatedIdKeyHolder);
        operationPercent.setId(Objects.requireNonNull(generatedIdKeyHolder.getKey()).intValue());
        return operationPercent;
    }
}
