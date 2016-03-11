package com.tuenti.gourmet.adapters;

import butterknife.Bind;
import butterknife.ButterKnife;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuenti.gourmet.R;

/**
 * Created by gmerino on 11/03/16.
 */
public class SubscriptionViewHolder extends RecyclerView.ViewHolder {
	@Bind(R.id.image)
	ImageView imageView;
	@Bind(R.id.title)
	TextView tvTitle;
	@Bind(R.id.subscribe)
	CheckBox subscribe;


	public SubscriptionViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}
}
