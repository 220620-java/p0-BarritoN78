package com.revature.p0.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import com.revature.p0.Transaction;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.List;
import com.revature.p0.util.SQLConnect;

public class TransactionDAO{
	private SQLConnect sqlConn = SQLConnect.getSQLConnect();

	public Transaction insert(Transaction trans) {
		String sql = "INSERT INTO tbl_transactions(transid, transdate, transtype, transprebalance, transpostbalance, transnotes, accountid) "
			+"values ( default, "
	        +" ?, "
	        +" ?, "
	        +" ?, "
	        +" ?, "
	        +" ?, "
	        +" ?)";
		String[] keys = {"transid"};
		try(Connection conn = sqlConn.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql,keys);
			stmt.setDate(1, trans.getDate());
			stmt.setString(2, trans.getType());			
			stmt.setDouble(3, trans.getAccountBal());
			stmt.setDouble(4, trans.getNewBal());
			stmt.setString(5, trans.getNotes());
			stmt.setInt(6, trans.getAccID());
			stmt.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			trans = null;
			e.printStackTrace();
		}		
		return trans;
	}

	public List<Transaction> findByID(int accID) {
		/*Local Variables*/
		List<Transaction> acc = new ArrayList<Transaction>();
		Transaction in;
		String sql = "select  "
				+ "transid, "
				+ "transtype, "
				+ "transprebalance, "
				+ "transpostbalance, "
				+ "transnotes, "				
				+ "transdate "
				+ "from tbl_transactions "
				+ "where accountid = ?";
		ResultSet result;
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accID);
			result = stmt.executeQuery();
			while(result.next()) {
				in = new Transaction(accID);
				in.setTransID(result.getInt("transid"));
				in.setType(result.getString("transtype"));
				in.setNotes(result.getString("transnotes"));
				in.setAccountBal(result.getDouble("transprebalance"));
				in.setNewBal(result.getDouble("transpostbalance"));
				in.setDate(LocalDate.parse(result.getDate("transdate").toString()));
				
				acc.add(in);
			}
			conn.close();
			return acc;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
