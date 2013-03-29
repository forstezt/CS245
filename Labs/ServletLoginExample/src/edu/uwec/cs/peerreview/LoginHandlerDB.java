package edu.uwec.cs.peerreview;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHandlerDB extends LoginHandler {
	
	//static variables
	private static final String LOGIN_PASSED_STUDENT = "student";
	private static final String LOGIN_PASSED_STAFF = "staff";
	private static final String LOGIN_WAITING = "waiting";
	
	//non-static variables
	private String username;
	private String namefield;
	private String password;
	private String loginStatus;
	private Connection cn = DBUtils.createConnection();

	
	//constructors
	
	public LoginHandlerDB() {
		clearStrings();
	}
	
	//other methods
	
	private void clearStrings() {
		username="";
		namefield="";
		password="";
		loginStatus= LOGIN_WAITING;
	}
	

	public boolean validatePassword() throws CommunicationException, NamingException {
		try {
			// first check if the person is in staff
			validateStaff(namefield, password);
			loginStatus = LOGIN_PASSED_STAFF;
			return true;
		} catch (AuthenticationException e) {
			try {
				// then check if they are in students
				validateStudent(namefield, password);
				loginStatus = LOGIN_PASSED_STUDENT;
				return true;
			} catch (AuthenticationException e1) { //student login invalid, throw exception for testLogin to handle
				throw e1;
				// if neither, throw exception
			}
		}
		
	}

	private void validateStudent(String nameFieldText, String passwordFieldText) throws NamingException {
		try {
			PreparedStatement ps = cn.prepareStatement("select firstname, lastname " +
				"from student " +
				"where username = ? and password = ?");
			
			ps.setString(1, nameFieldText);
			ps.setString(2, passwordFieldText);
			ResultSet rs = ps.executeQuery();
			boolean isEmpty = !rs.first();
			if (isEmpty) throw new AuthenticationException("Username/password not found in student table.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void validateStaff(String nameFieldText, String passwordFieldText) throws NamingException {
		try {
			PreparedStatement ps = cn.prepareStatement("select firstname, lastname " +
				"from instructor " +
				"where username = ? and password = ?");
			
			ps.setString(1, nameFieldText);
			ps.setString(2, passwordFieldText);
			ResultSet rs = ps.executeQuery();
			boolean isEmpty = !rs.first();
			if (isEmpty) throw new AuthenticationException("Username/password not found in instructor table.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUsername() {
		return username;
	}


	@Override
	public boolean isStaffLoginOK() {
		return (loginStatus == LOGIN_PASSED_STAFF);
	}


	@Override
	public boolean isStudentLoginOK() {
		return (loginStatus == LOGIN_PASSED_STUDENT);
	}


	@Override
	public void setNamefield(String namefieldText) {
		namefield = namefieldText;
		
	}


	@Override
	public void setPassword(String passwordText) {
		password = passwordText;
		
	}

	@Override
	public Connection getConnection() {
		return cn;
	}


	
}
