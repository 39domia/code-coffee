package com.dao;

import com.model.Bill;
import com.resultset.BillResultSet;
import com.rowmapper.BillRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDAO implements BaseDAO<Bill> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Bill> findAll() {
        String sql = "select * from bill";
        return jdbcTemplate.query(sql, new BillResultSet());
    }

    @Override
    public Bill findByID(Long id) {
        return null;
    }

    public List<Bill> findByDateExport(String dateExportIn, String dateExportOut) {
        String sql = "select * from bill where date_export > ? and date_export < ?";
        Object[] values = {dateExportIn, dateExportOut};
        return jdbcTemplate.query(sql, new BillResultSet(), values);

    }

    public Bill findByIdOder(Long idOrder) {
        String sql = "select * from bill where id_order =?";
        Object[] values = {idOrder};
        return jdbcTemplate.queryForObject(sql, new BillRowMapper(), values);
    }

    public Bill sumTotalPriceAll() {
        String sql = "SELECT id_order, date_join, date_export, name_table, sum(total_price) as total_price FROM bill;";
        return jdbcTemplate.queryForObject(sql, new BillRowMapper());
    }

    public Bill countBills() {
        String sql = "SELECT id_order, date_join, date_export, name_table, count(id_order) as total_price FROM bill;";
        return jdbcTemplate.queryForObject(sql, new BillRowMapper());
    }

    @Override
    public boolean save(Bill bill) {
        String sql = "insert into bill(id_order,date_join,name_table,total_price) values(?,?,?,?)";
        Object[] values = {bill.getIdOrder(), bill.getDateJoin(), bill.getNameTable(), bill.getTotalPrice()};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean update(Bill bill) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public boolean deleteByIdOrder(Long idOrder) {
        String sql = "delete from bill where id_order=?";
        Object[] values = {idOrder};
        return jdbcTemplate.update(sql, values) > 0;
    }
}
