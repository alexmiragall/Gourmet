package com.tuenti.gourmet.startEvent.Presenter;

import java.util.ArrayList;
import java.util.List;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.repositories.RestaurantRepository;
import com.tuenti.gourmet.repositories.RestaurantRepository.GetRestaurantCallback;

/**
 * Created by jmbrocal on 10/3/16.
 */
public class RestaurantPresenter {

	private RestaurantRepository restaurantRepository;

	public RestaurantPresenter() {
		restaurantRepository = new RestaurantRepository();
	}

	public void initializer(final GetRestaurantCallback callback) {
		restaurantRepository.getAllRestaurants(callback);
	}
}
