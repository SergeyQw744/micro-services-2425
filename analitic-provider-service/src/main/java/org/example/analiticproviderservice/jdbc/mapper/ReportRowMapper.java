package org.example.analiticproviderservice.jdbc.mapper;

import org.example.analiticproviderservice.model.OperationPercent;
import org.example.analiticproviderservice.model.Report;
import org.example.analiticproviderservice.model.RequestPercent;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReportRowMapper implements RowMapper<Report> {

    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = Report.builder().build();
            report.setId(rs.getInt("id"));
            report.setMostPopularProvider(rs.getString("most_popular_provider"));
            report.setAvgPrice(rs.getDouble("avg_price"));
            report.setOperationPercent(new OperationPercent(
                    rs.getDouble("save"),
                    rs.getDouble("find_all"),
                    rs.getDouble("delete"))
            );
            report.setRequestPercent( new RequestPercent(
                    rs.getDouble("post"),
                    rs.getDouble("get"),
                    rs.getDouble("delete"))
            );
        return report;
    }
}
