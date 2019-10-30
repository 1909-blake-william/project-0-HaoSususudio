package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.actions.AccountActions;
import com.chipndale.daos.AccountDao;
import com.chipndale.util.ConsoleUtil;

public class AccountWithdrawPrompt implements Prompt {
  private Scanner scan = new Scanner(System.in);
  AccountDao accDao = AccountDao.currentImplementation;

  public Prompt run(int accountId) {
    ConsoleUtil.echo("How much would you like to withdraw?");
    String selection = scan.nextLine();
    double amount = Double.valueOf(selection);
    AccountActions.withdraw(accountId, amount);
    return new AccountActionsPromt().run(accountId);
  }

  @Override
  public Prompt run() {
    return new AccountActionsPromt();
  }

}
