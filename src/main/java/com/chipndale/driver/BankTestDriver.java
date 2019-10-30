package com.chipndale.driver;

import com.chipndale.daos.AccountDao;
import com.chipndale.daos.UserInfoDao;

public class BankTestDriver {
  public static void main(String[] args) {
//    AuthUtil authUtil = AuthUtil.instance;
//    UserLoginDao userLoginDao = UserLoginDao.currentImplementation;
    UserInfoDao userInfodao = UserInfoDao.currentImplementation;
    AccountDao accDao = AccountDao.currentImplementation;

//    Iterable<UserLogin> userlogins = userLoginDao.findAll();
//    ConsoleUtil.printAsTable(userlogins);
//    ConsoleUtil.printAsTable(userInfodao.findById(5));
//    AdminActions.viewUserAccounts("mickey");
//    for (User u : users) {
//      System.out.println(u);
//    }

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
