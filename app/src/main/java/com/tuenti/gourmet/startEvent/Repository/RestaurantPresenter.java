package com.tuenti.gourmet.startEvent.Repository;

import java.util.ArrayList;
import java.util.List;

import com.tuenti.gourmet.models.Restaurant;


/**
 * Created by jmbrocal on 10/3/16.
 */
public class RestaurantPresenter {

	public interface RestaurantCallback {
		void onDataChange(List<Restaurant> restaurantList);
	}


	public void initializer(RestaurantCallback callback) {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant rest = new Restaurant("Chaparrito",
				"Calle del Marqués de Cubas, 6",
				40.417252D,
				-3.698831D,
				"https://lh4.googleusercontent.com/-u2_e-NOBDCE/UIfH9ZH92kI/AAAAAAAADRA/kZKoQW--Js8/s544-k-no/");
		restaurants.add(rest);

		callback.onDataChange(restaurants);
	}

}
