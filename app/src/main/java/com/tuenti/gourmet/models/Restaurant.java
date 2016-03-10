package com.tuenti.gourmet.models;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class Restaurant {
	private String name;
	private String address;
	private String lat;
	private String lon;
	private String photo;

	public Restaurant(String name, String address, String lat, String lon, String photo) {
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}

	public String getPhoto() {
		return photo;
	}
}
