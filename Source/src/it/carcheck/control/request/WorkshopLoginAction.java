package it.carcheck.control.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class WorkshopLoginAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Gson gson = new Gson();
		
		try {
			WorkshopBean user = WorkshopManager.getInstance().doLogin(email, password);
			
			if(user != null) {
				request.getSession().setAttribute("user", user);
				response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
				return "workshop/dashboard";
			}
			
			else {
				PrintWriter writer = response.getWriter();
				response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, ERROR_MESSAGE)));
		
				return "login";
			}
				
			
		} catch (SQLException | IOException e) {
			throw new ActionException();
		}
	}

	
	private static final String ERROR_MESSAGE = "Username o password errati";
}
