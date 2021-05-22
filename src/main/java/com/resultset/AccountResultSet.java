package com.resultset;

import com.model.Account;
import com.model.en.Authorization;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountResultSet implements ResultSetExtractor<List<Account>> {
    @Override
    public List<Account> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Account> accounts=new ArrayList<>();
        while(rs.next()){
            Account account=new Account();
            account.setId(rs.getLong("id"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));
            account.setImage(rs.getString("image"));
            account.setFullName(rs.getString("fullname"));
            account.setAuthorization(Authorization.valueOf(rs.getString("authorization")));
            accounts.add(account);
        }
        return accounts;
    }
}
