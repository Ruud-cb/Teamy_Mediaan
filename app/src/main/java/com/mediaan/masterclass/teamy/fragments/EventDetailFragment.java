package com.mediaan.masterclass.teamy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mediaan.masterclass.teamy.R;
import com.mediaan.masterclass.teamy.pojo.Event;
import com.mediaan.masterclass.teamy.pojo.EventLocation;
import com.mediaan.masterclass.teamy.pojo.EventOrganiser;
import com.mediaan.masterclass.teamy.utils.CapacityUtils;
import com.mediaan.masterclass.teamy.utils.DateTimeUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class EventDetailFragment extends Fragment {

    private static final int MEDALS_COUNT = 5;
    private static final String EXTRA_EVENT = "extra_name";

    public static EventDetailFragment getNewInstance(@NonNull final Event event) {
        EventDetailFragment simpleDemoFragment = new EventDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_EVENT, event);
        simpleDemoFragment.setArguments(bundle);
        return simpleDemoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_event_details, container, false);
        final Event event = getArguments() != null ? (Event) getArguments().getSerializable(EXTRA_EVENT) : null;

        if (event == null) {
            return null;
        }

        ImageView imgType = (ImageView) view.findViewById(R.id.detail_event_type);
        TextView tvTitle = (TextView) view.findViewById(R.id.detail_event_title);
        TextView tvDescription = (TextView) view.findViewById(R.id.detail_event_description);
        TextView tvTime = (TextView) view.findViewById(R.id.detail_event_time);
        TextView tvDate = (TextView) view.findViewById(R.id.detail_event_date);
        TextView tvLocation = (TextView) view.findViewById(R.id.detail_event_location_name);
        TextView tvLocationDistance = (TextView) view.findViewById(R.id.detail_event_location_distance);
        TextView tvOrganiser = (TextView) view.findViewById(R.id.detail_event_organiser);
        TextView tvCapacityHeadline = (TextView) view.findViewById(R.id.detail_event_capacity_headline);
        TextView tvCapacityCurrent = (TextView) view.findViewById(R.id.detail_event_capacity_current);
        TextView tvCapacityTotal = (TextView) view.findViewById(R.id.detail_event_capacity_total);
        ImageView imgCapacity = (ImageView) view.findViewById(R.id.detail_event_capacity_bar);
        LinearLayout medalsContainer = (LinearLayout) view.findViewById(R.id.detail_event_medal_container);
        Button btnInvite = (Button) view.findViewById(R.id.detail_event_btn_invite);
        Button btnJoin = (Button) view.findViewById(R.id.detail_event_btn_join);

        imgType.setImageResource(event.getType().getIcon());
        tvTitle.setText(event.getTitle());
        tvDescription.setText(event.getDescription());
        tvTime.setText(getString(R.string.detail_time_format, DateTimeUtils.formatToHours(event.getStart()), DateTimeUtils.formatToHours(event.getEnd())));
        tvDate.setText(new SimpleDateFormat(getString(R.string.detail_date_format), Locale.getDefault()).format(event.getStart()));
        final EventLocation eventLocation = event.getLocation();
        tvLocation.setText(eventLocation.getName());
        tvLocationDistance.setText(getString(R.string.detail_location_distance_format, eventLocation.getDistance()));
        final EventOrganiser eventOrganiser = event.getOrganiser();
        tvOrganiser.setText(eventOrganiser.getName());
        tvCapacityHeadline.setText(getString(R.string.detail_capacity_headline_format, event.getMaxParticipants() - event.getCurrentParticipants()));
        tvCapacityCurrent.setText(getString(R.string.detail_capacity_current_format, event.getCurrentParticipants(), event.getMaxParticipants()));
        tvCapacityTotal.setText(getString(R.string.detail_capacity_total_format, event.getMinParticipants(), event.getMaxParticipants()));
        imgCapacity.setImageResource(CapacityUtils.getCapacityDrawable(event, true));

        final int medalCount = eventOrganiser.getScore();
        for (int i = 0; i < MEDALS_COUNT; i ++) {
            inflater.inflate(i < medalCount ? R.layout.medal_color : R.layout.medal_mono, medalsContainer, true);
        }

        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), R.string.detail_invite_friends_clicked, Toast.LENGTH_SHORT).show();
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), R.string.detail_join_clicked, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
