package org.example.analiticproviderservice.dao;

import lombok.RequiredArgsConstructor;
import org.example.analiticproviderservice.jdbc.mapper.ReportRowMapper;
import org.example.analiticproviderservice.model.Report;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReportDao {

    private final JdbcTemplate jdbcTemplate;
    private final ReportRowMapper reportRowMapper;

    public Report save(Report report, int requestPercentId, int operationPercentId){
        String sql = "insert into report_table(request_percent_id, operation_percent_id, most_popular_provider, avg_price) values (?, ?, ?, ?)";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[] { "id" });
            statement.setInt(1, requestPercentId);
            statement.setInt(2, operationPercentId);
            statement.setString(3, report.getMostPopularProvider());
            statement.setDouble(4, report.getAvgPrice());
            statement.executeUpdate();
            return statement;
        }, generatedKeyHolder);
        return Report.builder()
                .id(Objects.requireNonNull(generatedKeyHolder.getKey()).intValue())
                .mostPopularProvider(report.getMostPopularProvider())
                .avgPrice(report.getAvgPrice())
                .build();
    }

    public Optional<Report> findById(int id){
        String sql = "select " +
                    "report_table.*, " +
                    "request_percent_table.get, request_percent_table.post, request_percent_table.delete," +
                    "operation_percent_table.save, operation_percent_table.find_all, operation_percent_table.delete " +
                "from " +
                "report_table " +
                    "join " +
                "request_percent_table on report_table.id = request_percent.report_id " +
                    "join " +
                "report_table.id = operation_percent_table.report_id";
        return jdbcTemplate.query(sql, reportRowMapper)
                .stream()
                .findFirst();
    }
}
