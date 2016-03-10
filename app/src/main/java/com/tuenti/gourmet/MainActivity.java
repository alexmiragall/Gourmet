package com.tuenti.gourmet;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.tuenti.gourmet.models.User;
import com.tuenti.gourmet.repositories.UserRepository;
import com.tuenti.gourmet.startEvent.RestaurantsMapActivity;

public class MainActivity extends AppCompatActivity {

	private static final int RESULT_CODE_SIGN_IN = 1;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;

	@Bind(R.id.main_content)
	CoordinatorLayout mainContent;

	@Bind(R.id.splash)
	View splash;

	private GoogleApiClient googleApiClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		showSplash();

		initAuth();

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			Intent newActivity = new Intent(this, RestaurantsMapActivity.class);
			startActivity(newActivity);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void initAuth() {
		// Configure sign-in to request the user's ID, email address, and basic
		// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();

		// Build a GoogleApiClient with access to the Google Sign-In API and the
		// options specified by gso.
		googleApiClient = new GoogleApiClient.Builder(this)
				.enableAutoManage(this, new OnConnectionFailedListener() {
					@Override
					public void onConnectionFailed(ConnectionResult connectionResult) {
						finish();
					}
				})
				.addApi(Auth.GOOGLE_SIGN_IN_API, gso)
				.build();

		signInSilently();
	}

	private void signInSilently() {
		Auth.GoogleSignInApi.silentSignIn(googleApiClient).setResultCallback(new ResultCallback<GoogleSignInResult>() {
			@Override
			public void onResult(GoogleSignInResult googleSignInResult) {
				if (googleSignInResult.isSuccess()) {
					handleSignInResult(googleSignInResult);
				} else {
					showSignInDialog();
				}
			}
		});
	}

	private void showSignInDialog() {
		Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
		startActivityForResult(signInIntent, RESULT_CODE_SIGN_IN);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
		if (requestCode == RESULT_CODE_SIGN_IN) {
			GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
			handleSignInResult(result);
		}
	}

	private void handleSignInResult(GoogleSignInResult result) {
		if (result.isSuccess()) {
			// Signed in successfully, show authenticated UI.
			hideSplash();
			GoogleSignInAccount signInAccount = result.getSignInAccount();
			Uri photoUrl = signInAccount.getPhotoUrl();
			UserRepository.getInstance().setCurrentUser(
					new User(signInAccount.getDisplayName(), photoUrl != null ? photoUrl.toString() : null));
		} else {
			finish();
		}
	}

	private void hideSplash() {
		splash.setVisibility(View.GONE);
		mainContent.setVisibility(View.VISIBLE);
	}

	private void showSplash() {
		splash.setVisibility(View.VISIBLE);
		mainContent.setVisibility(View.GONE);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		public PlaceholderFragment() {
		}

		/**
		 * Returns a new instance of this fragment for the given section
		 * number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			TextView textView = (TextView) rootView.findViewById(R.id.section_label);
			textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch(position) {
				case 0:
					fragment = new EventsFragment();
					break;
				default:
					fragment = PlaceholderFragment.newInstance(position + 1);
					break;
			}

			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return "Events";
				case 1:
					return "SECTION 2";
				case 2:
					return "SECTION 3";
			}
			return null;
		}
	}
}
