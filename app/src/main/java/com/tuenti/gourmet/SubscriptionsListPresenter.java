package com.tuenti.gourmet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.models.Subscription;
import com.tuenti.gourmet.repositories.Repository.Callback;
import com.tuenti.gourmet.repositories.RestaurantRepository;
import com.tuenti.gourmet.repositories.SubscriptionRepository;

/**
 * Created by gmerino on 11/03/16.
 */
public class SubscriptionsListPresenter implements Callback<Subscription> {

	private View view;
	private List<Subscription> subscriptions;



	@Override
	public void onDataChange(List<Subscription> items) {
	}

	public interface View {
		void onSubscriptionsChanged(List<Subscription> subscriptions);
	}

	public void init(View view) {
		this.view = view;
	}

	public void onResume() {
		List<Subscription> subscriptionsTemp = new ArrayList<>();
		Set<String> subscriptionNames = SubscriptionRepository.getInstance().getSubscriptions();

		List<Restaurant> restaurantList = RestaurantRepository.getInstance().getItems();

		for (Restaurant restaurant : restaurantList) {
			subscriptionsTemp.add(new Subscription(restaurant, subscriptionNames.contains(restaurant.getName())));
		}

		subscriptions = subscriptionsTemp;

		view.onSubscriptionsChanged(subscriptions);
	}

	public void onPause() {
		SubscriptionRepository.getInstance().reset();
		for (Subscription subscription : subscriptions) {
			if (subscription.isSubscribed()) {
				SubscriptionRepository.getInstance().post(subscription.getRestaurant().getName());
			}
		}

	}
}
