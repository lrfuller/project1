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

public class ManagerApproveDenyRequest extends HttpServlet{
	private static Logger log = Logger.getLogger(ManagerApproveDenyRequest.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ImpReDAO reDAO = new ImpReDAO();
		
		String newId = req.getParameter("refNumberApproveDeny");
		String status = req.getParameter("updateStatus");
		int id = Integer.parseInt(newId);
		
		//add a loop that checks if any of the values are null
		if (status.equals("Approved") || status.equals("Denied")) {
			reDAO.updateRequestResponse(status, id);
			req.getRequestDispatcher("/WEB-INF/manager/manager.html").forward(req, resp);
		}
			

	}

	@Override
	public void init() throws ServletException {
		log.debug("LoginServlet init method");
		super.init();
	}
}
