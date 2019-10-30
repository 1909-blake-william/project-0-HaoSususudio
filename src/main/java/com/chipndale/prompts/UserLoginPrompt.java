package com.chipndale.prompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.chipndale.models.UserLogin;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;
import com.chipndale.util.TempObjUtil;

public class UserLoginPrompt implements Prompt {
  private Logger log = Logger.getRootLogger();
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;
  UserLogin tempUser = TempObjUtil.userLoginInstance;

  @Override
  public Prompt run() {
    log.debug("Attempting to login.");

    ConsoleUtil.echo("Please enter your username.");
    String username = scan.nextLine().toLowerCase();
    ConsoleUtil.echo("Please enter the password.");
    String password = scan.nextLine();

    if (authUtil.login(username, password) == null) {
      log.debug("failed to login due to credentials");
      ConsoleUtil.echo("Invalid Credentials");
      return new MainLoginPrompt();
    } else {
      log.debug("successfully logged in");
      return new UserMainActionsPrompt();
    }

  }

}
