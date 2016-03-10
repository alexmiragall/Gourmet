package com.tuenti.gourmet;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuenti.gourmet.adapters.EventsAdapter;
import com.tuenti.gourmet.models.Event;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class EventsFragment extends Fragment {

	@Bind(R.id.events_recycler)
	public RecyclerView recyclerView;

	private EventsAdapter eventsAdapter;

	private List<Event> events = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_events, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		eventsAdapter = new EventsAdapter(getActivity(), events);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(eventsAdapter);
	}
}