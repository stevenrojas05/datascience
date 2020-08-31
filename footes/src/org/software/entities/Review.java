package org.software.entities;

import java.util.Date;

public class Review {
	private int id;
	private int user_id;
	private int item_id;
	private double rating;
	private String comment;
	private int approved;
	private int spam;
	private Date created_at;
	private Date updated_at;
	
	public Review() {
		
	}
	
	public Review(int id, int user_id, int item_id, double rating, String comment, int approved, int spam,
			Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.item_id = item_id;
		this.rating = rating;
		this.comment = comment;
		this.approved = approved;
		this.spam = spam;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getSpam() {
		return spam;
	}

	public void setSpam(int spam) {
		this.spam = spam;
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
