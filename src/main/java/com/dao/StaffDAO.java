package com.dao;

import com.model.Staff;
import com.resultset.StaffResultSet;
import com.rowmapper.StaffRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public class StaffDAO implements BaseDAO<Staff> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Staff> findAll() {
        String sql = "select * from staff ";
        return jdbcTemplate.query(sql, new StaffResultSet());
    }

    @Override
    public Staff findByID(Long id) {
        String sql = "select * from staff where id=?";
        Object[] values = {id};
        return jdbcTemplate.queryForObject(sql,new StaffRowMapper(),values);
    }

    @Override
    public boolean save(Staff staff) {
        String sql = "insert into staff(fullname,gender,position ,date_of_birth,address,phone,username,password) values (?,?,?,?,?,?,?,?)";
        Object[] values = {staff.getFullName(), staff.getGender(), staff.getPosition(), Date.valueOf(staff.getDateOfBirth()), staff.getAddress(), staff.getPhone(), staff.getUsername(), staff.getPassword()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Staff staff) {
        String sql ="update staff set fullname =?,gender=?,position =?,date_of_birth=?,address= ?,phone =?,username =? ,password =? where id =?";
        Object [] values = {staff.getFullName(), staff.getGender(), staff.getPosition(), Date.valueOf(staff.getDateOfBirth()), staff.getAddress(), staff.getPhone(), staff.getUsername(), staff.getPassword(),staff.getId()};
        return jdbcTemplate.update(sql,values) > 0 ;
    }

    @Override
    public boolean delete(Long id) {
        String sql ="delete from staff where id =?";
        Object[] values= {id};
        return jdbcTemplate.update(sql,values) > 0;
    }
}
