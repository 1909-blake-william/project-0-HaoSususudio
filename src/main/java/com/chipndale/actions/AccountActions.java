package com.chipndale.actions;

import java.util.List;

import com.chipndale.daos.AccountDao;
import com.chipndale.daos.TransactionDao;
import com.chipndale.models.Transaction;
import com.chipndale.util.ConsoleUtil;
import com.chipndale.util.TempObjUtil;

public class AccountActions {
  static AccountDao accDao = AccountDao.currentImplementation;
  static TransactionDao transDao = TransactionDao.currentImplementation;

  public static void showTransactions(int accountId) {
    System.out.println("acc id: " + accountId);
    List<Transaction> tempTrans = transDao.findAllByAccountId(accountId);
    ConsoleUtil.printAsTable(tempTrans);
    System.out.println();
  }

  public static void deposit(int accountId, double amount) {
    TempObjUtil.acctInst = accDao.findById(accountId);
    TempObjUtil.acctInst.setBalance(TempObjUtil.acctInst.getBalance() + amount);
    accDao.update(TempObjUtil.acctInst);
    TempObjUtil.transcInst.setAccountId(accountId);
    TempObjUtil.transcInst.setTransactionType("deposit");
    TempObjUtil.transcInst.setDeltaBalance(amount);
    transDao.save(TempObjUtil.transcInst);
  };

  public static void withdraw(int accountId, double amount) {
    TempObjUtil.acctInst = accDao.findById(accountId);
    if (TempObjUtil.acctInst.getBalance() < amount) {
      ConsoleUtil.echo("Not enough balance.");
    } else {
      TempObjUtil.acctInst.setBalance(TempObjUtil.acctInst.getBalance() - amount);
      accDao.update(TempObjUtil.acctInst);
      TempObjUtil.transcInst.setAccountId(accountId);
      TempObjUtil.transcInst.setTransactionType("withdraw");
      TempObjUtil.transcInst.setDeltaBalance(amount);
      transDao.save(TempObjUtil.transcInst);
    }
  }

  public static void deactivate(int accountId) {
    TempObjUtil.acctInst = accDao.findById(accountId);
    TempObjUtil.acctInst.setAccountStatus("deactivated");
    accDao.update(TempObjUtil.acctInst);
    TempObjUtil.transcInst.setAccountId(accountId);
    TempObjUtil.transcInst.setTransactionType("deactivation");
    TempObjUtil.transcInst.setDeltaBalance(0);
  }

}
