package com.chipndale.models;

import com.chipndale.util.ConsoleUtil;

public class UserInfo implements IHavingTableHeader {
  private int userID;
  private String username;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String email;

  public UserInfo() {
    super();
    // TODO Auto-generated constructor stub
  }

  public UserInfo(int userID, String username, String firstName, String lastName, String phoneNumber, String email) {
    super();
    this.userID = userID;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toTableHeader() {
    return ConsoleUtil.padRToLen("User ID", 10) + ConsoleUtil.padRToLen("Username", 20)
        + ConsoleUtil.padRToLen("First Name", 20) + ConsoleUtil.padRToLen("Last Name", 20)
        + ConsoleUtil.padRToLen("Phone Number", 20) + ConsoleUtil.padRToLen("Email", 50);
  }

  @Override
  public String toString() {
    return ConsoleUtil.padRToLen(String.valueOf(userID), 10) + ConsoleUtil.padRToLen(username, 20)
        + ConsoleUtil.padRToLen(firstName, 20) + ConsoleUtil.padRToLen(lastName, 20)
        + ConsoleUtil.padRToLen(phoneNumber, 20) + ConsoleUtil.padRToLen(email, 50);
  }

}
