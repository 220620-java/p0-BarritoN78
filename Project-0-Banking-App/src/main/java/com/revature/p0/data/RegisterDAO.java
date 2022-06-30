package com.revature.p0.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.p0.Register;
import com.revature.p0.util.List;
import com.revature.p0.util.SQLConnect;
import com.revature.p0.util.ArrayList;

public class RegisterDAO {
	private SQLConnect sqlConn = SQLConnect.getSQLConnect();
	
	public Register insert(Register reg) {
		// TODO Auto-generated method stub
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
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Output*/
		return records;
	}

}
