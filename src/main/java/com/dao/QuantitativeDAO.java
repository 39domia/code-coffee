package com.dao;

import com.model.Quantitative;
import com.model.dto.QuantitativeDTO;
import com.resultset.QuantitativeResultSet;
import com.rowmapper.QuantitativeDTORowMapper;
import com.rowmapper.QuantitativeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantitativeDAO implements BaseDAO<Quantitative> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Quantitative> findAll() {
        String sql = "select q.*,p.name as name_product,i.name as name_ingredient from quantitative q left join product p on p.id=q.id_product left join ingredient i on i.id=q.id_ingredient";
        return jdbcTemplate.query(sql, new QuantitativeResultSet());
    }

    @Override
    public Quantitative findByID(Long id) {
        return null;
    }

    public Quantitative findByIdProductAndIdIngredient(Long idProduct, Long idIngredient) {
        String sql = "select q.*,p.name as name_product,i.name as name_ingredient from quantitative q left join product p on p.id=q.id_product left join ingredient i on i.id=q.id_ingredient where q.id_product =? and q.id_ingredient =?";
        Object[] values = {idProduct, idIngredient};
        return jdbcTemplate.queryForObject(sql, new QuantitativeRowMapper(), values);
    }

    @Override
    public boolean save(Quantitative quantitative) {
        String sql = "insert into quantitative(id_product,id_ingredient,quantity) value(?,?,?)";
        Object[] value = {quantitative.getProduct().getId(), quantitative.getIngredient().getId(), quantitative.getQuantity()};
        return jdbcTemplate.update(sql, value) > 0;
    }

    @Override
    public boolean update(Quantitative quantitative) {
        return false;
    }

    public boolean updateByIdProductAndIdIngredient(Quantitative quantitative, Long idProduct, Long idIngredient) {
        String sql = "update quantitative set id_product=?,id_ingredient=?,quantity=? where id_product =? and id_ingredient=?";
        Object[] values = {quantitative.getProduct().getId(), quantitative.getIngredient().getId(), quantitative.getQuantity(), idProduct, idIngredient};
        return jdbcTemplate.update(sql, values) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    public boolean deleteByIdProductAndIdIngredient(Long idProduct, Long idIngredient) {
        String sql = "delete from quantitative where id_product =? and id_ingredient =?";
        Object[] values = {idProduct, idIngredient};
        return jdbcTemplate.update(sql, values) > 0;
    }

    public QuantitativeDTO getQuantitative(){
        String sql="select sum(ii.import_quantity) as quantity_import,coalesce((select sum(qe.quantity) from quantitative_export qe),0) as quantity_export from import_ingredient ii";
        return (QuantitativeDTO) jdbcTemplate.query(sql,new QuantitativeDTORowMapper());
    }
}
