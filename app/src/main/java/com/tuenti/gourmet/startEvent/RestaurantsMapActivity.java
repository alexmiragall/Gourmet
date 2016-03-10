package com.tuenti.gourmet.startEvent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tuenti.gourmet.R;
import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.repositories.RestaurantRepository;
import com.tuenti.gourmet.startEvent.Domain.RestaurantParcelable;
import com.tuenti.gourmet.startEvent.Presenter.RestaurantPresenter;

public class RestaurantsMapActivity extends FragmentActivity implements OnMapReadyCallback, RestaurantRepository
		.GetRestaurantCallback, OnMarkerClickListener {

	private GoogleMap map;

	private LatLng TUENTI_POSITION = new LatLng(40.4201097,-3.7030209);

	RestaurantPresenter restaurantPresenter = new RestaurantPresenter();
	private final Map<Marker, Restaurant> markerToRestaurant = new HashMap<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_map);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		MapsInitializer.initialize(getApplicationContext());

		mapFragment.getMapAsync(this);
	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		map = googleMap;

		// Add a marker in Sydney and move the camera
		map.moveCamera(CameraUpdateFactory.newLatLng(TUENTI_POSITION));
		map.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

		map.setOnMarkerClickListener(this);

		restaurantPresenter.initializer(this);

		addTuentiMarker();

	}

	private void addTuentiMarker() {
		MarkerOptions markerOptions = new MarkerOptions().position(TUENTI_POSITION).title(getString(R.string.tuenti));
		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_tuenti_marker));
		map.addMarker(markerOptions);
	}

	@Override
	public void onDataChange(List<Restaurant> restaurants) {
		map.clear();
		for(Restaurant restaurant : restaurants) {
			Marker marker = createMarkerForRestaurant(restaurant);
			markerToRestaurant.put(marker, restaurant);
		}
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(TUENTI_POSITION, 15.f));
	}

	private Marker createMarkerForRestaurant(Restaurant restaurant) {
		LatLng position = new LatLng(restaurant.getLat(), restaurant.getLon());
		MarkerOptions markerOptions = new MarkerOptions().position(position).title(restaurant.getName());
		markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_restaurant_marker));
		return map.addMarker(markerOptions);
	}

	@Override
	public boolean onMarkerClick(final Marker marker) {

		Restaurant restaurant = markerToRestaurant.get(marker);

		if (restaurant != null) {

			RestaurantParcelable restaurantParcelable = new RestaurantParcelable(restaurant);

			ViewRestaurantDialogFragment viewRestaurantDialogFragment = ViewRestaurantDialogFragment.newInstance(restaurantParcelable);
			viewRestaurantDialogFragment.show(getSupportFragmentManager(), "ViewRestaurantDialogFragment");

			return true;
		}
		return false;
	}

}
