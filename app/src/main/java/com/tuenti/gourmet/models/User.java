package com.tuenti.gourmet.models;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class User {
	private String name;
	private String photoUrl;

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
}
