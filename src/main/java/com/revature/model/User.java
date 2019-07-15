package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	//TODO
	//add more variables depending on what each employee/manager requires
	
	/*
-	--	Id (int on DB and java, will be the primary key)
-	--	Full name (varchar on DB, String in java)
-	--	Username (varchar on DB, String in java)
-	--	Password (varchar on DB, String in java)
-	--	Role (varchar on DB, String in java)
	 */
	
	private int id;
	private String fullName;
	private String username;
	private String password;
	private String role;
	
	public User() {
		super();
	}
	
	public User(String name) {
		super();
		this.fullName = name;
	}
	
	
	public User(int id, String name, String username, String password, String role) {
		super();
		this.id = id;
		this.fullName = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	//used to make the result set
		public User(ResultSet resultSet) throws SQLException {
			this(resultSet.getInt("id"),
					resultSet.getString("full_name"),
					resultSet.getString("username"),
					resultSet.getString("password"),
					resultSet.getString("role")
					);
		}
		
		


		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}


		@Override
		public String toString() {
			return "User [id=" + id + ", fullName=" + fullName + ", username=" + username + ", password=" + password
					+ ", role=" + role + "]";
		}

	
	
	
}
