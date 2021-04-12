package com.nobita.demo.dao;

import com.nobita.demo.model.Unit;
import com.nobita.demo.resultset.UnitResultSet;
import com.nobita.demo.rowmapper.UnitRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UnitDAO implements BaseDAO<Unit> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Unit> findAll() {
        String sql = "select * from unit";
        return jdbcTemplate.query(sql, new UnitResultSet());
    }

    @Override
    public Unit findByID(int id) {
        String sql = "select * from unit where id=?";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql, new UnitRowMapper(), values);
    }

    @Override
    public boolean save(Unit unit) {
        String sql = "insert into unit(name,comment) values(?,?)";
        Object[] values = {unit.getName(), unit.getComment()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Unit unit) {
        String sql = "update unit set name=?,comment=? where id=?";
        Object[] values = {unit.getName(), unit.getComment(), unit.getId()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete * from unit where id=?";
        Object[] values = {id};
        return jdbcTemplate.update(sql, values) > 0;
    }
}