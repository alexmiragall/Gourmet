package com.tuenti.gourmet.models;

import java.util.List;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class Event {
	private Restaurant restaurant;
	private Long date;
	private User owner;
	private String comment;
	private List<User> occupants;

	public Event() {
	}

	public Event(Restaurant restaurant, Long date, User owner, String comment) {
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

	public Long getDate() {
		return date;
	}

	public User getOwner() {
		return owner;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Event event = (Event) o;

		if (restaurant != null ? !restaurant.equals(event.restaurant) : event.restaurant != null) return false;
		if (date != null ? !date.equals(event.date) : event.date != null) return false;
		if (owner != null ? !owner.equals(event.owner) : event.owner != null) return false;
		if (comment != null ? !comment.equals(event.comment) : event.comment != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = restaurant != null ? restaurant.hashCode() : 0;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (owner != null ? owner.hashCode() : 0);
		result = 31 * result + (comment != null ? comment.hashCode() : 0);
		result = 31 * result + (occupants != null ? occupants.hashCode() : 0);
		return result;
	}
}
