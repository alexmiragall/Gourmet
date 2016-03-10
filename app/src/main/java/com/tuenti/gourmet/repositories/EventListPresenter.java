package com.tuenti.gourmet.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tuenti.gourmet.models.Event;
import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.models.User;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class EventListPresenter implements EventRepository.GetAllEventsCallback {

	private View view;
	private List<Event> events = new ArrayList<>();
	private EventRepository eventRepository;

	public interface View {
		void onEventsChanged(List<Event> events);
	}

	public EventListPresenter() {
		eventRepository = new EventRepository();
	}

	public void init(View view) {
		this.view = view;
		eventRepository.getAllEvents(this);
	}

	public void onPause() {
		eventRepository.onSubscribObserver();
	}

	@Override
	public void onEventCreated(Event event) {
		events.add(event);
		view.onEventsChanged(events);
	}

	@Override
	public void onEventChanged(Event event) {

	}

	@Override
	public void onEventDeleted(Event event) {
		events.remove(event);
		view.onEventsChanged(events);
	}

}
