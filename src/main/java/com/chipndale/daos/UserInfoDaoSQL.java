package com.chipndale.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chipndale.models.UserInfo;
import com.chipndale.util.DBConnectionUtil;

public class UserInfoDaoSQL implements UserInfoDao {

  private Logger log = Logger.getRootLogger();

  UserInfo extractUserInfo(ResultSet rs) throws SQLException {
    int userID = rs.getInt("user_id");
    String username = rs.getString("username");
    String firstName = rs.getString("first_name");
    String lastName = rs.getString("last_name");
    String phoneNumber = rs.getString("phone_number");
    String email = rs.getString("email");
    return new UserInfo(userID, username, firstName, lastName, phoneNumber, email);
  }

  @Override
  public int save(UserInfo u) {
    log.debug("attempting to save user info");
    try (Connection c = DBConnectionUtil.getConnection()) {

      CallableStatement cs = c.prepareCall("call new_user_info(?,?,?,?,?,?)");
      cs.setString(1, u.getUsername());
      cs.setString(2, u.getFirstName());
      cs.setString(3, u.getLastName());
      cs.setString(4, u.getPhoneNumber());
      cs.setString(5, u.getEmail());
      cs.registerOutParameter(6, Types.INTEGER);
      cs.execute();

      int generatedId = cs.getInt(6);
      log.debug("generated user id is: " + generatedId);
      return generatedId;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public List<UserInfo> findAll() {
    log.debug("Attempting to find all users from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM user_info ORDER BY user_id";
      PreparedStatement ps = c.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      List<UserInfo> userinfo = new ArrayList<UserInfo>();
      while (rs.next()) {
        extractUserInfo(rs).toString();
        userinfo.add(extractUserInfo(rs));
      }
      return userinfo;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public UserInfo findById(int userId) {
    log.debug("Attempting to find UserInfo by userId");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM user_info WHERE user_id = ?";
      PreparedStatement ps = c.prepareStatement(sql);
      ps.setInt(1, userId);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return extractUserInfo(rs);
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public UserInfo findByUserame(String username) {
    log.debug("Attempting to find UserInfo by username");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM user_info WHERE username = ?";
      PreparedStatement ps = c.prepareStatement(sql);
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return extractUserInfo(rs);
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
