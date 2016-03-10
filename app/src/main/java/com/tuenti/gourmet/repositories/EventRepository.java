package com.tuenti.gourmet.repositories;

import java.util.ArrayList;
import java.util.List;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Firebase.CompletionListener;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tuenti.gourmet.models.Event;

public class EventRepository extends Repository<Event> {

	private static EventRepository instance;

	public static EventRepository getInstance() {
		if (instance == null) {
			instance = new EventRepository();
		}
		return instance;
	}

	public EventRepository() {
		super("https://tuenti-restaurants.firebaseio.com/events", Event.class);
	}
}
