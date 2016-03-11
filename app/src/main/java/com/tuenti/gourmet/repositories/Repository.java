package com.tuenti.gourmet.repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Firebase.CompletionListener;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public abstract class Repository<T> {

	private final Set<Callback<T>> callbacks = new HashSet<>();
	private final Firebase firebase;
	private List<T> items = new ArrayList<>();

	public Repository(String firebaseUrl, final Class<T> modelClass) {
		firebase = new Firebase(firebaseUrl);

		firebase.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				List<T> items = new ArrayList<>();
				for (DataSnapshot postSnapshot : snapshot.getChildren()) {
					T restaurant = postSnapshot.getValue(modelClass);
					items.add(restaurant);
				}
				Repository.this.items = items;
				notifyDataChanged();
			}
			@Override
			public void onCancelled(FirebaseError firebaseError) {
				/* Do nothing */
			}
		});
	}

	public void subscribe(Callback<T> listener) {
		synchronized (callbacks) {
			callbacks.add(listener);
		}
		/* Notify about current state to the just subscribed listener */
		listener.onDataChange(items);
	}

	public void unsubscribe(Callback<T> listener) {
		synchronized (callbacks) {
			callbacks.remove(listener);
		}
	}

	public void post(T item) {
		firebase.push().setValue(item);
	}

	public void post(T item, final PostCallback postCallback) {
		firebase.push().setValue(item, new CompletionListener() {
			@Override
			public void onComplete(FirebaseError firebaseError, Firebase firebase) {
				postCallback.onItemPosted();
			}
		});
	}

	public List<T> getItems() {
		return new ArrayList<>(items);
	}

	private void notifyDataChanged() {
		synchronized (callbacks) {
			for (Callback<T> callback : callbacks) {
				callback.onDataChange(items);
			}
		}
	}

	public interface Callback<T> {
		void onDataChange(List<T> items);
	}

	public interface PostCallback {
		void onItemPosted();
	}
}
