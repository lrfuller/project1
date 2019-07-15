package com.revature;

import java.io.IOException;

import com.revature.data.ImpReDAO;
import com.revature.data.ImpUserDAO;
import com.revature.model.User;

public class testMethods {
	public static void main(String[] args) {
		ImpUserDAO userDAO = new ImpUserDAO();
		ImpReDAO reDAO = new ImpReDAO();
		
		//testing login functionality
//		System.out.println(userDAO.getUser("emp", "pass"));
//		System.out.println(userDAO.getUser("man1", "pass"));
		
		//testing employee's viewing pending/completed requests
//		System.out.println(reDAO.getReForEmployeeResolved(1, "Pending"));
//		System.out.println(reDAO.getReForEmployeePending(1, "Pending"));
		
		//testing manager's method for viewing all pending/resolve requests of employees
//		System.out.println(reDAO.getReForManager("Resolved"));
//		System.out.println(reDAO.getReForManager("Pending"));
		
		//displays all the current employees and their info
//		System.out.println(userDAO.getEmployeeNameList());
		
		//updating the user's values
//		userDAO.updateFullName("Bob Joe", 1);
//		userDAO.updateUserName("emp1", 1);
//		userDAO.updatePassword("pass", 1);
//		System.out.println(userDAO.getEmployee("emp1", "pass"));
		
		//updating the reimbursment's status
//		reDAO.updateRequestResponse("Denied", 1);
//		System.out.println(reDAO.getReForEmployee(1, "Denied"));
		
		
		
	}
}
