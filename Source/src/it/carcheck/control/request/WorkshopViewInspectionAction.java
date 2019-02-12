package it.carcheck.control.request;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;;

public class WorkshopViewInspectionAction implements IAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		int inspectionKey = Integer.parseInt(request.getParameter("inspectionKey"));
		VehicleInspectionManager inspection = VehicleInspectionManager.getInstance();
		WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
		
		request.setAttribute("property", "readonly");
		request.setAttribute("class", "classname=active");
		request.setAttribute("title", "Visualizza Revisione");
		request.setAttribute("uploadIsVisible", "");
		
		
		
		
		try {
			inspection.doRetrieveByKey(workshop, inspectionKey);
		} catch (SQLException e) {
			throw new ActionException();
		}
		return null;

	}
}
