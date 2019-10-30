package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.actions.AccountActions;
import com.chipndale.daos.AccountDao;
import com.chipndale.util.ConsoleUtil;

public class ComfirmDeleteAccountPrompt implements Prompt {
  private Scanner scan = new Scanner(System.in);
  AccountDao accDao = AccountDao.currentImplementation;

  public Prompt run(int accountId) {
    ConsoleUtil.echo("Confirm deletion of this account? (Y/N)");
    String selection = scan.nextLine();

    if (selection.equals("y") || selection.contentEquals("Y")) {
      AccountActions.deactivate(accountId);
      ConsoleUtil.echo("Account has been deleted");
      return new UserMainActionsPrompt();
    } else {
      return new AccountActionsPromt().run(accountId);
    }
  }

  @Override
  public Prompt run() {
    // won' be used at all but point to the last prompt just in case
    return new UserMainActionsPrompt();
  }
}
