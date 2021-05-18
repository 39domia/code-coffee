package com.service;


import com.model.Account;

public interface AccountService extends BaseService<Account>{

    public Account findByUsername(String username);

}
