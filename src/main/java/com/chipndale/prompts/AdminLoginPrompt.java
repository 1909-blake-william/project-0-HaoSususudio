package com.chipndale.prompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;

public class AdminLoginPrompt implements Prompt {
  private Logger log = Logger.getRootLogger();
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;

  @Override
  public Prompt run() {
    log.debug("Attempting to login as Admin.");

    ConsoleUtil.echo("Please enter your username.");
    String username = scan.nextLine().toLowerCase();
    ConsoleUtil.echo("Please enter the password.");
    String password = scan.nextLine();

    if (authUtil.loginAsAdmin(username, password) == null) {
      log.debug("failed to login as admin due to credentials");
      ConsoleUtil.echo("Invalid Credentials");
      return new MainLoginPrompt();
    } else {
      log.debug("successfully logged in as Admin.");
      return new AdminActionsPrompt();
    }

  }

}