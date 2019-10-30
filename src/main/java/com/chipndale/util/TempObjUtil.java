package com.chipndale.util;

import com.chipndale.models.Account;
import com.chipndale.models.UserInfo;
import com.chipndale.models.UserLogin;

public class TempObjUtil {
  public static final UserLogin userLoginInstance = new UserLogin();
  public static final UserInfo userInfoInstance = new UserInfo();
  public static final Account accountInstance = new Account();
//  private TempUserUtil() {
//    super();
//  }

}
