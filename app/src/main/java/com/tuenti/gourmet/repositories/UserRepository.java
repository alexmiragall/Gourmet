package com.tuenti.gourmet.repositories;

import com.tuenti.gourmet.models.User;

public class UserRepository extends Repository<User> {

	private static UserRepository instance;

	public static UserRepository getInstance() {
		if (instance == null) {
			instance = new UserRepository();
		}
		return instance;
	}

	private UserRepository() {
		super("https://tuenti-restaurants.firebaseio.com/users", User.class);
	}
}
