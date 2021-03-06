package com.dao;

import com.model.Ingredient;
import com.resultset.IngredientResultSet;
import com.rowmapper.IngredientRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientDAO implements BaseDAO<Ingredient> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredient> findAll() {
        String sql = "select i.* ,u.name as name_unit from ingredient i left join unit u on u.id=i.id_unit where i.deleted = 0";
        return jdbcTemplate.query(sql, new IngredientResultSet());
    }

    // lấy tất cả các Nguyên liệu không có định mức theo Sản phẩm
    public List<Ingredient> findAllNotQuantitativeProduct (Long idProduct) {
        String sql = "select i.* ,u.name as name_unit from ingredient i left join unit u on u.id=i.id_unit where i.deleted = 0 and i.id not in (select id_ingredient from quantitative where id_product = ?) ";
        Object[] values = {idProduct};
        return jdbcTemplate.query(sql, new IngredientRowMapper(), values);
    }

    @Override
    public Ingredient findByID(Long id) {
        String sql = "select i.* ,u.name as name_unit from ingredient i left join unit u on u.id=i.id_unit where i.id = ? and i.deleted = 0";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql, new IngredientRowMapper(), values);
    }

    @Override
    public boolean save(Ingredient ingredient) {
        String sql = "insert into ingredient(name,id_unit,comment) values(?,?,?)";
        Object[] values = {ingredient.getName(), ingredient.getUnit().getId(), ingredient.getComment()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Ingredient ingredient) {
        String sql = "update ingredient set name=?,id_unit=?,comment=? where id=?";
        Object[] values = {ingredient.getName(), ingredient.getUnit().getId(), ingredient.getComment(), ingredient.getId()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "update ingredient set deleted=1 where id=?";
        Object[] values = {id};
        return jdbcTemplate.update(sql, values) > 0;
    }
}
