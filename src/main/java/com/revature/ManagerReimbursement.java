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

public class ManagerReimbursement extends HttpServlet{
	private static Logger log = Logger.getLogger(EmployeeReimbursement.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Reimbursement> reimbursements = (List<Reimbursement>) req.getSession().getAttribute("reimbursements");
		ObjectMapper om = new ObjectMapper();
		Reimbursement re = new Reimbursement();
		String jsonRepUser = "";

		// TODO
		// **************************************************
		// get my visuals down. Right now I can post stuff, but I need to GET it with a
		// button ideally

		// just practice, only prints the last object from the reimbursements list
//		resp.setContentType("application/json");
//		for (Object o: reimbursements) {
//			re = (Reimbursement) o;
//			jsonRepUser = re == null ? "null" : om.writeValueAsString(re);			
//		}		
//		resp.getWriter().write(jsonRepUser);

		// needs to be parsed version
		jsonRepUser = reimbursements == null ? "null" : om.writeValueAsString(reimbursements);
		log.trace("JSON representation: " + jsonRepUser);
		resp.setContentType("application/json");
		resp.getWriter().write(jsonRepUser);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("mantype");
		System.out.println(type);

		HttpSession session = req.getSession();
		ImpUserDAO userDAO = new ImpUserDAO();
		ImpReDAO reDAO = new ImpReDAO();
		User userInfo = null;

		userInfo = (User) session.getAttribute("user");
//		System.out.println(userInfo);

		session.getAttribute("user");
		List<Reimbursement> list = new ArrayList<>();

		if (type.equals("Pending")) {
			list = reDAO.getReForManagerPending("Pending");
			session.setAttribute("reimbursements", list);
			req.getRequestDispatcher("/WEB-INF/manager/manager.html").forward(req, resp);
		}
		if (type.equals("Resolved")) {
			list = reDAO.getReForManagerResolved("Pending");
			session.setAttribute("reimbursements", list);
			req.getRequestDispatcher("/WEB-INF/manager/manager.html").forward(req, resp);
		}	
	}

	@Override
	public void init() throws ServletException {
		log.debug("LoginServlet init method");
		super.init();
	}
}
