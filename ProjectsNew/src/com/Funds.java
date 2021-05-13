package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Funds {
	//Connect DB
	
		private Connection connect()
		{
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				
				con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/newdb","root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return con;

		}
		
		
		public String addFund(String admin_ID, String product_ID, String funds)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for adding.";
			}
			
			// create a prepared statement for insert funds
			String query = " insert into funds (`fund_ID`,`admin_ID`,`product_ID`,`funds`)"+ " values (?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, admin_ID); 
			preparedStmt.setString(3, product_ID);
			preparedStmt.setDouble(4, Double.parseDouble(funds));
			// execute insert statement

			preparedStmt.execute();
			
			con.close();
			
			String newFunds = readFunds();
			output = "{\"status\":\"success\", \"data\": \"" +
					newFunds + "\"}";
			
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while adding the fund.\"}";
				System.err.println(e.getMessage());
		}
			
		return output;
	}


	public String readFunds()
	{
			String output = "";
			try
			{
					Connection con = connect();
					
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				
				// Prepare table to display funds details
		        
				output = "<table border='1'><tr><th>Funds ID</th><th>Admin ID</th>" +
						 "<th>Product ID</th>" + 
						 "<th>Funds</th>" + 
						 "<th>Update</th><th>Remove</th></tr>"; 
		
				String query = "select * from funds";
				
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			
			
			while (rs.next())
			{
				String fund_ID  = Integer.toString(rs.getInt("fund_ID")); 
				 String admin_ID = rs.getString("admin_ID"); 
				 String product_ID = rs.getString("product_ID");
				 String funds = Double.toString(rs.getDouble("funds"));
				
				// Add funds details into table
				output += "<tr><td>" + fund_ID + "</td>"; 
				output += "<td>" + admin_ID + "</td>"; 
				output += "<td>" + product_ID + "</td>"; 
				output += "<td>" + funds + "</td>";
	           
	              
				// Edit and Update buttons
	            
	            output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-fundid='" + fund_ID + "'></td>"
	            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
	            		+ "class = 'btnRemove btn btn-danger' data-fundid='" + fund_ID + "'>"
	            		+"</td></tr>";
	            		
			}
			
			con.close();
			
			output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the funds";
				System.err.println(e.getMessage());
			}
			
			return output;
		}


		public String updateFunds(String ID, String admin_ID, String product_ID, String funds)
		{
				String output = "";
				try
				{
						Connection con = connect();
						
						if (con == null)
						{
							return "Error while connecting to the database for updating.";
						}
						
						// create a prepared statement for update funds details
						String query = "UPDATE funds SET admin_ID=?,product_ID=?,funds=? WHERE fund_ID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values
						preparedStmt.setString(1, admin_ID); 
						preparedStmt.setString(2, product_ID);
						preparedStmt.setDouble(3, Double.parseDouble(funds));
						 
						preparedStmt.setInt(4, Integer.parseInt(ID));
						// execute update statement
						preparedStmt.execute();
						
						con.close();
						
						String newFunds = readFunds();
						output = "{\"status\":\"success\", \"data\": \"" +
								newFunds + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\": \"Error while updating the Funds.\"}";
					System.err.println(e.getMessage());
				}
				return output;
		}
		
		
	public String deleteFunds(String fund_ID)
	{
		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
				{	
					return "Error while connecting to the database for deleting."; 
				}
				
				// create a prepared statement for delete funds
				
				String query = "delete from funds where fund_ID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setInt(1, Integer.parseInt(fund_ID));
				// execute delete statement
				preparedStmt.execute();
				
				con.close();
				
				String newFunds = readFunds();
				output = "{\"status\":\"success\", \"data\": \"" +
						newFunds + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the fund.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
		}

}
