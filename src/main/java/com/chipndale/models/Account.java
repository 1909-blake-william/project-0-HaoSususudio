package com.chipndale.models;

import com.chipndale.util.ConsoleUtil;

public class Account implements IHavingTableHeader {
  private int accountID;
  private String ownerUsername;
  private String accountType;
  private String designation;
  private String unit;
  private double balance;
  private String accountStatus;

  public Account() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Account(int accountID, String ownerUsername, String accountType, String designation, String unit,
      double balance, String accountStatus) {
    super();
    this.accountID = accountID;
    this.ownerUsername = ownerUsername;
    this.accountType = accountType;
    this.designation = designation;
    this.unit = unit;
    this.balance = balance;
    this.accountStatus = accountStatus;
  }

  public int getAccountID() {
    return accountID;
  }

  public void setAccountID(int accountID) {
    this.accountID = accountID;
  }

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
  }

  @Override
  public String toTableHeader() {
    return ConsoleUtil.padRToLen("Acc. ID", 10) + ConsoleUtil.padRToLen("Owner's Username", 20)
        + ConsoleUtil.padRToLen("Account Type", 15) + ConsoleUtil.padRToLen("Designation", 20)
        + ConsoleUtil.padRToLen("Balance", 20) + ConsoleUtil.padRToLen("Unit", 15)
        + ConsoleUtil.padRToLen("Status", 15);
  }

  @Override
  public String toString() {
    return ConsoleUtil.padRToLen(String.valueOf(accountID), 10) + ConsoleUtil.padRToLen(ownerUsername, 20)
        + ConsoleUtil.padRToLen(accountType, 15) + ConsoleUtil.padRToLen(designation, 20)
        + ConsoleUtil.padRToLen(String.valueOf(balance), 20) + ConsoleUtil.padRToLen(unit, 15)
        + ConsoleUtil.padRToLen(accountStatus, 15);
  };

}
