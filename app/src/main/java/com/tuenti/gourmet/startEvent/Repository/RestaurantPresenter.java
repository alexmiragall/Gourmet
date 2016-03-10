package com.tuenti.gourmet.startEvent.Repository;

import java.util.List;

import com.tuenti.gourmet.models.Restaurant;


/**
 * Created by jmbrocal on 10/3/16.
 */
public class RestaurantPresenter {

	public interface RestaurantCallback {
		void onDataChange(List<Restaurant> restaurantList);
	}
}
