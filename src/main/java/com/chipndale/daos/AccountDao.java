package com.chipndale.daos;

import java.util.List;

import com.chipndale.models.Account;

public interface AccountDao {
  AccountDao currentImplementation = new AccountDaoSQL();

  int save(Account account);

  int update(Account account);

  List<Account> findAll();

  List<Account> findAllByUsername(String username);

  List<Account> findAllActiveByUsername(String username);

  Account findById(int accountId);

}
