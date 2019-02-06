package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the Admin table of database
 */
public class AdminBean {

	@Table(name = "admin")
	public AdminBean() {}

	/*
	 * Getters and setters methods
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}


	/*
	 * Instance Variables
	 */
	@PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
	int id;
	private String name, surname;
	private String email, password;
	private boolean isFirstLogin;
	private int grade;

}
