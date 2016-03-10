package com.tuenti.gourmet;

import android.app.Application;
import android.content.Intent;

import com.firebase.client.Firebase;
import com.tuenti.gourmet.service.SubscriptionService;

public class GourmetApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Firebase.setAndroidContext(this);
		startService(new Intent(this, SubscriptionService.class));
	}
}
