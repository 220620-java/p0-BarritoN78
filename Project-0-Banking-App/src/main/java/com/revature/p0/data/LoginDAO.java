package com.revature.p0.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.p0.Login;
import com.revature.p0.util.List;
import com.revature.p0.util.SQLConnect;
import com.revature.p0.util.ArrayList;

public class LoginDAO {
	private SQLConnect sqlConn = SQLConnect.getSQLConnect();

	public Login findByEmail(String email) {
		/* Local Variables */
		Login log = new Login();
		ResultSet result = null;
		String sql = "select userpassword from tbl_users where useremail = ?";

		/* Function */
		try (Connection conn = sqlConn.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			result = stmt.executeQuery();
			if (result.next()) {
				log.setPassword(result.getString(1));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Output*/
		return log;
	}

}
