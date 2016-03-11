package com.tuenti.gourmet.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.tuenti.gourmet.MainActivity;
import com.tuenti.gourmet.R;
import com.tuenti.gourmet.models.Event;
import com.tuenti.gourmet.repositories.EventRepository;
import com.tuenti.gourmet.repositories.Repository;
import com.tuenti.gourmet.repositories.SubscriptionRepository;
import com.tuenti.gourmet.repositories.UserRepository;

public class SubscriptionService extends Service implements Repository.Callback<Event> {

	private NotificationManager notificationManager;
	private List<Event> alreadyNotifiedEvents = new ArrayList<>();

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		EventRepository.getInstance().subscribe(this);
		SubscriptionRepository.getInstance().initialize(this);
		// If we get killed, after returning from here, restart
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		EventRepository.getInstance().unsubscribe(this);
	}

	@Override
	public void onDataChange(List<Event> events) {
		for (final Event event : events) {
			if (SubscriptionRepository.getInstance().isSubscribedTo(event.getRestaurant().getName()) &&
					!isEventAlreadyNotified(event) &&
					!isFromMyself(event) &&
					!eventHasExpired(event)) {

				alreadyNotifiedEvents.add(event);

				new Thread(new Runnable() {
					@Override
					public void run() {

							Notification.Builder notificationBuilder =
									new Notification.Builder(SubscriptionService.this)
											.setContentTitle(getNotificationTitle(event))
											.setContentText(getNotificationText(event))
											.setSmallIcon(R.drawable.notification_icon);

							if (event.getOwner().getPhotoUrl() != null) {
								try {
									Bitmap largeIcon = Picasso.with(SubscriptionService.this)
											.load(event.getRestaurant().getPhoto())
											.get();
									notificationBuilder.setLargeIcon(largeIcon);
								} catch (IOException e) {}
							}

						Intent startMainActivity = new Intent(SubscriptionService.this, MainActivity.class);
						startMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

						PendingIntent pendingIntent = PendingIntent
								.getActivity(SubscriptionService.this, 0, startMainActivity, 0);
						notificationBuilder.setContentIntent(pendingIntent);
						notificationBuilder.setAutoCancel(true);

						notificationManager.notify(event.hashCode(), notificationBuilder.build());
					}
				}).start();
			}
		}
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private boolean isFromMyself(Event event) {
		if (event.getOwner() == null || UserRepository.getInstance().getCurrentUser() == null) {
			return false;
		}

		return event.getOwner().getName().equals(UserRepository.getInstance().getCurrentUser().getName());
	}

	private boolean eventHasExpired(Event event) {
		return System.currentTimeMillis() < event.getDate();
	}

	private boolean isEventAlreadyNotified(Event event) {
		for (Event notifiedEvent : alreadyNotifiedEvents) {
			if (event.equals(notifiedEvent)) {
				return true;
			}
		}
		return false;
	}

	private String getNotificationTitle(Event event) {
		return event.getOwner().getName().split(" ")[0] + " quiere ir al " + event.getRestaurant().getName();
	}

	private String getNotificationText(Event event) {
		return SimpleDateFormat.getInstance().format(new Date(event.getDate()));
	}
}
