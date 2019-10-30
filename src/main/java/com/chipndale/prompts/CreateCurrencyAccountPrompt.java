package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.actions.UserActions;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;

public class CreateCurrencyAccountPrompt implements Prompt {
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;

  @Override
  public Prompt run() {
    String currentUsername = authUtil.getCurrentUserLogin().getUsername();
    ConsoleUtil.echo("Enter 'u' to choose USD as currency.");
    ConsoleUtil.echo("Enter 'c' to choose RMB as currency.");
    ConsoleUtil.echo("Enter 'r' to return to the previous page.");
    String selection = scan.nextLine();
    double initialBalance = 0;

    switch (selection) {

    case "u":
      System.out.println("How much do you want to deposit initially?");
      selection = scan.nextLine();
      initialBalance = Double.valueOf(selection);
      UserActions.createAccount(currentUsername, "USD", initialBalance);
      return new UserMainActionsPrompt();
    case "c":
      System.out.println("How much do you want to deposit initially?");
      selection = scan.nextLine();
      initialBalance = Double.valueOf(selection);
      UserActions.createAccount(currentUsername, "RMB", initialBalance);
      return new UserMainActionsPrompt();
    case "r":
      return new UserMainActionsPrompt();
    default:
      ConsoleUtil.echo("Invalid selection, try again.\n");
      return this;
    }
  }

}
