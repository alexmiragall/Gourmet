package com.tuenti.gourmet.models;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class User {

	private String name;
	private String photoUrl;

	public User() {
	}

	public User(String name, String photoUrl) {
		this.name = name;
		this.photoUrl = photoUrl;
	}

	public String getName() {
		return name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		return !(photoUrl != null ? !photoUrl.equals(user.photoUrl) : user.photoUrl != null);

	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (photoUrl != null ? photoUrl.hashCode() : 0);
		return result;
	}
}
