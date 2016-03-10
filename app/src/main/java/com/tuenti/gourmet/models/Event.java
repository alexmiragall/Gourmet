package com.tuenti.gourmet.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class Event {
	private Restaurant restaurant;
	private Date date;
	private User owner;
	private String comment;
	private List<User> occupants;

	public Event() {
	}

	public Event(Restaurant restaurant, Date date, User owner, String comment) {
		this.restaurant = restaurant;
		this.date = date;
		this.owner = owner;
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Date getDate() {
		return date;
	}

	public User getOwner() {
		return owner;
	}
}
