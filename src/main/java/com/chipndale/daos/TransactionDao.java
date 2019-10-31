package com.chipndale.daos;

import java.util.List;

import com.chipndale.models.Transaction;

public interface TransactionDao {
  TransactionDao currentImplementation = new TransactionDaoSQL();

  int save(Transaction transaction);

  List<Transaction> findAll();

  List<Transaction> findAllByAccountId(int accountId);

}
