package com.tuenti.gourmet.repositories;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SubscriptionRepository {

	private static final String SUBSCRIPTIONS_ARRAY_PREFERENCE = "subscriptions_array_preference";

	private static SubscriptionRepository instance;

	public static SubscriptionRepository getInstance() {
		if (instance == null) {
			instance = new SubscriptionRepository();
		}
		return instance;
	}

	private SharedPreferences preferences;
	private Set<String> subscriptions;

	public void initialize(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		subscriptions = preferences.getStringSet(SUBSCRIPTIONS_ARRAY_PREFERENCE, new HashSet<String>());
	}

	public void post(String restaurantName) {
		subscriptions.add(restaurantName);
		preferences.edit()
				.putStringSet(SUBSCRIPTIONS_ARRAY_PREFERENCE, subscriptions)
				.apply();
	}


	public void remove(String restaurantName) {
		subscriptions.remove(restaurantName);
		preferences.edit()
				.putStringSet(SUBSCRIPTIONS_ARRAY_PREFERENCE, subscriptions)
				.apply();
	}

	public boolean isSubscribedTo(String restaurantName) {
		return subscriptions.contains(restaurantName);
	}

	public Set<String> getSubscriptions() {
		return new HashSet<>(subscriptions);
	}

	public void reset() {
		subscriptions.clear();
		preferences.edit()
				.putStringSet(SUBSCRIPTIONS_ARRAY_PREFERENCE, subscriptions)
				.apply();
	}
}
