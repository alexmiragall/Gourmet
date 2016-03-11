package com.tuenti.gourmet;

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

import com.tuenti.gourmet.adapters.SubscriptionsAdapter;
import com.tuenti.gourmet.models.Subscription;

/**
 * Copyright (c) Tuenti Technologies. All rights reserved.
 */
public class SubscriptionsFragment extends Fragment implements SubscriptionsListPresenter.View {

	@Bind(R.id.events_recycler)
	protected RecyclerView recyclerView;

	private SubscriptionsListPresenter presenter;
	private SubscriptionsAdapter subscriptionsAdapter;

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
		presenter = new SubscriptionsListPresenter();
		presenter.init(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		presenter.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		presenter.onPause();
	}

	@Override
	public void onSubscriptionsChanged(List<Subscription> subscriptions) {
		if (subscriptionsAdapter == null)  {
			subscriptionsAdapter = new SubscriptionsAdapter(getActivity(), subscriptions);
			recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
			recyclerView.setAdapter(subscriptionsAdapter);
		} else {
			subscriptionsAdapter.update(subscriptions);
		}
	}
}
