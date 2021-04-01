package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteDB 
{
	Connection c=null;
	Statement stmt=null;
	
	SqliteDB()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:Contact.db");
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
	}
	public void closeConnection()
	{
		try
		{
			c.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public ResultSet executeQuery(String query)
	{
		try
		{
			this.stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			return rs;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public int executeUpdate(String query)
	{
		try
		{
			this.stmt=c.createStatement();
			int i=stmt.executeUpdate(query);
			return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}

