package com.tuenti.gourmet.repositories;

import com.tuenti.gourmet.models.User;

public class UserRepository extends Repository<User> {

	private static UserRepository instance;

	private User currentUser;

	public static UserRepository getInstance() {
		if (instance == null) {
			instance = new UserRepository();
		}
		return instance;
	}

	private UserRepository() {
		super("https://tuenti-restaurants.firebaseio.com/users", User.class);
	}

	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	public User getCurrentUser() {
		return currentUser;
	}
}
