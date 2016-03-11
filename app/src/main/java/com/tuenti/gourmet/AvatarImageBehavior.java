package com.tuenti.gourmet;


import de.hdodenhof.circleimageview.CircleImageView;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
@SuppressWarnings("unused")
public class AvatarImageBehavior extends CoordinatorLayout.Behavior<CircleImageView> {

	private final static float MIN_AVATAR_PERCENTAGE_SIZE   = 0.3f;
	private final static int EXTRA_FINAL_AVATAR_PADDING     = 80;

	private final static String TAG = "behavior";
	private final Context mContext;
	private float mAvatarMaxSize;

	private float mFinalLeftAvatarPadding;
	private float mStartPosition;
	private int mStartXPosition;
	private float mStartToolbarPosition;

	public AvatarImageBehavior(Context context, AttributeSet attrs) {
		mContext = context;
		init();

		mFinalLeftAvatarPadding = context.getResources().getDimension(
				R.dimen.spacing_normal);
	}

	private void init() {
		bindDimensions();
	}

	private void bindDimensions() {
		mAvatarMaxSize = mContext.getResources().getDimension(R.dimen.image_width);
	}

	private int mStartYPosition;

	private int mFinalYPosition;
	private int finalHeight;
	private int mStartHeight;
	private int mFinalXPosition;
	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
		return dependency instanceof AppBarLayout;
	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
		maybeInitProperties(child, dependency);
		final int maxScrollDistance = (int) (mStartToolbarPosition - getStatusBarHeight());
		View toolbar = getToolbar((ViewGroup) dependency);
		float expandedPercentageFactor = getY(toolbar) / maxScrollDistance;

		float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
				* (1f - expandedPercentageFactor)) + (child.getHeight()/2);

		float distanceXToSubtract = ((mStartXPosition - mFinalXPosition)
				* (1f - expandedPercentageFactor)) + (child.getWidth()/2);

		float heightToSubtract = ((mStartHeight - finalHeight) * (1f - expandedPercentageFactor));

		child.setY(mStartYPosition - distanceYToSubtract);
		child.setX(mStartXPosition - distanceXToSubtract);

		int proportionalAvatarSize = (int) (mAvatarMaxSize * (expandedPercentageFactor));

		CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
		lp.width = (int) (mStartHeight - heightToSubtract);
		lp.height = (int) (mStartHeight - heightToSubtract);
		child.setLayoutParams(lp);
		return true;
	}

	private int getY(View view) {
		final int maxScrollDistance = (int) (mStartToolbarPosition - getStatusBarHeight());
		int[] pos = new int[2];
		return pos[1];
	}
	private int getX(View view) {
		final int maxScrollDistance = (int) (mStartToolbarPosition - getStatusBarHeight());
		int[] pos = new int[2];
		return pos[0];
	}

	private void maybeInitProperties(CircleImageView child, View dependency) {
		View toolbar = getToolbar((ViewGroup) dependency);

		if (mStartYPosition == 0)
			mStartYPosition = (int) ((getY(toolbar)) + (dependency.getHeight() / 2));

		if (mFinalYPosition == 0)
			mFinalYPosition = (toolbar.getHeight() / 4);

		if (mStartHeight == 0)
			mStartHeight = child.getHeight();

		if (finalHeight == 0)
			finalHeight = mContext.getResources().getDimensionPixelOffset(R.dimen.image_final_width);

		if (mStartXPosition == 0)
			mStartXPosition = (int) (child.getX() + (child.getWidth() / 2));

		if (mFinalXPosition == 0)
			mFinalXPosition = mContext.getResources().getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + (finalHeight / 2);

		if (mStartToolbarPosition == 0)
			mStartToolbarPosition = getY(toolbar) + (toolbar.getHeight()/4);
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