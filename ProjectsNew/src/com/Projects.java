package com;

import java.sql.*;

public class Projects
{ //Connect DB
	
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
	
	
	public String addProjects(String name, String researcherID, String desc, String price, String email, String phone) 
{
	String output = "";
	try
	{
		Connection con = connect();
		if (con == null)
		{
			return "Error while connecting to the database for adding.";
		}
		
		// create a prepared statement for insert projects
		
		String query = " insert into projects (`projectID`,`projectName`,`researcherID`,`description`,`price`,`email`,`phone`)"+ " values (?, ?, ?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, researcherID);
		 preparedStmt.setString(4, desc);
		 preparedStmt.setDouble(5, Double.parseDouble(price)); 
		 preparedStmt.setString(6, email); 
		 preparedStmt.setString(7, phone);
		 
		// execute insert statement
		preparedStmt.execute();
		con.close();
		
		String newProjects = readProjects();
		output = "{\"status\":\"success\", \"data\": \"" +
				newProjects + "\"}";
		
	}
	catch (Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while adding the project.\"}";
			System.err.println(e.getMessage());
	}
		
	return output;
}


public String readProjects()
{
		String output = "";
		try
		{
				Connection con = connect();
				
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare table to display project details
	        
			output = "<table border='1'><tr><th>Project ID</th><th>Project Name</th>" +
					 "<th>Researcher ID</th>" + 
					 "<th>Description</th>" + 
					 "<th>Price</th>" +
					 "<th>Email</th>" + "<th>Phone</th>" +
					 "<th>Update</th><th>Remove</th></tr>"; 
	
			String query = "select * from projects"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next())
		{
			String projectID  = Integer.toString(rs.getInt("projectID")); 
			String projectName = rs.getString("projectName"); 
			String researcherID = rs.getString("researcherID");
			String description = rs.getString("description"); 
			String price = Double.toString(rs.getDouble("price")); 
			String email = rs.getString("email"); 
			String phone = rs.getString("phone");
			
			// Add projects details into the table
			output += "<tr><td>" + projectID + "</td>"; 
			output += "<td>" + projectName + "</td>"; 
			output += "<td>" + researcherID + "</td>"; 
			output += "<td>" + description + "</td>";
			output += "<td>" + price + "</td>"; 
			output += "<td>" + email + "</td>"; 
			output += "<td>" + phone + "</td>";
           
              
			// Edit and Update buttons
            
			 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary' data-projectid='" + projectID + "'></td>"
	            		+ "<td><input name = 'btnRemove' type='button' value = 'Remove' "
	            		+ "class = 'btnRemove btn btn-danger' data-projectid='" + projectID + "'>"
	            		+"</td></tr>"; 			 		
		}
		
		con.close();
		
		output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}


public String updateProjects(String ID ,String name, String researcherID, String desc, String price, String email, String phone)
	{
			String output = "";
			try
			{
					Connection con = connect();
					
					if (con == null)
					{
						return "Error while connecting to the database for updating.";
					}
					
					// create a prepared statement for update projects details
					
					String query = "UPDATE projects SET projectName=?,researcherID=?,description=?,price=?,email=?,phone=? WHERE projectID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, name); 
					 preparedStmt.setString(2, researcherID);
					 preparedStmt.setString(3, desc);
					 preparedStmt.setDouble(4, Double.parseDouble(price)); 
					 preparedStmt.setString(5, email); 
					 preparedStmt.setString(6, phone);
					 preparedStmt.setInt(7, Integer.parseInt(ID));
					// execute update statement
					preparedStmt.execute();
					
					con.close();
					
					String newProjects = readProjects();
					output = "{\"status\":\"success\", \"data\": \"" +
							newProjects + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}";
				System.err.println(e.getMessage());
			}
			return output;
	}
	
	
public String deleteProjects(String projectID)
{
	String output = "";
	try
	{
			Connection con = connect();
			if (con == null)
			{	
				return "Error while connecting to the database for deleting."; 
			}
			
			// create prepared statement for delete project details
			
			 String query = "delete from projects where projectID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			 preparedStmt.setInt(1, Integer.parseInt(projectID)); 
			// execute delete statement
			preparedStmt.execute();
			
			con.close();
			
			String newProjects = readProjects();
			output = "{\"status\":\"success\", \"data\": \"" +
					newProjects + "\"}";
	}
	catch (Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the project.\"}";
		System.err.println(e.getMessage());
	}
	
	return output;
	}

}