package com.tuenti.gourmet;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuenti.gourmet.adapters.EventViewHolder;
import com.tuenti.gourmet.models.Subscription;

/**
 * Created by gmerino on 11/03/16.
 */
public class SubscriptionsAdapter extends RecyclerView.Adapter<EventViewHolder> {

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
	public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.
				from(parent.getContext()).
				inflate(R.layout.subscription_item, parent, false);
		return new EventViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(EventViewHolder holder, int position) {
		Subscription subscription = subscriptions.get(position);
//		if (!TextUtils.isEmpty(subscription.getRestaurant().getPhoto())) {
//			Picasso.with(context).load(subscription.getRestaurant().getPhoto()).into(holder.imageView);
//		}
//		holder.tvTitle.setText(subscription.getRestaurant().getName());
//		holder.tvDate.setText(SimpleDateFormat.getDateInstance().format(subscription.getDate()));
//		holder.tvComment.setText(subscription.getComment());
	}

	@Override
	public int getItemCount() {
		return subscriptions.size();
	}
}