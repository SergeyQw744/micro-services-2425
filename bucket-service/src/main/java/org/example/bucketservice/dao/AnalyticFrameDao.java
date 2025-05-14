package org.example.bucketservice.dao;

import org.example.bucketservice.model.AnalyticalFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class AnalyticFrameDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AnalyticFrameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(AnalyticalFrame frame){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String sql = "insert into " +
                "analytical_frame(request, operation, date_of_action, provider, price) " +
                "values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, frame.getRequest().toString(),
                frame.getOperation().toString(),
                timestamp,
                frame.getProvider(),
                frame.getPrice());
    }

    public List<AnalyticalFrame> getLastFramesByTime(){
        String sql = "select * from analytical_frame where date_of_action >= now() - interval '1 minute'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(AnalyticalFrame.class));
    }
}
