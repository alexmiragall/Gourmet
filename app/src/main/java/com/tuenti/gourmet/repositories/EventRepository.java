package com.tuenti.gourmet.repositories;

import java.util.List;

import com.tuenti.gourmet.models.Event;

public class EventRepository {
	/**
	 * Copyright (c) Tuenti Technologies. All rights reserved.
	 */
	public interface Callback {
		void onEventsChanged(List<Event> events);
	}

	private Callback callback;
	public void init(Callback eventListener) {
		this.callback = eventListener;

//		Restaurant restaurant = new Restaurant("Taj", )
//		Event event = new Event()
	}


}
