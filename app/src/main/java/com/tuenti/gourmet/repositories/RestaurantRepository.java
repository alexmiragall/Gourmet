package com.tuenti.gourmet.repositories;

import com.tuenti.gourmet.models.Restaurant;

public class RestaurantRepository extends Repository<Restaurant> {

	private static RestaurantRepository instance;

	public static RestaurantRepository getInstance() {
		if (instance == null) {
			instance = new RestaurantRepository();
		}
		return instance;
	}

	private RestaurantRepository() {
		super("https://tuenti-restaurants.firebaseio.com/restaurants", Restaurant.class);
	}
}
