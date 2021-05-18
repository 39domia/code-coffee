package com.rowmapper;

import com.model.BillDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetailsRowMapper implements RowMapper<BillDetail> {
    @Override
    public BillDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        BillDetail billDetail=new BillDetail();
        billDetail.setIdOrder(rs.getLong("id_order"));
        billDetail.setNameProduct(rs.getString("name_product"));
        billDetail.setQuantity(rs.getLong("quantity"));
        billDetail.setPriceEach(rs.getLong("price_each"));
        billDetail.setIdProduct(rs.getLong("id_product"));
        return billDetail;
    }
}
