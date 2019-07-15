package com.revature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.data.ImpReDAO;
import com.revature.data.ImpUserDAO;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class UpdateEmpInfo extends HttpServlet {
	//finish setting up html form and implement the update Emp Info method
	
	
	private static Logger log = Logger.getLogger(EmployeeReimbursement.class);

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		List<Reimbursement> reimbursements = (List<Reimbursement>) req.getSession().getAttribute("update");
//		ObjectMapper om = new ObjectMapper();
//		Reimbursement re = new Reimbursement();
//		String jsonRepUser = "";
//
//
//		// needs to be parsed version
//		jsonRepUser = reimbursements == null ? "null" : om.writeValueAsString(reimbursements);
//		log.trace("JSON representation: " + jsonRepUser);
//		resp.setContentType("application/json");
//		resp.getWriter().write(jsonRepUser);
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newFullName = req.getParameter("updateFullName");
		String newPass = req.getParameter("updatePass");
		String newUserName = req.getParameter("updateUserName");
//		int typeInt = Integer.parseInt(type);
		

		HttpSession session = req.getSession();
		ImpUserDAO userDAO = new ImpUserDAO();
		ImpReDAO reDAO = new ImpReDAO();
		User userInfo = null;

		userInfo = (User) session.getAttribute("user");
//		System.out.println(userInfo);
		
		session.getAttribute("user");
		List<Reimbursement> list = new ArrayList<>();

//		userDAO.updateFullName(newFullName, userInfo.getId());
//		userDAO.getEmployee(userInfo.getUsername(), userInfo.getPassword());

		
//		session.setAttribute("user", );
//		list = reDAO.getReForManagerOneEmp(typeInt);
		
		
		
		userDAO.updateFullName(newFullName, userInfo.getId());
		userDAO.updatePassword(newPass, userInfo.getId());
		userDAO.updateUserName(newUserName, userInfo.getId());
		
		
		
//		list = reDAO.getReForManagerOneEmp(1);
		
		
		session.setAttribute("update", list);
		userInfo = userDAO.getEmployee(userInfo.getId());
		System.out.println(userInfo);
		
		if (!(userInfo == null)) {
			log.trace("Client login success");
			
//			HttpSession session = req.getSession();
			session.setAttribute("user", userInfo);
			
			//creating a list to hold the reimbursements
//			List<Reimbursement> list = new ArrayList<>();
			list = reDAO.getReForEmployeeResolved(userInfo.getId(), "Pending");
			session.setAttribute("reimbursements", list);
			

			
			if (userInfo.getRole().equals("employee")) {
				req.getRequestDispatcher("/employee.html").forward(req, resp);
			} else if (userInfo.getRole().equals("manager")) {
				req.getRequestDispatcher("/WEB-INF/manager/manager.html").forward(req, resp);
			}
		} else {
			log.trace("login FAILED");
			resp.getWriter().write("<html><h1>Invalid...</h1>"
					+ "<a href=\"/project1\">Go back</a></html>");
		}
		
//		System.out.println(list);
		
		
		
//		req.getRequestDispatcher("/WEB-INF/manager/manager.html").forward(req, resp);
		
		
//		if (type.equals("Pending")) {
//			
//			session.setAttribute("reimbursements", list);
//			req.getRequestDispatcher("/employee.html").forward(req, resp);
//		}
//		if (type.equals("Resolved")) {
//			list = reDAO.getReForEmployeeResolved(userInfo.getId(), "Pending");
//			session.setAttribute("reimbursements", list);
//			req.getRequestDispatcher("/employee.html").forward(req, resp);
//		}	
	}

	@Override
	public void init() throws ServletException {
		log.debug("LoginServlet init method");
		super.init();
	}
}
