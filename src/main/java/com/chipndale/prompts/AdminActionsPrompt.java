package com.chipndale.prompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.chipndale.actions.AdminActions;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;

public class AdminActionsPrompt implements Prompt {
  private Logger log = Logger.getRootLogger();
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;

  @Override
  public Prompt run() {
    ConsoleUtil.echo("Welcome Admin!");
    ConsoleUtil.echo("What do you want to do?");
    ConsoleUtil.echo("Enter 'u' to view all users' logins and info.");
    ConsoleUtil.echo("Enter 'a' to view all accounts.");
    ConsoleUtil.echo("Enter 't' to view all transactions.");
    ConsoleUtil.echo("Enter 'q' to log out");

    String selection = scan.nextLine();

    switch (selection) {
    case "u":
      log.debug("Admin attempting to view all users.");
      AdminActions.viewAllUserLogins();
      AdminActions.viewAllUserInfo();
      return this;
    case "a":
      log.debug("Admin attempting to view all accounts.");
      AdminActions.viewAllAccounts();
      return this;
    case "t":
      log.debug("Admin attempting to view all transactions.");
      AdminActions.viewAllTransactions();
      return this;
    case "q":
      authUtil.logout();
      return new MainLoginPrompt();
    default:
      ConsoleUtil.echo("Invalid selection, try again.\n");
      return this;
    }

  }

}
