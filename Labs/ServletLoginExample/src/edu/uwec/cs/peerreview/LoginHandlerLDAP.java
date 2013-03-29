package edu.uwec.cs.peerreview;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.sql.Connection;

public class LoginHandlerLDAP extends LoginHandler {
	
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
	
	public LoginHandlerLDAP() {
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
		validatePerson("students", nameFieldText, passwordFieldText);
		
	}

	private void validateStaff(String nameFieldText, String passwordFieldText) throws NamingException {

		validatePerson("staff", nameFieldText, passwordFieldText);

	}
	
	private void validatePerson(String personType, String nameFieldText, String passwordFieldText) throws NamingException {
		String username;
		Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		
		env.put(Context.PROVIDER_URL, "ldap://ldap.uwec.edu");
		
		username = nameFieldText;
		
		env.put(Context.SECURITY_PRINCIPAL, "CN=" + username + ",OU="+personType+",OU=uwec users,DC=uwec,DC=edu");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_CREDENTIALS, String.valueOf(passwordFieldText));
		DirContext dir = new InitialDirContext(env);

		dir.getAttributes("");
		dir.close();
	

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
