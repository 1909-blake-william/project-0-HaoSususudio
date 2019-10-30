package com.chipndale.daos;

import java.util.List;

import com.chipndale.models.UserLogin;

public interface UserLoginDao {

  UserLoginDao currentImplementation = new UserLoginDaoSQL();

  int save(UserLogin u);

  List<UserLogin> findAll();

  UserLogin findByUsername(String username);
}
