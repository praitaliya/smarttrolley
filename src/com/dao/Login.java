package com.dao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 * @author Prashant Italiya
 * 
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		DAOController dao = new DAOController();
		String str = request.getParameter("submit");
		if (str == null) { // check for void request
			response.sendError(403);
		} else if (str.equalsIgnoreCase("Login")) { 
			if (!request.getParameter("username").equals("") || !request.getParameter("password").equals("")) { // if username and password not null do authenticate
				String auth = dao.authenticate(request.getParameter("username"), request.getParameter("password"));
				System.out.println(auth);
				if (auth.equals("-1")) { // if auth = -1, means user account is blocked 
					session.setAttribute("accntBlock", 1);
					session.setAttribute("wrongUPW", 0);
					response.sendRedirect(request.getContextPath() + "/JSP/index.jsp");
					return;
				} else if (auth.equals("0")) { // if auth = 0, means user entered wrong password
					session.setAttribute("wrongUPW", 1);
					session.setAttribute("accntBlock", 0);
					response.sendRedirect(request.getContextPath() + "/JSP/index.jsp");
					return;
				} else { // authenticated user
					String data[] = auth.split(","); // parse data from authentication
					int uid = Integer.parseInt(data[0]); // user id
					int isPassReset = Integer.parseInt(data[1]); // if password is expired
					String name = data[2]; // user full name
					if (!(uid <= 0) && isPassReset == 0) { // if uid is not zero and password reset not required, then set session and rights
						session = dao.setRights(session, uid);
						if (session != null) {
							System.out.println(session.getAttribute("Name"));
							int loginLog = dao.addLoginTimeStamp(session.getAttribute("uid").toString(),
									request.getParameter("username")); // insert login logs
							if (loginLog != 0) {
								response.sendRedirect(request.getContextPath() + "/Home"); // redirect to home
							} else { // something went wrong while saving logs
								response.sendError(500); 
							}
						}
					} else if (isPassReset == 1) { // if password reset is required redirect to password reset page
						session.setAttribute("passReset", 1);
						session.setAttribute("uid", uid);
						session.setAttribute("fullName", name);
						response.sendRedirect(request.getContextPath() + "/cmVzZXRQYXNz==");
						return;
					} else { // redirect to login page with password wrong error
						session.setAttribute("wrongUPW", 1);
						session.setAttribute("accntBlock", 0);
						response.sendRedirect(request.getContextPath() + "/JSP/index.jsp");
						return;
					}
				}
			} else { // redirect to login page with password wrong error
				session.setAttribute("wrongUPW", 1);
				session.setAttribute("accntBlock", 0);
				response.sendRedirect(request.getContextPath() + "/JSP/index.jsp");
				return;
			}
		}
	}
}
