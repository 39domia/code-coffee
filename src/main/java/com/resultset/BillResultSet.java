package com.resultset;

import com.model.Bill;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillResultSet implements ResultSetExtractor<List<Bill>> {
    @Override
    public List<Bill> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Bill> bills = new ArrayList<>();
        while (rs.next()) {
            Bill bill = new Bill();
            bill.setIdOrder(rs.getLong("id_order"));
            bill.setDateJoinView(rs.getTimestamp("date_join").toLocalDateTime());
            bill.setDateExport(rs.getTimestamp("date_export").toLocalDateTime());
            bill.setNameTable(rs.getString("name_table"));
            bill.setTotalPrice(rs.getLong("total_price"));
            bills.add(bill);
        }
        return bills;
    }
}
