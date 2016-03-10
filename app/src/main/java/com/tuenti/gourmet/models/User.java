package com.tuenti.gourmet.models;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class User {
	private String name;
	private String username;
	private String photo;

	public User() {
	}

	public User(String name, String username, String photo) {
		this.name = name;
		this.username = username;
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPhoto() {
		return photo;
	}
}
