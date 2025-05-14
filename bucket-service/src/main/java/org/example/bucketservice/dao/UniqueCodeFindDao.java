package org.example.bucketservice.dao;

import org.example.bucketservice.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UniqueCodeFindDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UniqueCodeFindDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean findByCode(String code){
        String sql = "select * from Item where code=?";
        Optional<Item> item = jdbcTemplate.query(sql, new Object[]{code}, new BeanPropertyRowMapper<>(Item.class))
                .stream()
                .findFirst();
        return item.isPresent();
    }
}
