package it.carcheck.control.request;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.bean.AdminBean;


public class LogoutAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
		
		response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
		
		if (request.getSession().getAttribute("user") != null) {
			
			if((request.getSession().getAttribute("user")) instanceof AdminBean) {
				request.getSession().removeAttribute("user");
				return "admin/login";
			}
			else {
				return "workshop/login";
			}
		}
		
		return "index";
	}
}
