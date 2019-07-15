package com.revature.data;

import java.util.List;

import com.revature.model.User;

public interface UserDAO {
	/**
	 * Gets User from DB based on username and password
	 * 
	 * @param username username of the user
	 * @param password password of the user
	 * @return the user from the DB that matches username and password
	 */
	public User getEmployee(String username, String password);
	
	/**
	 * Gets User from DB based the inputed id
	 * 
	 * @param id the id of the user
	 * @return the user from the DB
	 */
	public User getEmployee(int id);
	
	/**
	 * Creates a new user in the DB
	 * 
	 * @param user the User that should be created
	 * @return true or false based on success or failure of user creation
	 */
	public boolean createUser(User user);
	/**
	 * 
	 * @return the list of employees (for the manager)
	 */
	public List<User> getEmployeeList();
	
	
	
	/**
	 * 
	 * @param newName the value the user inputs that will change the full_name on the DB
	 * @param id the user's id
	 */
	public void updateFullName(String newName, int id);
	/**
	 * 
	 * @param newName the value the user inputs that will change the username on the DB
	 * @param id the user's id
	 */
	public void updateUserName(String newName, int id);
	/**
	 * 
	 * @param newPass the value the user inputs that will change the password on the DB
	 * @param id the user's id
	 */
	public void updatePassword(String newPass, int id);
	
	
	
	
}
