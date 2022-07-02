package com.revature.p0.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.p0.Register;
import com.revature.p0.util.SQLConnect;

public class RegisterDAO {
	private SQLConnect sqlConn = SQLConnect.getSQLConnect();
	
	public Register insert(Register reg) {
		/* Local Variables */
		String sql = "insert into tbl_users(userid, useremail, userpassword, userfname, userminit, userlname) "
				+ "values(default, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, "
				+ "?, ";
		String[] keys = {"userid"};

		/* Function */
		try (Connection conn = sqlConn.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, reg.getEmail());
			stmt.setString(2, reg.getPassword());
			stmt.setString(3, reg.getFName());
			stmt.setString(4, reg.getMInit());
			stmt.setString(5, reg.getLName());
			stmt.executeUpdate();
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Output*/	
		return reg;
	}

	/*Return a count of how many records exist with a given email*/
	public int findByEmail(String email) {
		/* Local Variables */
		int records = -1;
		ResultSet result = null;
		String sql = "select count(*) as count from tbl_users where useremail = ?";

		/* Function */
		try (Connection conn = sqlConn.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			if (result.next()) {
				records = result.getInt("count");
			}
			conn.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Output*/
		return records;
	}

}
