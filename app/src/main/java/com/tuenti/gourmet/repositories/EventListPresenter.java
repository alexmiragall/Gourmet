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
public class EventListPresenter {

	private View view;
	private List<Event> events = new ArrayList<>();

	public interface View {
		void onEventsChanged(List<Event> events);
	}

	public void init(View view) {
		this.view = view;

		User user = new User("Pepe", "pepe");
		Restaurant restaurant = new Restaurant("Taj", "Calle", 2.0, 3.0, "");
		Event event = new Event(restaurant, new Date(), user, "Comentario");
		events.add(event);
		events.add(event);
		events.add(event);
		view.onEventsChanged(events);
	}


}
