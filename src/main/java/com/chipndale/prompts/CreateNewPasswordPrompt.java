package com.chipndale.prompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;

public class CreateNewPasswordPrompt implements Prompt {
  private Logger log = Logger.getRootLogger();
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;

  public Prompt run(String username) {
    ConsoleUtil.echo("Please enter the password.");
    String password = scan.nextLine();
    ConsoleUtil.echo("Please re-enter the password.");
    String passwordRe = scan.nextLine();
    if (!password.equals(passwordRe)) {
      ConsoleUtil.echo("Passwords don't match. Please try again");
      return new CreateNewPasswordPrompt().run(username);
    } else {
      authUtil.generateAndSaveNewUserLogin(username, password);
      ConsoleUtil.echo("A new user login is created for you!\n");
      log.debug("a new user login is created.");
      authUtil.login(username, password);
      log.debug("successfully logged in");
      return new UserMainActionsPrompt();
    }
  }

  @Override
  public Prompt run() {
    return new MainLoginPrompt();
  }

}
