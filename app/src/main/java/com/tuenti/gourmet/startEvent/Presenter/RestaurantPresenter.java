package com.tuenti.gourmet.startEvent.Presenter;

import java.util.ArrayList;
import java.util.List;

import com.tuenti.gourmet.models.Restaurant;

/**
 * Created by jmbrocal on 10/3/16.
 */
public class RestaurantPresenter {

	private RestaurantCallback callback;

	public void initializer(RestaurantCallback callback) {
		this.callback = callback;
	}

	private void loadMock() {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant rest = new Restaurant("Taj", "Calle del Marqués de Cubas, 6",
				40.417252,
				-3.698831,
				"https://lh4.googleusercontent.com/-u2_e-NOBDCE/UIfH9ZH92kI/AAAAAAAADRA/kZKoQW--Js8/s544-k-no/");
		restaurants.add(rest);

		rest = new Restaurant("El Chaparrito", "Calle Ventura de la Vega, 11",
				40.415459,
				-3.698831,
				"https://geo3.ggpht.com/cbk?panoid=0aowlUbXe4sAAAQvOfRizQ&output=thumbnail&cb_client=search.TACTILE.gps&thumb=2&w=408&h=256&yaw=106.04575&pitch=0");
		restaurants.add(rest);
		callback.onDataChange(restaurants);
	}

	public interface RestaurantCallback {
		void onDataChange(List<Restaurant> restaurantList);
	}
}
