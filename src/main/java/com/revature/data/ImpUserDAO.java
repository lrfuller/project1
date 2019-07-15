package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.CloseStreams;
import com.revature.service.ConnectionUtil;

public class ImpUserDAO implements UserDAO {

	public Logger log = Logger.getLogger(ImpUserDAO.class);

	@Override
	public User getEmployee(String username, String password) {
		ImpUserDAO userDao = new ImpUserDAO();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM ERS WHERE username = ? AND password = ?");
			statement.setString(1, username);
			statement.setString(2, password);
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			user = new User(resultSet);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return user;
	}

	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getEmployeeList() {

		boolean validB = true; // boolean used for validated the user. Defaults as true unless an exception is
		// caught
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<User> list = new ArrayList<>();
		String role = "employee";

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM ers WHERE role = ?");
			statement.setString(1, role);
			statement.execute();
			resultSet = statement.getResultSet();

			while (resultSet.next()) {
				list.add(new User(resultSet));
			}

			conn.close();
		} catch (SQLException e) {
			validB = false;
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return list;
	}

	
	@Override
	public void updateFullName(String newName, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("update ers set full_name = ? where id = ?");
			statement.setString(1, newName);
			statement.setInt(2, id);
			statement.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
	}
	
	@Override
	public void updateUserName(String newName, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("update ers set username = ? where id = ?");
			statement.setString(1, newName);
			statement.setInt(2, id);
			statement.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
	}

	@Override
	public void updatePassword(String newPass, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("update ers set password = ? where id = ?");
			statement.setString(1, newPass);
			statement.setInt(2, id);
			statement.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}
		
	}

	@Override
	public User getEmployee(int id) {
		ImpUserDAO userDao = new ImpUserDAO();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM ERS WHERE id = ?");
			statement.setInt(1, id);
			statement.execute();
			resultSet = statement.getResultSet();
			resultSet.next();
			user = new User(resultSet);

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return user;
	}



	

	
	
	
	
	
}
