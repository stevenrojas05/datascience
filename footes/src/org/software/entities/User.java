package org.software.entities;

import java.util.Date;

public class User {
	
	private int id;
	private int user_type;
	private String username;
	private String email;
	private String password;
	private Date created_at;
	private Date updated_at;
	
	
	
	public User() {
		
	}
	
	public User(int id, int user_type, String username, String email, String password, Date created_at,
			Date updated_at) {
		super();
		this.id = id;
		this.user_type = user_type;
		this.username = username;
		this.email = email;
		this.password = password;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
	

}
