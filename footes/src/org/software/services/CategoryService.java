package org.software.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.software.entities.Category;
import org.software.lists.CategoryList;
import org.software.util.DataBase;

@Path("/category")
public class CategoryService {
	
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response add(Category category) {
		
		DataBase database = new DataBase();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "";
		String mensaje = "";		
		int inserteds = 0;
		
		try {
			
			connection = database.getConnection("admin");
			sql = "INSERT INTO categories(name, icon)";
			sql += " VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category.getName());
			preparedStatement.setString(2, category.getIcon());
			inserteds = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.toString());
			
		} finally {
			
			database.closeObject(preparedStatement);
			database.closeObject(connection);
			
		}
		
		if (inserteds > 0) {
			
			mensaje = "{\"mensaje\":\"Adicionar OK\"}";
			return Response.status(200).entity(mensaje).build();
			
		} else {
			
			mensaje = "{\"mensaje\":\"Error al adicionar\"}";
			return Response.status(400).entity(mensaje).build();
			
		}
		
	}

	@GET
	@Path("/")
	@Produces("application/json")
	// @Produces("application/xml")
	public CategoryList getAll() {
		
		ArrayList<Category> categoryList = new ArrayList<Category>();
		DataBase database = new DataBase();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;		
		String sql = "";
		
		try {
			
			connection = database.getConnection("client");
			statement = connection.createStatement();
			sql = "select * from categories";
			rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				int published = rs.getInt("published");
				String name = rs.getString("name");
				String icon = rs.getString("icon");
				Category category = new Category();
				category.setId(id);
				category.setPublished(published);
				category.setName(name);
				category.setIcon(icon);
				categoryList.add(category);
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.toString());
			
		} finally {
			
			database.closeObject(rs);
			database.closeObject(statement);
			database.closeObject(connection);
			
		}
		
		return new CategoryList(categoryList);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response update(Category category, @PathParam(value = "id") int id) {
		
		DataBase database = new DataBase();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "";
		String mensaje = "";
		int updates = 0;
		
		try {
			
			connection = database.getConnection("admin");
			sql = "UPDATE categories SET published=?, name=?, icon=?";
			sql += " WHERE id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, category.getPublished());
			preparedStatement.setString(2, category.getName());
			preparedStatement.setString(3, category.getIcon());
			preparedStatement.setInt(4, id);
			updates = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.toString());
			
		} finally {
			
			database.closeObject(preparedStatement);
			database.closeObject(connection);
			
		}
		
		if (updates > 0) {
			
			mensaje = "{\"mensaje\":\"Modificar OK\"}";
			return Response.status(200).entity(mensaje).build();
			
		} else {
			
			mensaje = "{\"mensaje\":\"Error al modificar\"}";
			return Response.status(400).entity(mensaje).build();
			
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes({ "application/json" })
	@Produces("application/json")
	public Response adicionar(@PathParam(value = "id") int id) {
		
		DataBase database = new DataBase();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "";
		String mensaje = "";
		int deleteds = 0;
		
		try {
			
			connection = database.getConnection("admin");
			sql = "DELETE FROM categories WHERE id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			deleteds = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("Error: " + e.toString());
			
		} finally {
			
			database.closeObject(preparedStatement);
			database.closeObject(connection);
			
		}
		if (deleteds > 0) {
			
			mensaje = "{\"mensaje\":\"Eliminar OK\"}";
			return Response.status(200).entity(mensaje).build();
			
		} else {
			
			mensaje = "{\"mensaje\":\"Error al eliminar\"}";
			return Response.status(400).entity(mensaje).build();
			
		}
	}
}
