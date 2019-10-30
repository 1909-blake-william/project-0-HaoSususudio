package com.chipndale.prompts;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.chipndale.models.UserLogin;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;
import com.chipndale.util.TempObjUtil;

public class CreateANewUserLoginPrompt implements Prompt {
  private Logger log = Logger.getRootLogger();
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;
  UserLogin tempUser = TempObjUtil.userLoginInstance;

//  private User user = new User();

  @Override
  public Prompt run() {
    ConsoleUtil.echo("Please create your username:");
    String inputUsername = scan.nextLine().toLowerCase();

    if (authUtil.ifUserExistsInDB(inputUsername)) {
      log.debug("duplicated username is used during creation of a new account");
      ConsoleUtil.echo("Username already exists. Please try another one.");
      return this;
    } else {
      tempUser.setUsername(inputUsername);
      return new CreateNewPasswordPrompt();
    }
  }
}
