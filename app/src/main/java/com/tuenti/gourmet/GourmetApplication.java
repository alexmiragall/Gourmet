package com.tuenti.gourmet;

import android.app.Application;

import com.firebase.client.Firebase;

public class GourmetApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Firebase.setAndroidContext(this);
	}
}
