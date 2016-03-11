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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Restaurant that = (Restaurant) o;

		if (PARCELABLE_RESTAURANT != that.PARCELABLE_RESTAURANT) return false;
		if (Double.compare(that.lat, lat) != 0) return false;
		if (Double.compare(that.lon, lon) != 0) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (address != null ? !address.equals(that.address) : that.address != null) return false;
		if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
		return !(description != null ? !description.equals(that.description) : that.description != null);

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = PARCELABLE_RESTAURANT;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		temp = Double.doubleToLongBits(lat);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lon);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (photo != null ? photo.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
