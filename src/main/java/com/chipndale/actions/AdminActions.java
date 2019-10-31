package com.chipndale.actions;

import java.util.List;

import com.chipndale.daos.AccountDao;
import com.chipndale.daos.TransactionDao;
import com.chipndale.daos.UserInfoDao;
import com.chipndale.daos.UserLoginDao;
import com.chipndale.models.Account;
import com.chipndale.models.Transaction;
import com.chipndale.models.UserInfo;
import com.chipndale.models.UserLogin;
import com.chipndale.util.ConsoleUtil;

public class AdminActions {
  public static void viewAllUserLogins() {
    UserLoginDao userLoginDao = UserLoginDao.currentImplementation;
    List<UserLogin> userLogins = userLoginDao.findAll();
    ConsoleUtil.printAsTable(userLogins);
    System.out.println();
  }

  public static void viewAllUserInfo() {
    UserInfoDao userInfoDao = UserInfoDao.currentImplementation;
    List<UserInfo> userInfo = userInfoDao.findAll();
    ConsoleUtil.printAsTable(userInfo);
    System.out.println();
  }

  public static void viewAllAccounts() {
    AccountDao accDao = AccountDao.currentImplementation;
    List<Account> allAccounts = accDao.findAll();
    ConsoleUtil.printAsTable(allAccounts);
    System.out.println();
  }

  public static void viewUserAccounts(String username) {
    AccountDao accDao = AccountDao.currentImplementation;
    List<Account> userAccounts = accDao.findAllByUsername(username);
    ConsoleUtil.printAsTable(userAccounts);
    System.out.println();
  }

  public static void viewAllTransactions() {
    TransactionDao transDao = TransactionDao.currentImplementation;
    List<Transaction> allTransactions = transDao.findAll();
    ConsoleUtil.printAsTable(allTransactions);
    System.out.println();
  }

}
