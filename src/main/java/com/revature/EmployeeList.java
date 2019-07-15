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

public class EmployeeList extends HttpServlet{
	private static Logger log = Logger.getLogger(ManagerSingleEmp.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Reimbursement> reimbursements = (List<Reimbursement>) req.getSession().getAttribute("emplist");
		ObjectMapper om = new ObjectMapper();
		Reimbursement re = new Reimbursement();
		String jsonRepUser = "";


		// needs to be parsed version
		jsonRepUser = reimbursements == null ? "null" : om.writeValueAsString(reimbursements);
		log.trace("JSON representation: " + jsonRepUser);
		resp.setContentType("application/json");
		resp.getWriter().write(jsonRepUser);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ImpUserDAO userDAO = new ImpUserDAO();
		ImpReDAO reDAO = new ImpReDAO();
		User userInfo = null;

		
		userInfo = (User) session.getAttribute("user");

		session.getAttribute("user");
		List<User> list = new ArrayList<>();

		list = userDAO.getEmployeeList();
		session.setAttribute("emplist", list);
		req.getRequestDispatcher("/WEB-INF/manager/manager.html").forward(req, resp);
	}

	@Override
	public void init() throws ServletException {
		log.debug("LoginServlet init method");
		super.init();
	}
}
