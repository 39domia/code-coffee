package com.nobita.demo.dao;

import com.nobita.demo.model.QuantitativeInventory;
import com.nobita.demo.resultset.QuantitativeInventoryResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantitativeInventoryDAO implements BaseDAO<QuantitativeInventory> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<QuantitativeInventory> findAll() {
        return null;
    }

    public List<QuantitativeInventory> findAllByDate(String dateIn,String dateOut){
        String sql="select i.name as name_ingredient,coalesce((select sum(ii.import_quantity) from import_ingredient ii where i.id=ii.id_ingredient and ii.date_join <= ? group by ii.id_ingredient),0) as total_import,coalesce((select sum(qe.quantity) from quantitative_export qe where i.id=qe.id_ingredient and qe.date_export >= ? and qe.date_export <=? group by qe.id_ingredient),0) as total_export from ingredient i ";
        Object[]values={dateOut,dateIn,dateOut};
        return jdbcTemplate.query(sql,new QuantitativeInventoryResultSet(),values);
    }

    @Override
    public QuantitativeInventory findByID(Long id) {
        return null;
    }

    @Override
    public boolean save(QuantitativeInventory quantitativeInventory) {
        return false;
    }

    @Override
    public boolean update(QuantitativeInventory quantitativeInventory) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
