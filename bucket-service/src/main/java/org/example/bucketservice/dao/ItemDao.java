package org.example.bucketservice.dao;

import org.example.bucketservice.model.Item;
import org.example.bucketservice.util.CodeGeneratorForItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class ItemDao {
    private final JdbcTemplate jdbcTemplate;
    private final CodeGeneratorForItem codeGeneratorForItem;

    @Autowired
    public ItemDao(JdbcTemplate jdbcTemplate, CodeGeneratorForItem codeGeneratorForItem) {
        this.jdbcTemplate = jdbcTemplate;
        this.codeGeneratorForItem = codeGeneratorForItem;
    }

    public List<Item> findAll(){
        String sql = "select * from Item";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Item.class));
    }

    @Transactional
    public Item saveItem(Item item) throws DataAccessException, NullPointerException{
        String sql = "insert into Item(title, description, price, code) values(?,?,?,?)";
        String code = codeGeneratorForItem.generate();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, new String[] { "id" });
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getDescription());
            statement.setDouble(3, item.getPrice());
            statement.setString(4, code);
            return statement;
        }, keyHolder);
        int generatedId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        item.setId(generatedId);
        item.setCode(code);
        return item;
    }

    public void delete(int id){
        String sql = "delete from Item where id=?";
        jdbcTemplate.update(sql, id);
    }

    public Item findById(int id){
        String sql = "select * from Item where id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Item.class))
                .stream()
                .findFirst()
                .get();
    }
}
