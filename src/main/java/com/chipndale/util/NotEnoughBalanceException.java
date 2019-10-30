package com.chipndale.util;

public class NotEnoughBalanceException extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Override
  public String getMessage() {
    return "You overdrafted checking account.";
  }

}
