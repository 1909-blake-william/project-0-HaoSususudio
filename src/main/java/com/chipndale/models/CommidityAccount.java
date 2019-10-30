package com.chipndale.models;

public class CommidityAccount extends Account {
  private int accountID;
  private String ownerUsername;
  private String type;
  private String designation;
  private String unit;
  private double balance;
  private String status;

  public CommidityAccount() {
    super();
    // TODO Auto-generated constructor stub
  }

  public CommidityAccount(int accountID, String ownerUsername, String type, String designation, String unit,
      double balance, String status) {
    super();
    this.accountID = accountID;
    this.ownerUsername = ownerUsername;
    this.type = type;
    this.designation = designation;
    this.unit = unit;
    this.balance = balance;
    this.status = status;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toTableHeader() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    return "CommidityAccount [accountID=" + accountID + ", ownerUsername=" + ownerUsername + ", type=" + type
        + ", designation=" + designation + ", unit=" + unit + ", balance=" + balance + ", status=" + status + "]";
  }

}
