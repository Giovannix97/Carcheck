package it.carcheck.model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.enums.Grade;
import it.carcheck.model.bean.enums.RequestStatus;
import it.carcheck.model.interfaces.IAdmin;
import it.carcheck.utility.PasswordHasher;

public class AdminManager implements IAdmin{
	
	public static AdminManager getInstance() {
		if(instance == null)
			instance = new AdminManager();
		
		return instance;
	}
	
	private AdminManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public AdminBean doLogin(String email, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		
		AdminBean admin;
		try {
			admin = database.find(AdminBean.class, "SELECT * FROM admin WHERE email = ? AND password = ?", email, cryptedPassword).get(0);
			return admin;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public void doChangePassword(AdminBean user, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		user.setPassword(cryptedPassword);
		
		try {
			database.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public AdminBean doRetrieveByEmail(String email) throws SQLException {
		AdminBean admin = this.doFind("SELECT * FROM admin WHERE email = ?", email).get(0);
		return admin;
	}



	@Override
	public void doSave(AdminBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void doDelete(AdminBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void doInsert(AdminBean element) throws SQLException {
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public ArrayList<AdminBean> doFind(String query, Object...args) throws SQLException {
		try {
			return database.find(AdminBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



	@Override
	public void doAddAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException {
		if(loggedAdmin.getGrade() == Grade.SUPER_ADMIN) {
			AdminBean to_check = this.doFind("SELECT * FROM admin WHERE id = ?", admin.getId()).get(0);
			
			if(to_check != null && (to_check.getId() == admin.getId()))
				return; //TODO Catch exception if admin exist
			
			//Not equal
			this.doInsert(admin);
		}
	}



	@Override
	public void doRemoveAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException {
		if(loggedAdmin.getGrade() == Grade.SUPER_ADMIN)
			this.doDelete(admin);
		//TODO Catch exception if admin doesn't have permission
	}



	@Override
	public void doSetAdminPermission(AdminBean admin, int grade) throws SQLException {
		admin.setGrade(grade);
		this.doSave(admin);
	}



	@Override
	public void doApproveRequest(AdhesionRequestBean request) throws SQLException {
		request.setStatus(RequestStatus.APPROVED);
		
		AdhesionRequestManager requestManager = AdhesionRequestManager.getInstance();
		requestManager.doSave(request);
	}



	@Override
	public void doRejectRequest(AdhesionRequestBean request) throws SQLException {
		request.setStatus(RequestStatus.REFUSED);
		AdhesionRequestManager requestManager = AdhesionRequestManager.getInstance();
		requestManager.doSave(request);
	}



	@Override
	public void doSetRequestAppointment(AdhesionRequestBean request, Date date, Time time) throws SQLException {
		request.setStatus(RequestStatus.APPOINTMENT);
		request.setMeeetingHour(time);
		request.setMeetingDate(date);
		
		AdhesionRequestManager requestManager = AdhesionRequestManager.getInstance();
		requestManager.doSave(request);
	}
	
	private CarcheckDatabase database;
	private static AdminManager instance;
}
