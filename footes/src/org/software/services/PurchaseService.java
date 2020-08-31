package org.software.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.software.entities.Item;
import org.software.entities.Purchase;
import org.software.entities.User;
import org.software.lists.ItemList;
import org.software.lists.PurchaseList;
import org.software.util.DataBase;

@Path("/purchase")
public class PurchaseService {

	@GET
	@Path("/items/{purchase_id}")
	@Produces("application/json")
	public ItemList getItems(@Context HttpServletRequest request, @PathParam(value = "purchase_id") int purchase_id) {
		
		ArrayList<Item> lista = new ArrayList<Item>();
		
		DataBase database = new DataBase();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			connection = database.getConnection("client");
			statement = connection.createStatement();
			
			HttpSession session = request.getSession();
			
			User usuario = (User)session.getAttribute("usuario");
			
			
			
			int user_id = usuario.getId();
			
			if (purchase_id == 0) {
				sql = "select id from purchases";
				sql += " where user_id = " + user_id;
				rs = statement.executeQuery(sql);
				if (rs.next()) {
					purchase_id = rs.getInt("id");
				}
				database.closeObject(rs);
			}
					
					
			sql = "select purchase_items.id as item_id, * from purchases, purchase_items, products"; 
			sql += " where purchases.id = purchase_items.purchase_id"; 
			sql += " and purchase_items.product_id = products.id"; 
			sql += " and purchase_id = " + purchase_id;
					
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("item_id");
				double quantity = rs.getDouble("quantity");
				double price = rs.getDouble("price");
				int product_id = rs.getInt("product_id");
				String product_name = rs.getString("name");
				String short_description = rs.getString("short_description");
				String product_icon = rs.getString("icon");
				
				Item item = new Item();
				item.setId(id);
				item.setQuantity(quantity);
				item.setPrice(price);
				item.setProduct_id(product_id);
				item.setProduct_name(product_name);
				item.setProduct_description(short_description);
				item.setProduct_icon(product_icon);
				
				lista.add(item);
			}
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			database.closeObject(rs);
			database.closeObject(statement);
			database.closeObject(connection);
		}
		
		return new ItemList(lista);
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public PurchaseList getPurchases(@Context HttpServletRequest request) {
		
		ArrayList<Purchase> lista = new ArrayList<Purchase>();
		
		HttpSession session = request.getSession();
		
		User usuario = (User)session.getAttribute("usuario");
		int user_id = usuario.getId();
		
		DataBase database = new DataBase();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String sql = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			connection = database.getConnection("client");
			statement = connection.createStatement();
			
			sql = "select * from purchases";
			sql += " where user_id = " + user_id; 
			sql += " order by purchases.created_at DESC";
			
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				Timestamp created_at = rs.getTimestamp("created_at");
				Timestamp updated_at = rs.getTimestamp("updated_at");
				
				String created_at_text = dateFormat.format(created_at.getTime());
				
				Purchase purchase = new Purchase();
				purchase.setId(id);
				purchase.setUser_id(user_id);
				purchase.setCreated_at(created_at);
				purchase.setUpdated_at(updated_at);
				purchase.setCreated_at_text(created_at_text);
				
				lista.add(purchase);
			}
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			database.closeObject(rs);
			database.closeObject(statement);
			database.closeObject(connection);
		}
		
		return new PurchaseList(lista);
	}

}