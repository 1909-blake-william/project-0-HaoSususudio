package com.chipndale.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.chipndale.models.Transaction;
import com.chipndale.util.DBConnectionUtil;

public class TransactionDaoSQL implements TransactionDao {

  private Logger log = Logger.getRootLogger();

  Transaction extractTransaction(ResultSet rs) throws SQLException {
    int transactionId = rs.getInt("transaction_id");
    int accountId = rs.getInt("account_id");
    String tranactionType = rs.getString("transaction_type");
    double deltaBalance = rs.getDouble("delta_balance");
    Timestamp timeStamp = rs.getTimestamp("time_stamp");
    return new Transaction(transactionId, accountId, tranactionType, deltaBalance, timeStamp);
  }

  @Override
  public int save(Transaction transaction) {
    log.debug("attempting to save a transaction to DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      CallableStatement cs = c.prepareCall("call insert_transaction_log(?,?,?,?)");
      cs.setInt(1, transaction.getAccountId());
      cs.setString(2, transaction.getTransactionType());
      cs.setDouble(3, transaction.getDeltaBalance());
      cs.registerOutParameter(4, Types.INTEGER);
      cs.execute();

      int generatedId = cs.getInt(4);
      log.debug("generated transaction id is: " + generatedId);

      return generatedId;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public List<Transaction> findAll() {
    log.debug("Attempting to find all transactions from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM transaction_log ORDER BY transaction_id";
      PreparedStatement ps = c.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();

      List<Transaction> transactions = new ArrayList<Transaction>();
      while (rs.next()) {
        transactions.add(extractTransaction(rs));
      }
      return transactions;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Transaction> findAllByAccountId(int accountId) {
    log.debug("Attempting to find all transactions from DB");
    try (Connection c = DBConnectionUtil.getConnection()) {

      String sql = "SELECT * FROM transaction_log WHERE account_id = ? ORDER BY transaction_id";
      PreparedStatement ps = c.prepareStatement(sql);
      ps.setInt(1, accountId);
      ResultSet rs = ps.executeQuery();

      List<Transaction> transactions = new ArrayList<Transaction>();
      while (rs.next()) {
        transactions.add(extractTransaction(rs));
      }
      return transactions;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
