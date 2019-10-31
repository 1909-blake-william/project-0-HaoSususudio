package com.chipndale.models;

import java.sql.Timestamp;

import com.chipndale.util.ConsoleUtil;

public class Transaction implements IHavingTableHeader {
  private int transactionId;
  private int accountId;
  private String transactionType;
  private double deltaBalance;
  private Timestamp timeStamp;

  public Transaction() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Transaction(int transactionId, int accountId, String transactionType, double deltaBalance,
      Timestamp timeStamp) {
    super();
    this.transactionId = transactionId;
    this.accountId = accountId;
    this.transactionType = transactionType;
    this.deltaBalance = deltaBalance;
    this.timeStamp = timeStamp;
  }

  public int getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(int transactionId) {
    this.transactionId = transactionId;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public double getDeltaBalance() {
    return deltaBalance;
  }

  public void setDeltaBalance(double deltaBalance) {
    this.deltaBalance = deltaBalance;
  }

  public Timestamp getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Timestamp timeStamp) {
    this.timeStamp = timeStamp;
  }

  @Override
  public String toTableHeader() {
    return ConsoleUtil.padRToLen("Trans. Id", 10) + ConsoleUtil.padRToLen("Acct. Id", 10)
        + ConsoleUtil.padRToLen("Trans. Type", 15) + ConsoleUtil.padRToLen("Amount", 20)
        + ConsoleUtil.padRToLen("Time Stamp", 25);
  }

  @Override
  public String toString() {
//    ZoneId zoneId = ZoneId.of("America/New_York");
    return ConsoleUtil.padRToLen(String.valueOf(transactionId), 10)
        + ConsoleUtil.padRToLen(String.valueOf(accountId), 10) + ConsoleUtil.padRToLen(transactionType, 15)
        + ConsoleUtil.padRToLen(String.valueOf(deltaBalance), 20) + ConsoleUtil.padRToLen(timeStamp.toString(), 25);
  }

}
