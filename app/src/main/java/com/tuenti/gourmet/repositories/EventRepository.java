package com.tuenti.gourmet.repositories;

import java.util.ArrayList;
import java.util.List;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Firebase.CompletionListener;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.tuenti.gourmet.models.Event;

public class EventRepository {

	public interface GetAllEventsCallback {
		void onEventsChanged(List<Event> events);
	}

	public interface CreateNewEventCallback {
		void onEventCreated();
	}

	private String FIREBASE_URL = "https://tuenti-restaurants.firebaseio.com/events";
	private Firebase firebase;
	private GetAllEventsCallback getAllEventsCallback;

	public EventRepository() {
		firebase = new Firebase(FIREBASE_URL);
	}

	public void getAllEvents(GetAllEventsCallback callback) {
		getAllEventsCallback = callback;

		firebase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {

			}
		});
	}

	public void createEvent(Event newEvent, final CreateNewEventCallback createNewEventCallback) {
		firebase.setValue(newEvent, new CompletionListener() {
			@Override
			public void onComplete(FirebaseError firebaseError, Firebase firebase) {
				createNewEventCallback.onEventCreated();
			}
		});
	}
}
