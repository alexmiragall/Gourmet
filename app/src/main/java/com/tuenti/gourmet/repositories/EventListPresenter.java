package com.tuenti.gourmet.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tuenti.gourmet.models.Event;
import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.models.User;
import com.tuenti.gourmet.repositories.Repository.Callback;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class EventListPresenter implements Callback<Event> {

	private View view;
	private List<Event> events = new ArrayList<>();

	@Override
	public void onDataChange(List<Event> items) {
		events = items;
		view.onEventsChanged(events);
	}

	public interface View {
		void onEventsChanged(List<Event> events);
	}

	public void init(View view) {
		this.view = view;
	}
	public void onResume() {
		EventRepository.getInstance().subscribe(this);
	}

	public void onPause() {
		EventRepository.getInstance().unsubscribe(this);
	}
}
