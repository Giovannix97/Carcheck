package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AddressBean;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.interfaces.IAddress;

public class AddressManager implements IAddress {

	public static AddressManager getInstance() {
		if(instance == null)
			return new AddressManager();

		return instance;
	}
	
	private AddressManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public void doSave(AddressBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(AddressBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(AddressBean element) throws SQLException {
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<AddressBean> doFind(String query) throws SQLException {
		try {
			return database.find(query, AddressBean.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieve from database the addresses that are like the specified name
	 * @param name the address description
	 * @param istat the ISTAT code of the city to look for
	 * @return a collection of AddressBean
	 */
	@Override
	public ArrayList<AddressBean> getFullAddressByName(String name, String istat) {
		try {
			return this.database.find("SELECT * FROM address WHERE name LIKE \"" + name + "%\" AND istat = \"" + istat + "\" LIMIT 10", AddressBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Retrieve from database the addresses that are like the specified name
	 * @param name the address description
	 * @param city the CityBean of the city to look for
	 * @return a collection of AddressBean
	 */
	@Override
	public ArrayList<AddressBean> getFullAddressByName(String name, CityBean city) {
		try {
			return this.database.find("SELECT * FROM address WHERE name = \"" + name + "\" AND istat = \"" + city.getIstat() + "\"", AddressBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Retrieve from database the addresses that are like the specified name without distinguishing the street number
	 * @param name the address description
	 * @param istat the ISTAT code of the city to look for
	 * @return a collection of AddressBean
	 */
	@Override
	public ArrayList<AddressBean> getAddressByName(String name, String istat) {
		try {
			return database.find("SELECT * FROM address WHERE name LIKE \"" + name + "%\" AND istat = \"" + istat + "\" GROUP BY name LIMIT 10", AddressBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Retrieve from database the addresses that are like the specified name without distinguishing the street number
	 * @param name the address description
	 * @param city the CityBean of the city to look for
	 * @return a collection of AddressBean
	 */
	@Override
	public ArrayList<AddressBean> getAddressByName(String name, CityBean city) {
		try {
			return database.find("SELECT * FROM address WHERE name LIKE \"" + name + "%\" AND istat = \"" + city.getIstat() + "\" GROUP BY name LIMIT 10", AddressBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private CarcheckDatabase database;
	private static AddressManager instance;
	
}
