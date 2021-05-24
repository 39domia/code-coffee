package com.rowmapper;

import com.model.dto.QuantitativeDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuantitativeDTORowMapper implements RowMapper<QuantitativeDTO> {
    @Override
    public QuantitativeDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        QuantitativeDTO quantitativeDTO=new QuantitativeDTO();
        quantitativeDTO.setQuantityImport(resultSet.getDouble("quantity_import"));
        quantitativeDTO.setQuantityExport(resultSet.getDouble("quantity_export"));
        return quantitativeDTO;
    }
}
