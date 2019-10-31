package com.chipndale.actions;

import java.util.HashMap;

import com.chipndale.daos.AccountDao;
import com.chipndale.daos.TransactionDao;
import com.chipndale.daos.UserInfoDao;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;
import com.chipndale.util.TempObjUtil;

public class UserActions {
  static AuthUtil authUtil = AuthUtil.instance;
  static UserInfoDao userInfoDao = UserInfoDao.currentImplementation;
  static HashMap<String, String> designationMapToAccountType = createMapToAccountType();
  static HashMap<String, String> designationMapToUnit = createMapToUnit();

  private static HashMap<String, String> createMapToAccountType() {
    HashMap<String, String> tempMap = new HashMap<>();
    tempMap.put("USD", "currency");
    tempMap.put("RMB", "currency");
    tempMap.put("acron", "commodity");
    tempMap.put("bone", "commodity");
    tempMap.put("gold_nugget", "commodity");
    tempMap.put("honey", "commodity");
    return tempMap;
  }

  private static HashMap<String, String> createMapToUnit() {
    HashMap<String, String> tempMap = new HashMap<>();
    tempMap.put("USD", "dollar");
    tempMap.put("RMB", "yuan");
    tempMap.put("acron", "count");
    tempMap.put("bone", "count");
    tempMap.put("gold_nugget", "ounce");
    tempMap.put("honey", "kilogram");
    return tempMap;
  }

  public static void createAccount(String ownerUsername, String designation, double intialBalance) {
    AccountDao accDao = AccountDao.currentImplementation;
    TransactionDao transDao = TransactionDao.currentImplementation;
    TempObjUtil.acctInst.setOwnerUsername(ownerUsername);
    TempObjUtil.acctInst.setAccountType(designationMapToAccountType.get(designation));
    TempObjUtil.acctInst.setDesignation(designation);
    TempObjUtil.acctInst.setUnit(designationMapToUnit.get(designation));
    TempObjUtil.acctInst.setBalance(intialBalance);
    int accountId = accDao.save(TempObjUtil.acctInst);
    TempObjUtil.transcInst.setAccountId(accountId);
    TempObjUtil.transcInst.setTransactionType("new_account");
    TempObjUtil.transcInst.setDeltaBalance(intialBalance);
    transDao.save(TempObjUtil.transcInst);
  };

  public static void showUserInfo() {
    String username = authUtil.getCurrentUserLogin().getUsername();
    ConsoleUtil.printAsTable(userInfoDao.findByUserame(username));
    System.out.println();
  }

  public static int showAccounts() {
    AccountDao accDao = AccountDao.currentImplementation;
    String username = authUtil.getCurrentUserLogin().getUsername();
    String currentUsername = authUtil.getCurrentUserLogin().getUsername();
    String firstName = userInfoDao.findByUserame(currentUsername).getFirstName();
    String lastName = userInfoDao.findByUserame(currentUsername).getLastName();
    int numOfActiveAccounts = accDao.findAllActiveByUsername(username).size();

    if (numOfActiveAccounts == 0) {
      ConsoleUtil.echo("You don't have an account yet.");
    } else {
      ConsoleUtil.echo("Welcome " + firstName + " " + lastName + ". Your account(s):");
      System.out.println();
      ConsoleUtil.printAsTable(accDao.findAllActiveByUsername(username));
      System.out.println();
    }
    return numOfActiveAccounts;
  }
}
