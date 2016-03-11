package com.tuenti.gourmet.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class Restaurant implements Parcelable {
	private int PARCELABLE_RESTAURANT = 0;

	private String name;
	private String address;
	private double lat;
	private double lon;
	private String photo;
	private String description;

	public Restaurant() {

	}

	public Restaurant(String name,
			String address,
			double lat,
			double lon,
			String photo,
			String comment) {
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
		this.photo = photo;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	protected Restaurant(Parcel in) {
		readFromParcel(in);
	}

	public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
		@Override
		public Restaurant createFromParcel(Parcel in) {
			return new Restaurant(in);
		}

		@Override
		public Restaurant[] newArray(int size) {
			return new Restaurant[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(address);
		dest.writeDouble(lat);
		dest.writeDouble(lon);
		dest.writeString(photo);
		dest.writeString(description);
	}


	public void readFromParcel(Parcel source) {
		this.name = source.readString();
		this.address = source.readString();
		this.lat = source.readDouble();
		this.lon = source.readDouble();
		this.photo = source.readString();
		this.description = source.readString();
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

	public String getDescription() {
		return description;
	}


}
