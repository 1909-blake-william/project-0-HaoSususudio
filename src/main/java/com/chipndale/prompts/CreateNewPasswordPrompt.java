package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.daos.UserLoginDao;
import com.chipndale.models.UserLogin;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;
import com.chipndale.util.TempObjUtil;

public class CreateNewPasswordPrompt implements Prompt {
//  private Logger log = Logger.getRootLogger();
  private Scanner scan = new Scanner(System.in);
  UserLogin tempUser = TempObjUtil.userLoginInst;
  private UserLoginDao userDao = UserLoginDao.currentImplementation;

  @Override
  public Prompt run() {
    ConsoleUtil.echo("Please enter the password.");
    String password = scan.nextLine();
    ConsoleUtil.echo("Please re-enter the password.");
    String passwordRe = scan.nextLine();

    if (!password.equals(passwordRe)) {
      ConsoleUtil.echo("Passwords don't match. Please try again");
      return new CreateNewPasswordPrompt();
    } else {
      String salt = AuthUtil.generateSalt().get();
      String key = AuthUtil.hashPassword(password, salt).get();
      tempUser.setSaltForPassword(salt);
      tempUser.setSecureKey(key);
      userDao.save(tempUser);
      ConsoleUtil.echo("A new user is created.");
      return new MainLoginPrompt();
    }

  }

}
