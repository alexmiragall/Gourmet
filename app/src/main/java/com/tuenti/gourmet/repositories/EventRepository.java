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

public class EventRepository {

	public interface GetAllEventsCallback {
		void onEventCreated(Event event);
		void onEventChanged(Event event);
		void onEventDeleted(Event event);
	}

	public interface CreateNewEventCallback {
		void onEventCreated();
	}

	private String FIREBASE_URL = "https://tuenti-restaurants.firebaseio.com/events";
	private Firebase firebase;
	private GetAllEventsCallback getAllEventsCallback;
	private ChildEventListener childEventListener;

	public EventRepository() {
		firebase = new Firebase(FIREBASE_URL);
	}

	public void getAllEvents(GetAllEventsCallback callback) {
		getAllEventsCallback = callback;

		childEventListener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				Event eventAdded = dataSnapshot.getValue(Event.class);
				getAllEventsCallback.onEventCreated(eventAdded);
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {
				Event eventChanged = dataSnapshot.getValue(Event.class);
				getAllEventsCallback.onEventChanged(eventChanged);
			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
				Event eventRemoved = dataSnapshot.getValue(Event.class);
				getAllEventsCallback.onEventDeleted(eventRemoved);
			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {

			}
		};

		firebase.addChildEventListener(childEventListener);
	}

	public void onSubscribObserver() {
		firebase.removeEventListener(childEventListener);
	}

	public void createEvent(Event newEvent, final CreateNewEventCallback createNewEventCallback) {
		firebase.push().setValue(newEvent, new CompletionListener() {
			@Override
			public void onComplete(FirebaseError firebaseError, Firebase firebase) {
				createNewEventCallback.onEventCreated();
			}
		});
	}
}
