package com.tuenti.gourmet.startEvent.Presenter;

import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.repositories.Repository;
import com.tuenti.gourmet.repositories.RestaurantRepository;

/**
 * Created by jmbrocal on 10/3/16.
 */
public class RestaurantPresenter {

	public void initializer(final Repository.Callback<Restaurant> callback) {
		RestaurantRepository.getInstance().subscribe(callback);
		callback.onDataChange(RestaurantRepository.getInstance().getItems()); /* Send existing items */
	}
}
