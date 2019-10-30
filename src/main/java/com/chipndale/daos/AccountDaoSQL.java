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

import com.chipndale.models.Account;
import com.chipndale.util.DBConnectionUtil;

public class AccountDaoSQL implements AccountDao {

  private Logger log = Logger.getRootLogger();

  Account extractAccount(ResultSet rs) throws SQLException {
    int accountID = rs.getInt("account_id");
    String ownerUsername = rs.getString("owner_username");
    String accountType = rs.getString("account_type");
    String designation = rs.getString("designation");
    String unit = rs.getString("unit");
    double balance = rs.getDouble("balance");
    String accountStatus = rs.getString("account_status");
    return new Account(accountID, ownerUsername, accountType, designation, unit, balance, accountStatus);
  }

  @Override
  public int save(Account accountBeingSaved) {
    log.debug("attempting to save an Account");
    try (Connection c = DBConnectionUtil.getConnection()) {

      CallableStatement cs = c.prepareCall("call create_new_account(?,?,?,?,?,?)");
      cs.setString(1, accountBeingSaved.getOwnerUsername());
      cs.setString(2, accountBeingSaved.getAccountType());
      cs.setString(3, accountBeingSaved.getDesignation());
      cs.setString(4, accountBeingSaved.getUnit());
      cs.setDouble(5, accountBeingSaved.getBalance());
      cs.registerOutParameter(6, Types.INTEGER);
      cs.execute();

      int generatedId = cs.getInt(6);
      log.debug("generated Account id is: " + generatedId);
      return generatedId;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int update(Account account) {
    int accountId = account.getAccountID();
    double balance = account.getBalance();
    String status = account.getAccountStatus();
    log.debug("attempting to update account " + accountId);
    try (Connection c = DBConnectionUtil.getConnection()) {

      CallableStatement cs = c.prepareCall("call update_account(?,?,?)");
      cs.setInt(1, accountId);
      cs.setDouble(2, balance);
      cs.setString(3, status);
      cs.execute();

      log.debug("account with id: " + accountId + " is updated");
      return accountId;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public List<Account> findAll() {
    log.debug("Attempting to find all accounts from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM account ORDER BY account_id";
      PreparedStatement ps = c.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      List<Account> allAccounts = new ArrayList<Account>();
      while (rs.next()) {
        extractAccount(rs).toString();
        allAccounts.add(extractAccount(rs));
      }
      return allAccounts;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Account> findAllByUsername(String username) {
    log.debug("Attempting to find all accounts by username from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM account WHERE owner_username = ? ORDER BY account_id";

      PreparedStatement ps = c.prepareStatement(sql);
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();

      List<Account> allAccounts = new ArrayList<Account>();
      while (rs.next()) {
        extractAccount(rs).toString();
        allAccounts.add(extractAccount(rs));
      }
      return allAccounts;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Account> findAllActiveByUsername(String username) {
    log.debug("Attempting to find all accounts by username from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM account WHERE owner_username = ? AND account_status = 'active'";

      PreparedStatement ps = c.prepareStatement(sql);
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();

      List<Account> allAccounts = new ArrayList<Account>();
      while (rs.next()) {
        extractAccount(rs).toString();
        allAccounts.add(extractAccount(rs));
      }
      return allAccounts;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Account findById(int accountId) {
    log.debug("Attempting to find account by accountId");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM account WHERE account_id = ?";
      PreparedStatement ps = c.prepareStatement(sql);
      ps.setInt(1, accountId);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        return extractAccount(rs);
      } else {
        return null;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
