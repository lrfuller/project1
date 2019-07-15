package com.revature.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Reimbursement {

	
//	-- Id (int java, number SQL)   (foreign key)
//	-- date_created (string java, date SQL)
//	-- reference_number (int java, number SQL)  (also primary key)
//	-- amount_requested (float in both)
//	-- status (string java, varchar SQL)
	
	private int Id;
	private String date;
	private int ref;
	private float amount;
	private String status;
	
	public Reimbursement() {
		super();
	}
	public Reimbursement(int id, String date, int ref, float amount, String status) {
		super();
		Id = id;
		this.date = date;
		this.ref = ref;
		this.amount = amount;
		this.status = status;
	}
	public Reimbursement(ResultSet resultSet) throws SQLException {
		this(resultSet.getInt("id"),
				resultSet.getString("date_created"),
				resultSet.getInt("reference_number"),
				resultSet.getFloat("amount_requested"),
				resultSet.getString("status")
				);
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reimbursement [Id=" + Id + ", date=" + date + ", ref=" + ref + ", amount=" + amount + ", status="
				+ status + "]";
	}
	
	
}
