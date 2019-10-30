package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.util.ConsoleUtil;

public class MainLoginPrompt implements Prompt {

  private Scanner scan = new Scanner(System.in);
//  private AuthUtil authUtil = AuthUtil.instance;

  public Prompt run() {
    ConsoleUtil.echo("Welcome to Chip'n'Dale Acorn Holdings, LLC.");
    ConsoleUtil.echo("Press Enter to log into your accounts.");
    ConsoleUtil.echo("Enter 'new' to create an new user log in.");

    // TODO Auto-generated method stub

    String selection = scan.nextLine();

    switch (selection) {
    case "":
      return new UserLoginPrompt();
    case "new":
      return new CreateANewUserLoginPrompt();
    case "admin":
      return new AdminLoginPrompt();
    default:
      ConsoleUtil.echo("Invalid command, try again.\n");
      return this;
    }

  }

}
