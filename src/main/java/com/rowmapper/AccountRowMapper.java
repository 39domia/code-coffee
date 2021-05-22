package com.rowmapper;

import com.model.Account;
import com.model.en.Authorization;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account=new Account();
        account.setId(rs.getLong("id"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setImage(rs.getString("image"));
        account.setFullName(rs.getString("fullname"));
        account.setAuthorization(Authorization.valueOf(rs.getString("authorization")));
        return account;
    }
}
