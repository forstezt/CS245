package edu.uwec.cs.peerreview.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.CommunicationException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uwec.cs.peerreview.*;

/**
 * Servlet implementation class Login
 */
public class LoginDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>" +
				"<head>" +
				"<title>log in first!</title>" +
				"</head>" +
				"<body>" +
				"You cannot access this servlet with GET. Go to the" +
				"<a href=\"LoginDemo\">login page</a> first." +
				"</body>" +
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		out.println("<html>" +
				"<head>" +
				"<title>Results</title>" +
				"</head>" +
				"<body>" +
				"<h1>Login results:</h1>" +
				"<p>Username: " + username + "</p>" +
				"<p>Password: " + password + "</p>");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			LoginHandler log = new LoginHandlerDB();
			out.println("<p>Using handler: " + log.getClass().getSimpleName() + "</p>");
			Connection cn = log.getConnection();
			log.setNamefield(username);
			log.setPassword(password);
			if (log.validatePassword()) {
				PreparedStatement ps = cn.prepareStatement(QueryLibrary.INSTRUCTOR_FROM_USERNAME);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) { //it was an instructor login name
					out.println("<p>Instructor ID: " + rs.getInt("instructor_id") + "</p>");
				} else { //it might be student login name
					ps = cn.prepareStatement(QueryLibrary.STUDENT_FROM_USERNAME);
					ps.setString(1, username);
					rs = ps.executeQuery();
					if (rs.next()) { //it was a valid student user name
						out.println("<p><strong>Success!</strong> " +
								"Student ID: " + rs.getInt("student_id") + "</p>");
					} else { //not a valid student or instructor name
						out.println("<div style=\"color: red\">" +
								"Username does not match an instructor or student in the Peer Review system." +
								"</div>");
					}
				}
			}
		} catch (CommunicationException e) {
			out.println("<div style=\"color: red\">" +
					"Unable to connect to the validation server. \nNote: If you are off-campus, you will need \nto set up a VPN in order to use this system." +
					"</div>");
		} catch (NamingException e) {
			out.println("<div style=\"color: red\">" +
					"Username/Password invalid" +
					"</div>");
		} catch (SQLException e) {
			out.println("<div style=\"color: red; font-family: monospace\">");
			e.printStackTrace(out);
			out.println("</div>");
		} catch (ClassNotFoundException e) {
			out.println("<div style=\"color: red; font-family: monospace\">");
			e.printStackTrace(out);
			out.println("</div>");
		}
		
		out.println("<p><small><i>This page does not use any session variables.</i></small></p>" +
				"</body>" +
				"</html>");
	}

}
