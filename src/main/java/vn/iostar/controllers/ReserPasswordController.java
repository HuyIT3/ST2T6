package vn.iostar.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.service.impl.UserService;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = {"/resetpass"})
public class ReserPasswordController extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(ReserPasswordController.class.getName());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession(false);
		if(session !=null && session.getAttribute("username") != null ) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		Cookie[] cookies = req.getCookies();
		if(cookies != null)
		{
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/login");
					return;
				}
			}
		}
		req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String newPassword = req.getParameter("psw");
		String confirmPassword = req.getParameter("repeat-psw");
		UserService service = new UserService();
		String alertMsg ="";
		LOGGER.log(Level.INFO, "Received password reset for email: {0}", email);
		if (!newPassword.equals(confirmPassword)) {
			alertMsg =
			"Password do not match";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
			return;
		}
		if (!service.checkExistEmail(email)) {
			alertMsg = "Email does not exist!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
			return;
		}
		boolean isSuccess = service.resetPass(email, newPassword);
		if (isSuccess) {
				LOGGER.log(Level.INFO, "Password reset successful for email: {0}", email);
				resp.sendRedirect(req.getContextPath() + "/login");
		} else {
				alertMsg = "System error!";
				LOGGER.log(Level.SEVERE, "Password reset failed for email: {0}", email);
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher(Constant.Path.RESETPW).forward(req, resp);
				}
	}
}
