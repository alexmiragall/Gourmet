package com.tuenti.gourmet;


import de.hdodenhof.circleimageview.CircleImageView;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
@SuppressWarnings("unused")
public class TitleBehavior extends CoordinatorLayout.Behavior<View> {

	private final static float MIN_AVATAR_PERCENTAGE_SIZE   = 0.3f;

	private final static String TAG = "behavior";
	private final Context mContext;


	public TitleBehavior(Context context, AttributeSet attrs) {
		mContext = context;
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
		return dependency instanceof AppBarLayout;
	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
		final int maxScrollDistance =  mContext.getResources().getDimensionPixelSize(R.dimen.max_height_main_action_bar) -  mContext
				.getResources().getDimensionPixelSize(R.dimen.min_height_main_action_bar);
		View toolbar = getToolbar((ViewGroup) dependency);
		View view = ((ViewGroup)((ViewGroup) dependency).getChildAt(0)).getChildAt(3);
		View viewTarget = ((ViewGroup)((ViewGroup)((ViewGroup) dependency).getChildAt(0)).getChildAt(2)).getChildAt(0);
		float expandedPercentageFactor = getY(view) / (float)
				maxScrollDistance;
		Log.d("sadsadasdasd", "expandedPercentageFactor: " + expandedPercentageFactor + "\n");
		Log.d("sadsadasdasd", "getY(view): " + getY(view));
		viewTarget.setScaleX(0.3f + expandedPercentageFactor);
		viewTarget.setScaleY(0.3f + expandedPercentageFactor);

		return true;
	}

	private int getY(View view) {
		int[] pos = new int[2];
		view.getLocationOnScreen(pos);
		return pos[1];
	}
	private int getX(View view) {
		int[] pos = new int[2];
		view.getLocationOnScreen(pos);
		return pos[0];
	}

	private View getToolbar(ViewGroup dependency) {
		ViewGroup viewCoordinator = (ViewGroup) dependency.getChildAt(0);
		return viewCoordinator.getChildAt(1);
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");

		if (resourceId > 0) {
			result = mContext.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}