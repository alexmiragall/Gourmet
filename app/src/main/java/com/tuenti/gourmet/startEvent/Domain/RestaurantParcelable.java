package com.tuenti.gourmet.startEvent.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.tuenti.gourmet.models.Restaurant;

/**
 * Created by gmerino on 10/03/16.
 */
public class RestaurantParcelable implements Parcelable {

	private String name;
	private String address;
	private double lat;
	private double lon;
	private String photo;

	private int PARCELABLE_RESTAURANT = 0;

	public RestaurantParcelable(Restaurant restaurant) {
		this(restaurant.getName(),
				restaurant.getAddress(),
				restaurant.getLat(),
				restaurant.getLon(),
				restaurant.getPhoto());
	}

	public RestaurantParcelable(String name,
			String address,
			double lat,
			double lon,
			String photo) {
		this.name = name;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
		this.photo = photo;
	}

	protected RestaurantParcelable(Parcel in) {
		readFromParcel(in);
	}

	public static final Creator<RestaurantParcelable> CREATOR = new Creator<RestaurantParcelable>() {
		@Override
		public RestaurantParcelable createFromParcel(Parcel in) {
			return new RestaurantParcelable(in);
		}

		@Override
		public RestaurantParcelable[] newArray(int size) {
			return new RestaurantParcelable[size];
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
	}


	public void readFromParcel(Parcel source) {
		this.name = source.readString();
		this.address = source.readString();
		this.lat = source.readDouble();
		this.lon = source.readDouble();
		this.photo = source.readString();
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
