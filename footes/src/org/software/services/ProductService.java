package org.software.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.software.entities.Product;
import org.software.lists.ProductList;
import org.software.util.DataBase;

@Path("/products")
public class ProductService {
	
	@GET
	@Path("/ByCategory/{category}")
	@Produces("application/json")
	public ProductList getProductsByCategory(@PathParam(value = "category") int category) {
		ArrayList<Product> productList = new ArrayList<Product>();
		DataBase database = new DataBase();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String sql = "";
		try {
			connection = database.getConnection("client");
			statement = connection.createStatement();
			sql = "select * from products where category_id = " + category;
			sql += " and published = 1 order by name";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				
				long id = rs.getLong("id");
				int category_id = rs.getInt("category_id");
				int published = rs.getInt("published");
				String name = rs.getString("name");
				double pricing = rs.getDouble("pricing");
				String icon = rs.getString("icon");
				String short_description = rs.getString("short_description");
				String long_description = rs.getString("long_description");
				
				Product product = new Product();
				product.setCategory_id(category_id);
				product.setId(id);
				product.setPublished(published);
				product.setName(name);
				product.setPricing(pricing);
				product.setIcon(icon);
				product.setShort_description(short_description);
				product.setLong_description(long_description);
				productList.add(product);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs);
			database.closeObject(statement);
			database.closeObject(connection);
		}
		return new ProductList(productList);
	}
	
	
	
	@GET
	@Path("/ListRecommended/{product_id}")
	@Produces("application/json")
	public ProductList getProductsRecommended(@PathParam(value = "product_id") int product_id) {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		DataBase database = new DataBase();
		
		DataBase database1 = new DataBase();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		
		Connection connection1 = null;
		Statement statement1 = null;
		ResultSet rs1 = null;
		
		
		String sql = "";
		double media = 0;
		
		
		try {
			connection1 = database1.getConnection("client");
			statement1 = connection1.createStatement();
			sql = "select avg(rating) as media from reviews where item_id =" + product_id;
			rs1 = statement1.executeQuery(sql);
			if(rs1.next()){
				media = rs1.getDouble("media");
			}
				

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database1.closeObject(rs1);
			database1.closeObject(statement1);
			database1.closeObject(connection1);
		}
		
		
		
		
		try {
			connection = database.getConnection("client");
			statement = connection.createStatement();
			sql = "select * from products where id = " + product_id;
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				
				long id = rs.getLong("id");
				int category_id = rs.getInt("category_id");
				int published = rs.getInt("published");
				String name = rs.getString("name");
				double pricing = rs.getDouble("pricing");
				String icon = rs.getString("icon");
				String short_description = rs.getString("short_description");
				String long_description = rs.getString("long_description");
				
				Product product = new Product();
				product.setCategory_id(category_id);
				product.setId(id);
				product.setPublished(published);
				product.setName(name);
				product.setPricing(pricing);
				product.setMedia(media);
				product.setIcon(icon);
				product.setShort_description(short_description);
				product.setLong_description(long_description);
				productList.add(product);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs);
			database.closeObject(statement);
			database.closeObject(connection);
		}
		return new ProductList(productList);
	}
	
	
	@GET
	@Path("/RecommendCategory/{category_id}/{product_id}")
	@Produces("application/json")
	public ProductList getProductsRecommendedCategory(@PathParam(value = "category_id") int category_id_parameter, @PathParam(value = "product_id") int product_id) {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		DataBase database = new DataBase();
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		String sql = "";
		try {
			connection = database.getConnection("client");
			statement = connection.createStatement();
			sql = "select * from products where category_id = " + category_id_parameter;
			sql += " and id = "+product_id;
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				
				long id = rs.getLong("id");
				int category_id = rs.getInt("category_id");
				int published = rs.getInt("published");
				String name = rs.getString("name");
				double pricing = rs.getDouble("pricing");
				String icon = rs.getString("icon");
				String short_description = rs.getString("short_description");
				String long_description = rs.getString("long_description");
				
				Product product = new Product();
				product.setCategory_id(category_id);
				product.setId(id);
				product.setPublished(published);
				product.setName(name);
				product.setPricing(pricing);
				product.setIcon(icon);
				product.setShort_description(short_description);
				product.setLong_description(long_description);
				productList.add(product);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} finally {
			database.closeObject(rs);
			database.closeObject(statement);
			database.closeObject(connection);
		}
		return new ProductList(productList);
	}

	
}