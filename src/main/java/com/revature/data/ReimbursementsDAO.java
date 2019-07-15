package com.revature.data;

import java.util.List;

import com.revature.model.Reimbursement;

public interface ReimbursementsDAO {
	
	/**
	 * used to list all the 'approved/denied' reimbursements
	 * 
	 * @param id the user's id value will refer to this
	 * @param status will be 'Pending', but in SQL it will use a NOT, therefore
	 * retrieving the Approved & Denied reimbursements
	 * @return the inputed user's resolved reimbursement
	 */
	public List<Reimbursement> getReForEmployeeResolved(int id, String status);
	
	/**
	 * used to list all the 'Pending' reimbursements
	 * 
	 * @param id the user's id value will refer to this
	 * @param status will be 'Pending'
	 * @return the inputed user's pending reimbursements
	 */
	public List<Reimbursement> getReForEmployeePending(int id, String status);
	
	/**
	 * used for managers to view all reimbursements for pending requests
	 * 
	 * @param 'Pending'
	 * @return pending reimbursements
	 */
	public List<Reimbursement> getReForManagerPending(String status);
	
	/**
	 * used for managers to view all reimbursements for Resolved requests
	 * 
	 * @param 'Pending', however in the SQL statement, the querey uses goes for the opposite of 'Pending'
	 * @return resolved reimbursements
	 */
	public List<Reimbursement> getReForManagerResolved(String status);
	
	/**
	 * used for managers to view a single employee's reimbursements
	 * 
	 * @param id the user's id value will refer to this
	 * @return resolved reimbursements
	 */
	public List<Reimbursement> getReForManagerOneEmp(int id);
	
	
	/**
	 * Creates a new reimbursement request
	 * @param newId 
	 * @param newDate
	 * @param newRef
	 * @param newAmount
	 * @return new reimbursement request
	 */
	public List<Reimbursement> newReimbursement(int newId, String newDate, int newRef, float newAmount);
	
	/**
	 * used to change the response to 'Approved' or 'Denied'
	 * @param newResponse contains approved or denied
	 * @param refNum contains the reimbursement 'reference_number'
	 */
	public void updateRequestResponse(String newStatus, int refNum);
	
}
