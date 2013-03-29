package edu.uwec.cs.peerreview;

import javax.naming.CommunicationException;
import javax.naming.NamingException;

public abstract class LoginHandler {
	
	public abstract boolean validatePassword() throws CommunicationException, NamingException;
	
	public abstract boolean isStaffLoginOK(); 
	
	public abstract boolean isStudentLoginOK();
	
	public abstract String getUsername();
	
	public abstract void setPassword(String passwordText);
	
	public abstract void setNamefield(String namefieldText);
	
	public abstract java.sql.Connection getConnection();
	
}
