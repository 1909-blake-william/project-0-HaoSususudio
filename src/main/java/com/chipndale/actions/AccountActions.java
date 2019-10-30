package com.chipndale.actions;

import com.chipndale.daos.AccountDao;
import com.chipndale.models.Account;
import com.chipndale.util.ConsoleUtil;

public class AccountActions {

  public static void showTransactions() {
    // TODO
  }

  public static void deposit(int accountId, double amount) {
    AccountDao accDao = AccountDao.currentImplementation;
    Account tempAccount = accDao.findById(accountId);
    tempAccount.setBalance(tempAccount.getBalance() + amount);
    accDao.update(tempAccount);
  };

  public static void withdraw(int accountId, double amount) {
    AccountDao accDao = AccountDao.currentImplementation;
    Account tempAccount = accDao.findById(accountId);
    if (tempAccount.getBalance() < amount) {
      ConsoleUtil.echo("Not enough balance.");
      // throw new NotEnoughBalanceException();
    } else {
      tempAccount.setBalance(tempAccount.getBalance() - amount);
      accDao.update(tempAccount);
    }
  }

  public static void deactivate(int accountId) {
    AccountDao accDao = AccountDao.currentImplementation;
    Account tempAccount = accDao.findById(accountId);
    tempAccount.setAccountStatus("deactivated");
    accDao.update(tempAccount);
  }

}
