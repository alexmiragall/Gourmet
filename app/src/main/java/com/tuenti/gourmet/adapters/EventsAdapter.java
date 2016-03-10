package com.tuenti.gourmet.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tuenti.gourmet.R;
import com.tuenti.gourmet.models.Event;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

	private final List<Event> events;
	private final Context context;

	public EventsAdapter(Context context, List<Event> events) {
		this.events = events;
		this.context = context;
	}

	public void update(List<Event> events) {
		this.events.clear();
		this.events.addAll(events);
		notifyDataSetChanged();
	}

	@Override
	public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.
				from(parent.getContext()).
				inflate(R.layout.event_item, parent, false);
		return new EventViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(EventViewHolder holder, int position) {
		Event event = events.get(position);
		if (!TextUtils.isEmpty(event.getRestaurant().getPhoto())) {
			Picasso.with(context).load(event.getRestaurant().getPhoto()).into(holder.imageView);
		}
		holder.tvTitle.setText(event.getRestaurant().getName());
		holder.tvDate.setText(SimpleDateFormat.getDateInstance().format(event.getDate()));
		holder.tvComment.setText(event.getComment());
	}

	@Override
	public int getItemCount() {
		return events.size();
	}
}
