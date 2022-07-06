package com.revature.p0.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.p0.Account;
import com.revature.p0.P0Main;
import com.revature.p0.util.List;
import com.revature.p0.util.SQLConnect;
import com.revature.p0.util.ArrayList;

public class AccountDAO {
	private SQLConnect sqlConn = SQLConnect.getSQLConnect();

	public List<Account> findByUserID(int userID) {
		/*Local Variables*/
		List<Account> acc = new ArrayList<Account>();
		Account in;
		String sql = "select  "
				+ "accountid, "
				+ "accounttype, "
				+ "accountnotes, "
				+ "accountbalance "
				+ "from tbl_accounts "
				+ "where userid = ? "
				+ "order by accountid";
		ResultSet result;
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			result = stmt.executeQuery();
			while(result.next()) {
				in = new Account(userID);
				in.setAccID(result.getInt("accountid"));
				in.setType(result.getString("accounttype"));
				in.setAccNotes(result.getString("accountnotes"));
				in.setAccBal(result.getDouble("accountbalance"));
				
				acc.add(in);
			}
			conn.close();
			return acc;
		} 
		catch (Exception e) {
			P0Main.exceptionLogger(e);
			return null;
		}
	}
	
	public Account findByAccIDAndUserID(int accID, int userID) {
		/*Local Variables*/
		Account acc = null;
		String sql = "select  "
				+ "accountid, "
				+ "accounttype, "
				+ "accountnotes, "
				+ "accountbalance "
				+ "from tbl_accounts "
				+ "where accountid = ? "
				+ "and userid = ?";
		ResultSet result;
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accID);
			stmt.setInt(2, userID);
			result = stmt.executeQuery();
			if(result.next()) {
				acc = new Account(userID);
				acc.setAccID(result.getInt("accountid"));
				acc.setType(result.getString("accounttype"));
				acc.setAccNotes(result.getString("accountnotes"));
				acc.setAccBal(result.getDouble("accountbalance"));
			}
			conn.close();
			return acc;
		} 
		catch (Exception e) {
			P0Main.exceptionLogger(e);
			return null;
		}
		finally {
		}
	}
	
	public Account insert(Account acc) {
		/* Local Variables */
		String sql = "insert into tbl_accounts(accountid, accounttype, accountnotes, accountbalance, userid)"
				+ "values(default, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?) ";
		String[] keys = {"accountid"};

		/* Function */
		try (Connection conn = sqlConn.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, acc.getType());
			stmt.setString(2, acc.getAccNotes());
			stmt.setDouble(3, acc.getAccBal());
			stmt.setInt(4, acc.getUserID());
			stmt.executeUpdate();
			conn.close();
			return acc;
		} 
		catch (Exception e) {
			P0Main.exceptionLogger(e);
			return null;
		}
	}
	
	public Boolean updateBalance(double newBal, int accID) {
		/*Local Variables*/
		Boolean SQLSuccess = false;
		String sql = "update tbl_accounts set "
				+ "accountbalance = ? "
				+ "where accountid = ?";
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, newBal);
			stmt.setInt(2, accID);
			stmt.executeUpdate();
			conn.close();
			SQLSuccess = true;
		}
		catch(Exception e){
			P0Main.exceptionLogger(e);
			SQLSuccess = false;
		}
		
		/*Output*/
		return SQLSuccess;
	}
}
