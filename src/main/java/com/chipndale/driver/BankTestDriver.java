package com.chipndale.driver;

import java.time.ZonedDateTime;

import com.chipndale.actions.AdminActions;

public class BankTestDriver {
  public static void main(String[] args) {
//    AuthUtil authUtil = AuthUtil.instance;
//    UserLoginDao userLoginDao = UserLoginDao.currentImplementation;
//    UserInfoDao userInfodao = UserInfoDao.currentImplementation;
//    AccountDao accDao = AccountDao.currentImplementation;

//    Iterable<UserLogin> userlogins = userLoginDao.findAll();
//    ConsoleUtil.printAsTable(userlogins);
//    ConsoleUtil.printAsTable(userInfodao.findById(5));
//    AdminActions.viewUserAccounts("mickey");
//    for (User u : users) {
//      System.out.println(u);
//    }
    System.setProperty("user.timezone", "America/New_York");
    System.out.println(ZonedDateTime.now());
    System.out.println();
    AdminActions.viewAllTransactions();
//    String salt = AuthUtil.generateSalt().get();
//    String key = AuthUtil.hashPassword("pass", salt).get();
//    System.out.println(key);
//    System.out.println(key.length());
//    System.out.println(salt);
//    System.out.println(salt.length());
//    System.out.println(AuthUtil.verifyPassword("pass", "YKdlCHGaXu4k18nU5evfBA==", "oAkxJeAq8NpTtZsvF+cNJg=="));

//    AdminActions.viewAllUsers();
//    System.out.println(authUtil.loginAsAdmin("mickey", "pass").toString());
//    users.forEach(user -> {
//      System.out.println(user);
//    });

  }
}
