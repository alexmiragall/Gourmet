package com.tuenti.gourmet.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.tuenti.gourmet.models.Event;
import com.tuenti.gourmet.repositories.EventRepository;
import com.tuenti.gourmet.repositories.Repository;
import com.tuenti.gourmet.repositories.SubscriptionRepository;

public class SubscriptionService extends Service implements Repository.Callback<Event> {

	private NotificationManager notificationManager;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		EventRepository.getInstance().subscribe(this);

		SubscriptionRepository.getInstance().initialize(this);

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		EventRepository.getInstance().unsubscribe(this);
	}

	@Override
	public void onDataChange(List<Event> events) {
		for (final Event event : events) {
			if (SubscriptionRepository.getInstance().isSubscribedTo(event.getRestaurant().getName())) {

				new Thread(new Runnable() {
					@Override
					public void run() {

							Notification.Builder notificationBuilder =
									new Notification.Builder(SubscriptionService.this)
											.setContentTitle(getNotificationTitle(event))
											.setContentText(getNotificationText(event))
											.setSmallIcon(android.R.drawable.ic_notification_overlay);

							if (event.getOwner().getPhotoUrl() != null) {
								try {
									Bitmap largeIcon = Picasso.with(SubscriptionService.this).load(event.getOwner()
											.getPhotoUrl())
											.get();
									notificationBuilder.setLargeIcon(largeIcon);
								} catch (IOException e) {}
							}

							notificationManager.notify((new Random(System.currentTimeMillis())).nextInt(),
									notificationBuilder.build());
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

	private String getNotificationTitle(Event event) {
		return event.getOwner().getName() + " va al " + event.getRestaurant().getName();
	}

	private String getNotificationText(Event event) {
		return SimpleDateFormat.getInstance().format(new Date(event.getDate()));
	}
}
