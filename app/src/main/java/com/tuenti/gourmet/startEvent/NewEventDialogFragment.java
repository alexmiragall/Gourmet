package com.tuenti.gourmet.startEvent;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tuenti.gourmet.R;
import com.tuenti.gourmet.models.Event;
import com.tuenti.gourmet.models.Restaurant;
import com.tuenti.gourmet.models.User;
import com.tuenti.gourmet.repositories.EventRepository;
import com.tuenti.gourmet.repositories.Repository.PostCallback;
import com.tuenti.gourmet.repositories.UserRepository;

public class NewEventDialogFragment extends AppCompatDialogFragment {

	private static final String RESTAURANT_PARAM = "com.tuenti.gourmet.startEvent.Restaurant";
	private static final String COLOR_PARAM = "com.tuenti.gourmet.startEvent.MainColor";
	private static final String FRAGMENT_TAG = "com.tuenti.gourmet.startEvent.tag.NewEventDialogFragment";

	@Bind(R.id.event_dialog_title)
	TextView dialogTitle;

	@Bind(R.id.create_event_btn)
	Button createEventBtn;

	@Bind(R.id.event_date_editText)
	TimePicker eventDateEditText;

	@Bind(R.id.eventComment)
	EditText eventCommentEditText;

	@Bind(R.id.mainLayout)
	LinearLayout mainLayout;


	private Restaurant restaurant;
	private int mainColor;

	public static void show(FragmentActivity activity, Restaurant restaurant, int mainColor) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(RESTAURANT_PARAM, restaurant);
		bundle.putInt(COLOR_PARAM, mainColor);

		NewEventDialogFragment newEventDialogFragment = new NewEventDialogFragment();
		newEventDialogFragment.setArguments(bundle);

		newEventDialogFragment.show(activity.getSupportFragmentManager(), FRAGMENT_TAG);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE, 0);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new Dialog(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		loadRestaurantFromArguments();

		View v = inflater.inflate(R.layout.fragment_new_event_dialog, container, false);
		ButterKnife.bind(this, v);

		setUpView();

		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mainLayout.requestFocus();
	}

	private void setUpView() {
		dialogTitle.setText("Create an event for " + restaurant.getName());
		eventDateEditText.setBackgroundColor(mainColor);
		createEventBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				postNewEvent();
			}
		});
	}

	private void postNewEvent() {
		User owner = UserRepository.getInstance().getCurrentUser();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, eventDateEditText.getHour());
		cal.set(Calendar.MINUTE, eventDateEditText.getMinute());
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date eventDate = cal.getTime();

		Event newEvent = new Event(restaurant, eventDate.getTime(), owner, eventCommentEditText.getText().toString());

		EventRepository.getInstance().post(newEvent, new PostCallback() {
			@Override
			public void onItemPosted() {
				Toast.makeText(NewEventDialogFragment.this.getContext(), "You event has been created!", Toast
						.LENGTH_LONG).show();
				NewEventDialogFragment.this.dismiss();
			}
		});
	}

	private void loadRestaurantFromArguments() {
		Bundle arguments = getArguments();
		restaurant = arguments.getParcelable(RESTAURANT_PARAM);
		mainColor = arguments.getInt(COLOR_PARAM);
	}
}
