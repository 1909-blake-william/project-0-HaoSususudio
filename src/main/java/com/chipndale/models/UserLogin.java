package com.chipndale.models;

import com.chipndale.util.ConsoleUtil;

public class UserLogin implements IHavingTableHeader {
  private String username;
  private String secureKey;
  private String saltForPassword;
  private String role;

  public UserLogin() {
    super();
    // TODO Auto-generated constructor stub
  }

  public UserLogin(String username, String secureKey, String saltForPassword, String role) {
    super();
    this.username = username;
    this.secureKey = secureKey;
    this.saltForPassword = saltForPassword;
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSecureKey() {
    return secureKey;
  }

  public void setSecureKey(String secureKey) {
    this.secureKey = secureKey;
  }

  public String getSaltForPassword() {
    return saltForPassword;
  }

  public void setSaltForPassword(String saltForPassword) {
    this.saltForPassword = saltForPassword;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toTableHeader() {
    return ConsoleUtil.padRToLen("Username", 20) + ConsoleUtil.padRToLen("Secure Key", 27)
        + ConsoleUtil.padRToLen("Salt For Password", 27) + ConsoleUtil.padRToLen("Role", 10);
  }

  @Override
  public String toString() {
    return ConsoleUtil.padRToLen(username, 20) + ConsoleUtil.padRToLen(secureKey, 27)
        + ConsoleUtil.padRToLen(saltForPassword, 27) + ConsoleUtil.padRToLen(role, 10);
  }

}
