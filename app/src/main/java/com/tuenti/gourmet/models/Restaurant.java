package com.tuenti.gourmet.models;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class Restaurant {
	private String name;
	private String address;
	private double lat;
	private double lon;
	private String photo;

	public Restaurant() {

	}

	public Restaurant(String name, String address, double lat, double lon, String photo) {
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

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public String getPhoto() {
		return photo;
	}
}
