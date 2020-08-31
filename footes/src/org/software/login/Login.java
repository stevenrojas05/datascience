package org.software.login;

import org.software.entities.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.software.util.DataBase;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		HttpSession session = request.getSession();
		String emailJson = request.getParameter("username");
		String passwordJson = request.getParameter("password");

		DataBase database = new DataBase();
		Connection conection = null;
		Statement statemenet = null;
		ResultSet rs = null;
		String sql = "";
		User usuario = null;

		try {
			conection = database.getConnection("client");
			statemenet = conection.createStatement();
			sql = "select * from users where email = '" + emailJson + "'" + " and password= '" + passwordJson + "'";
			rs = statemenet.executeQuery(sql);
			if (rs.next()) {

				int id = rs.getInt("id");
				int user_type = rs.getInt("user_type");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Date created_at = rs.getDate("created_at");
				Date updated_at = rs.getDate("updated_at");

				usuario = new User(id, user_type, username, email, password, created_at, updated_at);

				session.setAttribute("usuario", usuario);

				response.sendRedirect("index.jsp");

				System.out.println("Usuario creado : " + usuario);
			} else {

				request.setAttribute("errorMsg", "CREDENCIALES INCORRECTAS");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);

			}

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs);
			database.closeObject(statemenet);
			database.closeObject(conection);
		}

	}

}
