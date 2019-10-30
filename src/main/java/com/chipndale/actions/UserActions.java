package com.chipndale.actions;

import java.util.HashMap;

import com.chipndale.daos.AccountDao;
import com.chipndale.daos.UserInfoDao;
import com.chipndale.models.Account;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;
import com.chipndale.util.TempObjUtil;

public class UserActions {
  private static AuthUtil authUtil = AuthUtil.instance;
  private static UserInfoDao userInfoDao = UserInfoDao.currentImplementation;
  public static HashMap<String, String> accountTypeMap = new HashMap<>();
  public static HashMap<String, String> designationUnitMap = new HashMap<>();

  // Make lookup tables tying account type and unit to designation of the account
  public static void initalizeAccDesignations() {
    accountTypeMap.put("USD", "currency");
    designationUnitMap.put("USD", "dollar");
    accountTypeMap.put("RMB", "currency");
    designationUnitMap.put("RMB", "yuan");
    accountTypeMap.put("acron", "commodity");
    designationUnitMap.put("acron", "count");
    accountTypeMap.put("bone", "commodity");
    designationUnitMap.put("bone", "count");
    accountTypeMap.put("gold_nugget", "commodity");
    designationUnitMap.put("gold_nugget", "ouce");
    accountTypeMap.put("honey", "commodity");
    designationUnitMap.put("honey", "kilogram");
  }

  public static void createAccount(String ownerUsername, String designation, double intialBalance) {
    AccountDao accDao = AccountDao.currentImplementation;
    Account tempAccount = TempObjUtil.accountInstance;
    tempAccount.setOwnerUsername(ownerUsername);
    tempAccount.setAccountType(accountTypeMap.get(designation));
    tempAccount.setDesignation(designation);
    tempAccount.setUnit(designationUnitMap.get(designation));
    tempAccount.setBalance(intialBalance);
    accDao.save(tempAccount);
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

  public static void createNewCurrencyAccount() {

  }

  public static void createNewComodityAccount() {

  }
}
