package com.tuenti.gourmet.models;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class Subscription {
	private User user;
	private Restaurant restaurant;

	public Subscription(User user, Restaurant restaurant) {
		this.user = user;
		this.restaurant = restaurant;
	}

	public User getUser() {
		return user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
}
