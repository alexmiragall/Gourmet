package com.tuenti.gourmet.startEvent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tuenti.gourmet.R;
import com.tuenti.gourmet.startEvent.Domain.RestaurantParcelable;

public class RestaurantActivity extends AppCompatActivity {

	public static final String PARCELABLE_KEY = "restaurant";

	private RestaurantParcelable restaurantParcelable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Intent intent = getIntent();
		restaurantParcelable = intent.getParcelableExtra(PARCELABLE_KEY);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});


	}

}