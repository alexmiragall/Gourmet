package com.tuenti.gourmet.startEvent.Repository;

import java.util.ArrayList;
import java.util.List;

import com.tuenti.gourmet.startEvent.Domain.Restaurant;

/**
 * Created by jmbrocal on 10/3/16.
 */
public class RestaurantPresenter {

	public interface RestaurantCallback {
		void onDataChange(List<Restaurant> restaurantList);
	}


	public void initializer(RestaurantCallback callback) {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant rest = new Restaurant();
		rest.address = "Calle del Marqués de Cubas, 6";
		rest.lat = 40.417252;
		rest.lon = -3.698831;
		rest.photoURL = "https://lh4.googleusercontent.com/-u2_e-NOBDCE/UIfH9ZH92kI/AAAAAAAADRA/kZKoQW--Js8/s544-k-no/";
		restaurants.add(rest);

		rest = new Restaurant();
		rest.address = "Calle Ventura de la Vega, 11";
		rest.lat = 40.415459;
		rest.lon = -3.698831;
		rest.photoURL = "https://geo3.ggpht.com/cbk?panoid=0aowlUbXe4sAAAQvOfRizQ&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=256&yaw=106.04575&pitch=0";
		restaurants.add(rest);

		callback.onDataChange(restaurants);
	}

}
