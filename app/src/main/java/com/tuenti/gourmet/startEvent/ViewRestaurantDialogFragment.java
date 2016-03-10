package com.tuenti.gourmet.startEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tuenti.gourmet.R;
import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.startEvent.Domain.RestaurantParcelable;

/**
 * Created by gmerino on 10/03/16.
 */
public class ViewRestaurantDialogFragment extends AppCompatDialogFragment {

	@Bind(R.id.name)
	public TextView name;

	@Bind(R.id.imageView)
	public ImageView imageView;

	@Bind(R.id.button)
	public Button button;

	private static final String PARCELABLE_KEY = "restaurant";

	private Restaurant restaurant;

	public static ViewRestaurantDialogFragment newInstance(Restaurant restaurantParcelable) {
		ViewRestaurantDialogFragment viewRestaurantDialogFragment = new ViewRestaurantDialogFragment();

		Bundle arguments = new Bundle();
		arguments.putParcelable(PARCELABLE_KEY, restaurantParcelable);
		viewRestaurantDialogFragment.setArguments(arguments);

		return viewRestaurantDialogFragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new Dialog(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
	}

	@Override
	public final View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_view_detail, container, false);
		ButterKnife.bind(this, view);
		return view;

	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		Bundle arguments = getArguments();
		restaurant = arguments.getParcelable(PARCELABLE_KEY);

		name.setText(restaurant.getName());
		Picasso.with(getActivity()).load(restaurant.getPhoto()).into(imageView);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), RestaurantActivity.class);
				intent.putExtra(RestaurantActivity.PARCELABLE_KEY, restaurant);
				startActivity(intent);
				dismiss();
			}
		});

	}
}
