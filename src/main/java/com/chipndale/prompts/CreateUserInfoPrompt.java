package com.chipndale.prompts;

import java.util.Scanner;

import com.chipndale.actions.UserActions;
import com.chipndale.daos.UserInfoDao;
import com.chipndale.models.UserInfo;
import com.chipndale.util.AuthUtil;
import com.chipndale.util.ConsoleUtil;
import com.chipndale.util.TempObjUtil;

public class CreateUserInfoPrompt implements Prompt {
  private Scanner scan = new Scanner(System.in);
  private AuthUtil authUtil = AuthUtil.instance;
  UserInfo tempUserInfo = TempObjUtil.userInfoInstance;
  private UserInfoDao userInfoDao = UserInfoDao.currentImplementation;

  @Override
  public Prompt run() {
    String username = authUtil.getCurrentUserLogin().getUsername();
    tempUserInfo.setUsername(username);
    ConsoleUtil.echo("Hello " + username + ". Please fill out the following information.");
    ConsoleUtil.echo("Please enter your first name:");
    tempUserInfo.setFirstName(scan.nextLine());
    ConsoleUtil.echo("Please enter your last name:");
    tempUserInfo.setLastName(scan.nextLine());
    ConsoleUtil.echo("Please enter your phone number:");
    tempUserInfo.setPhoneNumber(scan.nextLine());
    ConsoleUtil.echo("Please enter your email:");
    tempUserInfo.setEmail(scan.nextLine());
    userInfoDao.save(tempUserInfo);
    ConsoleUtil.echo("Thank you.");
    UserActions.showUserInfo();
    return new UserMainActionsPrompt();
  }

}
