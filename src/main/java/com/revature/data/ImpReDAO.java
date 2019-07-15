package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.service.CloseStreams;
import com.revature.service.ConnectionUtil;

public class ImpReDAO implements ReimbursementsDAO {

	public Logger log = Logger.getLogger(ImpReDAO.class);

	@Override
	public List<Reimbursement> getReForEmployeeResolved(int id, String status) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Reimbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM reimbursments WHERE id= ? AND NOT status = ?");
			statement.setInt(1, id);
			statement.setString(2, status);
			statement.execute();
			resultSet = statement.getResultSet();

			while (resultSet.next()) {
				list.add(new Reimbursement(resultSet));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return list;
	}
	@Override
	public List<Reimbursement> getReForEmployeePending(int id, String status) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Reimbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM reimbursments WHERE id= ? AND status = ?");
			statement.setInt(1, id);
			statement.setString(2, status);
			statement.execute();
			resultSet = statement.getResultSet();

			while (resultSet.next()) {
				list.add(new Reimbursement(resultSet));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return list;
	}

	@Override
	public List<Reimbursement> getReForManagerPending(String status) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Reimbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM reimbursments WHERE status = ?");
			statement.setString(1, status);
			statement.execute();
			resultSet = statement.getResultSet();

			while (resultSet.next()) {
				list.add(new Reimbursement(resultSet));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return list;
	}
	
	@Override
	public List<Reimbursement> getReForManagerResolved(String status) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Reimbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM reimbursments WHERE NOT status = ?");
			statement.setString(1, status);
			statement.execute();
			resultSet = statement.getResultSet();

			while (resultSet.next()) {
				list.add(new Reimbursement(resultSet));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return list;
	}
	
	@Override
	public void updateRequestResponse(String newStatus, int refNum) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("update reimbursments set status = ? where reference_number = ?");
			statement.setString(1, newStatus);
			statement.setInt(2, refNum);
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
	public List<Reimbursement> getReForManagerOneEmp(int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Reimbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("SELECT * FROM reimbursments WHERE id= ?");
			statement.setInt(1, id);
			statement.execute();
			resultSet = statement.getResultSet();

			while (resultSet.next()) {
				list.add(new Reimbursement(resultSet));
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return list;
	}
	@Override
	public List<Reimbursement> newReimbursement(int newId, String newDate, int newRef, float newAmount) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Reimbursement> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			statement = conn.prepareStatement("insert into reimbursments values(?, TO_DATE(? ,?), ?, ?, ?)");
			statement.setInt(1, newId);
			statement.setString(2, newDate);
			statement.setString(3, "YYYY-MM-DD");
			statement.setInt(4, newRef);
			statement.setFloat(5, newAmount);
			statement.setString(6, "Pending");
			statement.execute();

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
//			log.error(e);
		} finally {
			CloseStreams.close(statement);
			CloseStreams.close(resultSet);
		}

		return list;
	}

}
