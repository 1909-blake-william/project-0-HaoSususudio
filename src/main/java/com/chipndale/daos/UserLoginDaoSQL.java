package com.chipndale.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chipndale.models.UserLogin;
import com.chipndale.util.DBConnectionUtil;

public class UserLoginDaoSQL implements UserLoginDao {

  private Logger log = Logger.getRootLogger();

  UserLogin extractUser(ResultSet rs) throws SQLException {
    String rsUsername = rs.getString("username");
    String rsSecureKey = rs.getString("secure_key");
    String rsSalt = rs.getString("salt_for_password");
    String rsRole = rs.getString("role");
    return new UserLogin(rsUsername, rsSecureKey, rsSalt, rsRole);
  }

  @Override
  public int save(UserLogin u) {
    log.debug("attempting to find user by credentials from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      CallableStatement cs = c.prepareCall("call new_user_login(?,?,?)");
      cs.setString(1, u.getUsername());
      cs.setString(2, u.getSecureKey());
      cs.setString(3, u.getSaltForPassword());
      cs.execute();

      return 1;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public List<UserLogin> findAll() {
    log.debug("Attempting to find all users from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM user_login";
      PreparedStatement ps = c.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      List<UserLogin> users = new ArrayList<UserLogin>();
      while (rs.next()) {
        users.add(extractUser(rs));
      }
      return users;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public UserLogin findByUsername(String username) {
    log.debug("Attempting to find user by username from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM user_login WHERE username = ?";
      PreparedStatement ps = c.prepareStatement(sql);
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return extractUser(rs);
      } else {
        return null;
      }

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return null;
  }

}
