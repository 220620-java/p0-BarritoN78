package com.revature.p0.data;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.p0.Transaction;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.List;
import com.revature.p0.util.SQLConnect;

public class TransactionDAO{
	private SQLConnect sqlConn = SQLConnect.getSQLConnect();

	public Transaction insert(Transaction trans) {
		String sql = "INSERT INTO tbl_transactions(transid, transtype, transprebalance, transpostbalance, transnotes, accountid) "
			+"values ( default, "
	        +" ?, "
	        +" ?, "
	        +" ?, "
	        +" ?, "
	        +" ?, "
	        +" ?";
		String[] keys = {"transid"};
		try(Connection conn = sqlConn.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql,keys);

		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return trans;
	}

	public List<Transaction> findByID(int id) {
		List<Transaction> list = new ArrayList<Transaction>();
		return list;
	}
}
