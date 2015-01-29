package data;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbConnection {
	
	private static DbConnection c_This = null;
	
	private DbConnection(){}
	
	public static DbConnection getInstance()
	{
		if(c_This == null) {
			c_This = new DbConnection();
		}
		
		return c_This;
	}
	
	public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		
			//For Amazon EC2 Access
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/askme_db","root", "YOUR_PASSWORD");
			
		}
		catch(Exception ex)
		{
			return null;
		}
	}

}
