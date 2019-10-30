package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.actions.UserActions;
import com.chipndale.daos.AccountDao;
import com.chipndale.daos.UserInfoDao;
import com.chipndale.models.Account;
import com.chipndale.models.UserInfo;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;

public class UserMainActionsPrompt implements Prompt {
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;
  private UserInfoDao userInfoDao = UserInfoDao.currentImplementation;
  private AccountDao accDao = AccountDao.currentImplementation;

  @Override
  public Prompt run() {
    String currentUsername = authUtil.getCurrentUserLogin().getUsername();
    UserActions.initalizeAccDesignations();

    if (!isUserInfoExists(currentUsername)) {
      return new CreateUserInfoPrompt();
    } else {
      int numOfActiveAccounts = UserActions.showAccounts();
      ConsoleUtil.echo("Please select one of the following options:");
      if (numOfActiveAccounts > 0) {
        ConsoleUtil.echo("Enter the account id number to use an account or view its transactions");
      }
      ConsoleUtil.echo("Enter 'c' to open a new currency account.");
      ConsoleUtil.echo("Enter 'm' to open a new commodity account.");
      ConsoleUtil.echo("Enter 'i' to view your personal info.");
      ConsoleUtil.echo("Enter 'q' to log out");
      String selection = scan.nextLine();

      for (Account acc : accDao.findAllActiveByUsername(currentUsername)) {
        int accountId = acc.getAccountID();
        if (selection.equals(String.valueOf(accountId))) {
          return new AccountActionsPromt().run(accountId);
        }
      }

      switch (selection) {
      case "c":
        return new CreateCurrencyAccountPrompt();
      case "m":
        return new CreateCommodityAccountPrompt();
      case "i":
        return new UserShowInfoPrompt();
      case "q":
        authUtil.logout();
        return new MainLoginPrompt();
      default:
        ConsoleUtil.echo("Invalid selection, try again.\n");
        return this;
      }
    }
  }

  public boolean isUserInfoExists(String username) {
    UserInfo u = userInfoDao.findByUserame(username);
    return u != null ? true : false;
  }

}
