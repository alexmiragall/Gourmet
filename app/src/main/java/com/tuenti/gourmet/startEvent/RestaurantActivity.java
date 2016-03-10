package com.tuenti.gourmet.startEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;
import com.tuenti.gourmet.R;
import com.tuenti.gourmet.models.Event;
import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.models.User;
import com.tuenti.gourmet.repositories.EventRepository;

public class RestaurantActivity extends AppCompatActivity {

	public static final String PARCELABLE_KEY = "restaurant";

	@Bind(R.id.image_header)
	ImageView imageHeader;

	@Bind(R.id.description)
	TextView description;

	@Bind(R.id.fab)
	FloatingActionButton floatingActionButton;

	@Bind(R.id.toolbar)
	Toolbar toolbar;

	@Bind(R.id.toolbar_layout)
	CollapsingToolbarLayout collapsingToolbarLayout;


	private RestaurantParcelable restaurantParcelable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);

		Intent intent = getIntent();
		restaurantParcelable = intent.getParcelableExtra(PARCELABLE_KEY);


		if (!TextUtils.isEmpty(restaurantParcelable.getPhoto())) {
			setImage(restaurantParcelable.getPhoto());

		}

		setTitle(restaurantParcelable.getName());

		floatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				User owner = new User("José Miguel Brocal", "http://www.google.es");
				Event event = new Event(restaurantParcelable, 0L, owner, "Esto es un comentario");

				EventRepository.getInstance().add(event);
			}
		});


	}

	private void setImage(String photo) {

		Target target = new Target() {
			@Override
			public void onBitmapLoaded(Bitmap bitmap, LoadedFrom loadedFrom) {
				imageHeader.setImageBitmap(bitmap);

				Palette palette = Palette.generate(bitmap);
				int defaultColor = 0x000000;

				int vibrant = palette.getVibrantColor(defaultColor);
				int darkMutedColor = palette.getMutedColor(defaultColor);

				floatingActionButton.setBackgroundTintList(new ColorStateList(new int[][]{new int[]{0}}, new int[]{vibrant}));
				collapsingToolbarLayout.setContentScrimColor(darkMutedColor);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					Window window = getWindow();
					window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
					window.setStatusBarColor(darkMutedColor);
				}

			}

			@Override
			public void onBitmapFailed(Drawable drawable) {

			}

			@Override
			public void onPrepareLoad(Drawable drawable) {

			}
		};

		Picasso.with(this)
				.load(photo)
				.into(target);
	}

}
