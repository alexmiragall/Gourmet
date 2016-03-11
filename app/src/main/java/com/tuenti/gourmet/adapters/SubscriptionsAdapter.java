package com.tuenti.gourmet.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.squareup.picasso.Picasso;
import com.tuenti.gourmet.R;
import com.tuenti.gourmet.models.Subscription;

/**
 * Created by gmerino on 11/03/16.
 */
public class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionViewHolder> {

	private final List<Subscription> subscriptions;
	private final Context context;

	public SubscriptionsAdapter(Context context, List<Subscription> subscriptions) {
		this.subscriptions = new ArrayList<>(subscriptions);
		this.context = context;
	}

	public void update(List<Subscription> subscriptions) {
		this.subscriptions.clear();
		this.subscriptions.addAll(subscriptions);
		notifyDataSetChanged();
	}

	@Override
	public SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.
				from(parent.getContext()).
				inflate(R.layout.subscription_item, parent, false);
		return new SubscriptionViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final SubscriptionViewHolder holder, int position) {
		final Subscription subscription = subscriptions.get(position);

		if (!TextUtils.isEmpty(subscription.getRestaurant().getPhoto())) {
			Picasso.with(context).load(subscription.getRestaurant().getPhoto()).into(holder.imageView);
		}

		holder.tvTitle.setText(subscription.getRestaurant().getName());

		holder.subscribe.setChecked(subscription.isSubscribed());

		holder.subscribe.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				subscription.setSubscribed(isChecked);
			}
		});
	}

	@Override
	public int getItemCount() {
		return subscriptions.size();
	}
}