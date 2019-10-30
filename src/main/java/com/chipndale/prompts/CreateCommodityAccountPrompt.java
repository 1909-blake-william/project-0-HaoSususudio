package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.actions.UserActions;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;

public class CreateCommodityAccountPrompt implements Prompt {
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;

  @Override
  public Prompt run() {
    String currentUsername = authUtil.getCurrentUserLogin().getUsername();
    ConsoleUtil.echo("Enter 'a' to create an acron account.");
    ConsoleUtil.echo("Enter 'b' to create a bone account.");
    ConsoleUtil.echo("Enter 'g' to create a gold nugget account.");
    ConsoleUtil.echo("Enter 'h' to create a honey account.");
    ConsoleUtil.echo("Enter 'r' to return to the previous page.");
    String selection = scan.nextLine();
    double initialBalance = 0;

    switch (selection) {
    case "a":
      System.out.println("How much do you want to deposit initially?");
      selection = scan.nextLine();
      initialBalance = Double.valueOf(selection);
      UserActions.createAccount(currentUsername, "acron", initialBalance);
      return new UserMainActionsPrompt();
    case "b":
      System.out.println("How much do you want to deposit initially?");
      selection = scan.nextLine();
      initialBalance = Double.valueOf(selection);
      UserActions.createAccount(currentUsername, "bone", initialBalance);
      return new UserMainActionsPrompt();
    case "g":
      System.out.println("How much do you want to deposit initially?");
      selection = scan.nextLine();
      initialBalance = Double.valueOf(selection);
      UserActions.createAccount(currentUsername, "gold_nugget", initialBalance);
      return new UserMainActionsPrompt();
    case "h":
      System.out.println("How much do you want to deposit initially?");
      selection = scan.nextLine();
      initialBalance = Double.valueOf(selection);
      UserActions.createAccount(currentUsername, "honey", initialBalance);
      return new UserMainActionsPrompt();
    case "r":
      return new UserMainActionsPrompt();
    default:
      ConsoleUtil.echo("Invalid selection, try again.\n");
      return this;
    }
  }
}
