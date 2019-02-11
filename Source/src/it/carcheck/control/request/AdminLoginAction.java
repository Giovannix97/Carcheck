package it.carcheck.control.request;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdminManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.utility.PasswordHasher;;

public class AdminLoginAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		password=PasswordHasher.Encrypt(password);
		try {
			AdminBean user = AdminManager.getInstance().doLogin(email, password);
			if(user != null && user.isFirstLogin()) {
				request.getSession().setAttribute("user", user);
				response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
				return "admin/changePassword";
			}
			else if(user!=null && !user.isFirstLogin())
			{
				request.getSession().setAttribute("user", user);
				response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
				return "admin/dashboard";
			}
			else if(user==null) {
				response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
				return "admin/login";
			}
		} 
		catch (SQLException e) {
			return "admin/login";
		}
		
		return "admin/login";
	}
}
