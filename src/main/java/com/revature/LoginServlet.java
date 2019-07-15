package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.data.ImpReDAO;
import com.revature.data.ImpUserDAO;
import com.revature.data.UserDAO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.CloseStreams;
import com.revature.service.ConnectionUtil;

public class LoginServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Reimbursement> reimbursements = (List<Reimbursement>) req.getSession().getAttribute("reimbursements");
		ObjectMapper om = new ObjectMapper();
//		String jsonRepUser ="";
		
		
		//just practice, only prints the last object from the reimbursements list
//		Reimbursement re = new Reimbursement();
//		resp.setContentType("application/json");
//		for (Object o: reimbursements) {
//			re = (Reimbursement) o;
//			jsonRepUser = re == null ? "null" : om.writeValueAsString(re);			
//		}		
//		resp.getWriter().write(jsonRepUser);
//
//		
//		//needs to be parsed
//		String jsonRepUser = reimbursements == null ? "null" : om.writeValueAsString(reimbursements);
//		log.trace("JSON representation: " + jsonRepUser);
//		resp.setContentType("application/json");
//		resp.getWriter().write(jsonRepUser);
		
		
		//viewUser working
		User user = (User) req.getSession().getAttribute("user");
		String jsonRepUser = user == null ? "null" : om.writeValueAsString(user);
		log.trace("JSON representation: " + jsonRepUser);
		resp.setContentType("application/json");
		resp.getWriter().write(jsonRepUser);
	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		ImpUserDAO userDAO = new ImpUserDAO();
		ImpReDAO reDAO = new ImpReDAO();
		User userInfo = null;
		log.trace("username " + username + " and password " + password);

		
		
		userInfo = userDAO.getEmployee(username, password);
		
		if (!(userInfo == null)) {
			log.trace("Client login success");
			
			HttpSession session = req.getSession();
			session.setAttribute("user", userInfo);
			
			//creating a list to hold the reimbursements
			List<Reimbursement> list = new ArrayList<>();
			list = reDAO.getReForEmployeeResolved(userInfo.getId(), "Pending");
			session.setAttribute("reimbursements", list);
			
			
			if (userInfo.getRole().equals("employee")) {
				req.getRequestDispatcher("/employee.html").forward(req, resp);
			} else if (userInfo.getRole().equals("manager")) {
				req.getRequestDispatcher("/WEB-INF/manager/manager.html").forward(req, resp);
			}
		} else {
			log.trace("login FAILED");
			resp.getWriter().write("<html><h1>Failed login!</h1>"
					+ "<a href=\"/project1\">Go back</a></html>");
		}

	}

	@Override
	public void init() throws ServletException {
		log.debug("LoginServlet init method");
		super.init();
	}
}
