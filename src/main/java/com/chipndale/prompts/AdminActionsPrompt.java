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
    ConsoleUtil.echo("Enter 'u' to view all users info.");
    ConsoleUtil.echo("Enter 'a' to view all accounts.");
    ConsoleUtil.echo("Enter 'q' to log out");

    String selection = scan.nextLine();

    switch (selection) {
    case "u":
      log.debug("Admin attempting to view all users.");
      AdminActions.viewAllUserInfo();
      AdminActions.viewAllUserLogins();
      return this;
    case "a":
      log.debug("Admin attempting to view all accounts.");
      AdminActions.viewAllAccounts(); 
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
