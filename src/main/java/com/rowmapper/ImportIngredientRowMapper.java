package com.rowmapper;

import com.model.ImportIngredient;
import com.model.Ingredient;
import com.model.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportIngredientRowMapper implements RowMapper<ImportIngredient> {
    @Override
    public ImportIngredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImportIngredient importIngredient=new ImportIngredient();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getLong("id_ingredient"));
        ingredient.setName(rs.getString("name_ingredient"));
        Unit unit =new Unit();
        unit.setName(rs.getString("name_unit"));
        ingredient.setUnit(unit);
        importIngredient.setId(rs.getLong("id"));
        importIngredient.setDateJoin(rs.getTimestamp("date_join").toLocalDateTime());
        importIngredient.setQuantity(rs.getDouble("import_quantity"));
        importIngredient.setPrice(rs.getLong("price"));
        importIngredient.setTotalPrice(rs.getLong("total_price"));
        importIngredient.setComment(rs.getString("comment"));
        importIngredient.setIngredient(ingredient);
        return importIngredient;
    }
}
