package com.chipndale.daos;

import java.util.List;

import com.chipndale.models.UserInfo;

public interface UserInfoDao {
  UserInfoDao currentImplementation = new UserInfoDaoSQL();

  int save(UserInfo userinfo);

  List<UserInfo> findAll();

  UserInfo findById(int userId);

  UserInfo findByUserame(String username);

}
