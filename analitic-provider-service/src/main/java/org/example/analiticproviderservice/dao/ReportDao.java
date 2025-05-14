package org.example.analiticproviderservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReportDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(){

    }
}
