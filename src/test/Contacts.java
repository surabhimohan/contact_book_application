package test;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Contacts {
	private int id;
	private Type type=Type.PERSONAL;
	private String name, nickName, city, email, phNumber;
	
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private String datetime = formatter.format(date);

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static void listContacts() {
		try {
			SqliteDB db = new SqliteDB();
			String query = "SELECT * FROM Contacts";
			ResultSet rs = db.executeQuery(query);
			printTable(rs);
			db.closeConnection();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void listContactsByName() {
		try {
			SqliteDB db = new SqliteDB();
			String query = "SELECT * FROM Contacts ORDER BY name";
			ResultSet rs = db.executeQuery(query);
			printTable(rs);
			db.closeConnection();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	static void printTable(ResultSet rs) {
		try {
			if (rs != null) {
				int count = 0;

				System.out.printf("%5s %10s %10s %10s %10s %10s %10s %10s\n",
						"ID", "Name", "Nick Name", "City", "Email",
						"Phone Number", "Datetime", "Type");
				System.out
						.println("-------------------------------------------------------------------------------------------------------------------");
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickName");
					String city = rs.getString("city");
					String email = rs.getString("email");
					String phNumber = rs.getString("number");
					String datetime = rs.getString("datetime");
					String type = rs.getString("type");
					System.out.printf("%5d %10s %10s %10s %10s %10s %10s %10s",
							id, name, nickName, city, email, phNumber,
							datetime, type);
					System.out.println();
					count++;
				}
				if (count == 0)
					System.out.println("No record found!!");
			}
			System.out
					.println("-------------------------------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert() {
		try {
			SqliteDB db = new SqliteDB();
			String query = String
					.format("INSERT INTO Contacts(name,nickName,city,email,number,datetime,type)VALUES('%s','%s','%s','%s','%s','%s','%s')",
							this.getName(), this.getNickName(), this.getCity(),
							this.getEmail(), this.getPhNumber(),
							this.getDatetime(), this.getType());
			System.out.println(query);
			int row = db.executeUpdate(query);
			System.out.println("No of rows inserted: " + row);
			if (row > 0)
				System.out.println("data inserted");
			db.closeConnection();
		} catch (Exception e) {

		}
	}

	static void search(String searchKey) {
		try {
			SqliteDB db = new SqliteDB();
			String query = String.format(
					"SELECT * FROM Contacts WHERE name='%s' or number='%s'",
					searchKey, searchKey);
			ResultSet rs = db.executeQuery(query);
			printTable(rs);
			db.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void delete(int deleteKey) {
		try {
			SqliteDB db = new SqliteDB();
			String query = String.format("DELETE FROM Contacts WHERE id='%d'",
					deleteKey);
			int i = db.executeUpdate(query);
			if (i == 1)
				System.out.println("Row deleted");
			db.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void edit(String edit, String newValue, int row) {
		try {
			SqliteDB db = new SqliteDB();
			String query = String.format(
					"UPDATE Contacts SET '%s'='%s' WHERE id='%d'", edit,
					newValue, row);
			int i = db.executeUpdate(query);
			if (i == 1)
				System.out.println("Successfully edited");
			db.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void dbStats()
	{
		try {
			SqliteDB db=new SqliteDB();
			String totalCountQuery="SELECT COUNT(*) FROM Contacts";
			ResultSet rs=db.executeQuery(totalCountQuery);
			rs.next();
			System.out.println("\nTotal no. of rows in database: "+rs.getInt(1));
			
			System.out.println("\nContact counts with their total no of Unique Cities");
			String distinctCity="SELECT city, COUNT(*) AS 'num' FROM Contacts GROUP BY city";
			ResultSet rsCity=db.executeQuery(distinctCity);
			System.out.printf("%10s %5s \n-------------------------------------------------------\n","City","Contact Count");
			while(rsCity.next())
			{
				System.out.printf("%10s %5s\n",rsCity.getString("city"),rsCity.getInt("num"));
			}
			System.out.println("\n-------------------------------------------------------\n"+
								"Total no. of Contacts in diffrent types.");
			String distinctType="SELECT type, COUNT(*) AS 'num' FROM Contacts GROUP BY type";
			ResultSet rsType=db.executeQuery(distinctType);
			while(rsType.next())
			{
				System.out.println(rsType.getString("type")+" "+rsType.getInt("num"));
			}
			
			
			
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

enum Type {
	PERSONAL, BUSINESS;
}
