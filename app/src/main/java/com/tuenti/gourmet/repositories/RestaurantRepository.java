package com.tuenti.gourmet.repositories;

import java.util.ArrayList;
import java.util.List;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tuenti.gourmet.models.Restaurant;

/**
 * Created by jmbrocal on 10/3/16.
 */
public class RestaurantRepository {

	private String FIREBASE_URL = "https://tuenti-restaurants.firebaseio.com/restaurants";

	private Firebase firebase;
	private GetRestaurantCallback callback;

	public RestaurantRepository() {
		firebase = new Firebase(FIREBASE_URL);
	}

	public void getAllRestaurants(final GetRestaurantCallback callback) {
		this.callback = callback;

		firebase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				List<Restaurant> restaurants = new ArrayList<>();
				for (DataSnapshot postSnapshot : snapshot.getChildren()) {
					Restaurant restaurant = postSnapshot.getValue(Restaurant.class);
					restaurants.add(restaurant);
				}
				callback.onDataChange(restaurants);
			}
			@Override
			public void onCancelled(FirebaseError firebaseError) {
				System.out.println("The read failed: " + firebaseError.getMessage());
			}
		});
	}

	public interface GetRestaurantCallback {
		void onDataChange(List<Restaurant> restaurantList);
	}
}
