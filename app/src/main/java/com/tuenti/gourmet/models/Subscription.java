package com.tuenti.gourmet.models;

/**
 * Created by gmerino on 11/03/16.
 */
public class Subscription {

	private Restaurant restaurant;

	private boolean subscribed;

	public Subscription(Restaurant restaurant, boolean subscribed) {
		this.restaurant = restaurant;
		this.subscribed = subscribed;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
}
