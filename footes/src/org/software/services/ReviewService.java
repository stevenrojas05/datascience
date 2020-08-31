package org.software.services;

import org.software.entities.Review;
import org.software.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.software.util.DataBase;

@Path("/review")
public class ReviewService {
	
	@POST
	@Path("/add")
	@Produces("application/json")
	public Response addReview(@Context HttpServletRequest request, Review review) {
		
		HttpSession session = request.getSession();
		
		User usuario = (User)session.getAttribute("usuario");
		
		int user_id = usuario.getId();
		
		DataBase database = new DataBase();
		Connection connection = null;
		PreparedStatement statement = null;
		String sql = "";
		String mensaje = "";
		int insertados = 0;
		
		try {
			connection = database.getConnection("client");
						
			sql = "INSERT INTO reviews (item_id, user_id, rating, comment)";
			sql += " VALUES (?, ?, ?, ?)";
			
			statement = connection.prepareStatement(sql); 
			statement.setLong(1, review.getItem_id()); 
			statement.setLong(2, user_id);
			statement.setDouble(3, review.getRating());
			statement.setString(4, review.getComment());
			
			insertados = statement.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			database.closeObject(statement);
			database.closeObject(connection);
		}
		
		if(insertados > 0){ 
			mensaje = "{\"message\":\"La calificación del producto se ha realizado exitosamente\"}"; 
			System.out.println(Response.status(200).entity(mensaje).build());
			return Response.status(200).entity(mensaje).build(); 
		} 
		else{ 
			mensaje = "{\"message\":\"No se ha podido realizar la calificaci{on del producto\"}";
			System.out.println(Response.status(400).entity(mensaje).build());
			return Response.status(400).entity(mensaje).build(); 
		}
		
	}

	
	
}
