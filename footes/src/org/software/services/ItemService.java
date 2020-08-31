package org.software.services;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.software.entities.Item;
import org.software.entities.User;
import org.software.lists.ItemList;
import org.software.util.DataBase;

@Path("/cart")
public class ItemService {

	@GET
	@Path("/items")
	@Produces("application/json")
	// @Consumes("application/json")
	public Response getItemsCount(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		System.out.println(cart);

		if (cart == null) {
			cart = new ArrayList<Item>();
		}

		int items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

	@POST
	@Path("/update")
	@Produces("application/json")
	// @Consumes("application/json")
	public Response updateItems(@Context HttpServletRequest request, String data) {

		JsonReader reader = Json.createReader(new StringReader(data));
		JsonObject jsonObject = reader.readObject();
		JsonArray jArray = jsonObject.getJsonArray("data");

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<Item>();
		}

		for (int i = 0; i < jArray.size(); i++) {

			JsonObject o = jArray.get(i).asJsonObject();

			int product_id = o.getInt("product_id");
			double quantity = (double) o.getInt("quantity");

			Item item = (Item) cart.get(i);
			item.setId(product_id);
			item.setQuantity(quantity);

			cart.set(i, item);
		}

		session.setAttribute("cart", cart);

		for (int i = 0; i < cart.size(); i = i + 1) {

			Item item = (Item) cart.get(i);
			int id = item.getProduct_id();
			double qty = item.getQuantity();

			System.out.println(id + "," + qty);
		}

		int items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/add/{product_id}")
	@Produces("application/json")
	public Response addItem(@Context HttpServletRequest request, @PathParam(value = "product_id") int product_id) {

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<Item>();
		}

		for (int i = 0; i < cart.size(); i = i + 1) {

			Item item = (Item) cart.get(i);
			int id = item.getProduct_id();

			if (id == product_id) {
				int items = cart.size();
				String result = "{\"items\":" + items + "}";
				return Response.status(401).entity(result).build();
			}
		}

		Item item = new Item();
		item.setProduct_id(product_id);
		item.setQuantity(1);

		DataBase database = new DataBase();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String sql = "";
		try {
			connection = database.getConnection("client");
			statement = connection.createStatement();
			sql = "select * from products where id = " + product_id;
			rs = statement.executeQuery(sql);

			if (rs.next()) {

				String name = rs.getString("name");
				String short_description = rs.getString("short_description");
				double pricing = rs.getDouble("pricing");
				String icon = rs.getString("icon");

				item.setProduct_name(name);
				item.setProduct_description(short_description);
				item.setPrice(pricing);
				item.setProduct_icon(icon);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs);
			database.closeObject(statement);
			database.closeObject(connection);
		}

		cart.add(item);

		session.setAttribute("cart", cart);

		int items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/del/{product_id}")
	@Produces("application/json")
	public Response delItem(@Context HttpServletRequest request, @PathParam(value = "product_id") int product_id) {

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<Item>();
		}

		for (int i = 0; i < cart.size(); i = i + 1) {
			Item item = (Item) cart.get(i);
			int id = item.getProduct_id();

			if (id == product_id) {
				cart.remove(i);
			}
		}

		session.setAttribute("cart", cart);

		int items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	public ItemList getItems(@Context HttpServletRequest request) {

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<Item>();
		}

		return new ItemList(cart);
	}

	public int insertPurchase(int user_id) {

		int purchase_id = 0;

		DataBase database = new DataBase();
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String sql = "";

		try {
			connection = database.getConnection("client");

			sql = "INSERT INTO purchases (user_id)";
			sql = sql + " VALUES (?) RETURNING id";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				purchase_id = rs.getInt("id");
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs);
			database.closeObject(preparedStatement);
			database.closeObject(connection);
		}

		return purchase_id;
	}
	
	
	@GET
	@Path("/checkout")
	@Produces("application/json")
	public Response updateItems(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		User usuario = (User)session.getAttribute("usuario");
		int user_id = usuario.getId();
		
		@SuppressWarnings("unchecked")
		ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<Item>();
		}

		if (cart.size() > 0) {
			int purchase_id = insertPurchase(user_id);
			
			DataBase database = new DataBase();
			Connection connection = null;
			Statement statement = null;
			String sql = "";

			
			sql = "INSERT INTO purchase_items (purchase_id, product_id, price, quantity) VALUES ";
			String separator = "";
			for (int i = 0; i < cart.size(); i = i + 1) {
				Item item = (Item) cart.get(i);
				long product_id = item.getProduct_id();
				double quantity = item.getQuantity();
				double price = item.getPrice();
				
				sql = sql + separator + "(" + purchase_id + "," + product_id + "," + price + "," + quantity + ")";
				separator = ", "; 
			}
		
			try {
				connection = database.getConnection("client");
				statement = connection.createStatement();
				System.out.println(statement.executeUpdate(sql));
				
			} 
			catch (Exception e) {
				System.out.println("Error: " + e.toString());
			}
			finally {
				database.closeObject(statement);
				database.closeObject(connection);
			}
		}
		
		cart = new ArrayList<Item>();
		session.setAttribute("cart", cart);

		long items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}
	
}
