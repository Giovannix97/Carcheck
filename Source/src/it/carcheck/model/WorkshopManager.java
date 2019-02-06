package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.RequestStatus;
import it.carcheck.model.interfaces.IWorkshop;
import it.carcheck.utility.PasswordHasher;

public class WorkshopManager implements IWorkshop {

	
	public WorkshopManager() {
		this.database = CarcheckDatabase.getInstance();
	}

	@Override
	public WorkshopBean doLogin(String email, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		WorkshopBean workshop = doFind("SELECT * FROM workshop WHERE email = " + email + " AND password = " + cryptedPassword).get(0);
		return workshop;
	}

	@Override
	public void doChangePassword(WorkshopBean user, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		user.setPassword(cryptedPassword);
		
		try {
			database.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void doSave(WorkshopBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void doDelete(WorkshopBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(WorkshopBean element) throws SQLException {
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<WorkshopBean> doFind(String query) throws SQLException {
		try {
			return database.find(query, WorkshopBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void doSignUp(WorkshopBean workshop) throws SQLException {	
		doInsert(workshop);
		doSendAdhesionRequest(workshop);
		
	}

	@Override
	public void doSendAdhesionRequest(WorkshopBean workshop) throws SQLException {
		AdhesionRequestBean adhesionRequestBean = new AdhesionRequestBean();
		adhesionRequestBean.setWorkshopCode(workshop.getId());
		adhesionRequestBean.setStatus(RequestStatus.PROCESSING);
		
		AdhesionRequestManager adhesionRequestManager = new AdhesionRequestManager();
		adhesionRequestManager.doInsert(adhesionRequestBean);	
		/*
		 * TODO must implement email sender
		 */
	}
	
	@Override
	public void doAddVehicleInspection(WorkshopBean workshop,VehicleInspectionBean vehicleInspection,VehicleBean vehicle) throws SQLException{
		vehicleInspection.setWorkShop(workshop.getId());
		vehicleInspection.setVehicle(vehicle.getLicensePlate());
		
		VehicleInspectionManager vehicleInspectionManager = new VehicleInspectionManager();
		vehicleInspectionManager.doInsert(vehicleInspection);	
	}


	
	private CarcheckDatabase database;
}
