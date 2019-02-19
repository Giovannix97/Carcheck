package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.LocationManager;
import it.carcheck.model.bean.RegionBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.utility.JsonResponse;

import com.google.gson.*;

public class RegionAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Gson gson = new Gson();
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);

		LocationManager locationManager = LocationManager.getInstance();
		PrintWriter writer = response.getWriter();
		
		ArrayList<RegionBean> regions = locationManager.getRegionManager().getAllRegions();
		if(regions != null && regions.size() <= 0)
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "no regions")));
		else
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", regions)));
		
		return "region_service";
	}

}
