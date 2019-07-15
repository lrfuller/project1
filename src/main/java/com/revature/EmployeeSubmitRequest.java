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

public class EmployeeSubmitRequest extends HttpServlet{
	private static Logger log = Logger.getLogger(EmployeeSubmitRequest.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Reimbursement> reimbursements = (List<Reimbursement>) req.getSession().getAttribute("newReimbursements");
		ObjectMapper om = new ObjectMapper();
		
		
		//viewUser working
		User user = (User) req.getSession().getAttribute("user");
		String jsonRepUser = user == null ? "null" : om.writeValueAsString(user);
		log.trace("JSON representation: " + jsonRepUser);
		resp.setContentType("application/json");
		resp.getWriter().write(jsonRepUser);
	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ImpUserDAO userDAO = new ImpUserDAO();
		ImpReDAO reDAO = new ImpReDAO();
		User userInfo = null;
		HttpSession session = req.getSession();
		
		String date = req.getParameter("setDate");
		String newRefNum = req.getParameter("setRefNum");
		String newAmountReq = req.getParameter("amountReq");
		
		int refNum = Integer.parseInt(newRefNum);
		float amountReq = Integer.parseInt(newAmountReq);

		
		userInfo = (User) session.getAttribute("user");
		//setting my user from previous sessions
		session.getAttribute("user");
		List<Reimbursement> list = new ArrayList<>();
		
		//add a loop that checks if any of the values are null
		if (date != null & refNum != 0 & refNum != 0 & amountReq != 0) {
			//sets the list, then sets the list for future usage, then refreshes the page essentially
			list = reDAO.newReimbursement(userInfo.getId(), date, refNum, amountReq);
			session.setAttribute("newReimbursements", list);
			req.getRequestDispatcher("/employee.html").forward(req, resp);
		}

	}

	@Override
	public void init() throws ServletException {
		log.debug("LoginServlet init method");
		super.init();
	}
}
