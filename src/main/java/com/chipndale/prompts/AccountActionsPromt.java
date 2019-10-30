package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.daos.AccountDao;
import com.chipndale.util.ConsoleUtil;

public class AccountActionsPromt implements Prompt {
  private Scanner scan = new Scanner(System.in);
  AccountDao accDao = AccountDao.currentImplementation;

  public Prompt run(int accountId) {
    ConsoleUtil.printAsTable(accDao.findById(accountId));
    // TODO showTransactions()

    ConsoleUtil.echo("Enter 'd' to deposit.");
    ConsoleUtil.echo("Enter 'w' to withdraw");
    ConsoleUtil.echo("Enter 'delete' to delete this account.");
    ConsoleUtil.echo("Enter 'r' to return to last page");
    String selection = scan.nextLine();

    switch (selection) {
    case "d":
      return new AccountDepositPrompt().run(accountId);
    case "w":
      return new AccountWithdrawPrompt().run(accountId);
    case "delete":
      return new ComfirmDeleteAccountPrompt().run(accountId);
    case "r":
      return new UserMainActionsPrompt();
    default:
      ConsoleUtil.echo("Invalid selection, try again.\n");
      return this;
    }
  }

  @Override
  public Prompt run() {
    // won' be used at all but point to the last prompt just in case
    return new UserMainActionsPrompt();
  }

}
