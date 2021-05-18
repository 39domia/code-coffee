package com.resultset;

import com.model.QuantitativeInventory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuantitativeInventoryResultSet implements ResultSetExtractor<List<QuantitativeInventory>> {
    @Override
    public List<QuantitativeInventory> extractData(ResultSet rs) throws SQLException, DataAccessException {
       List<QuantitativeInventory> quantitativeInventories=new ArrayList<>();
       while(rs.next()){
           QuantitativeInventory quantitativeInventory=new QuantitativeInventory();
           quantitativeInventory.setNameIngredient(rs.getString("name_ingredient"));
           quantitativeInventory.setImportIngredient(rs.getDouble("total_import"));
           quantitativeInventory.setExportIngredient(rs.getDouble("total_export"));
           quantitativeInventories.add(quantitativeInventory);
       }
       return quantitativeInventories;
    }
}
