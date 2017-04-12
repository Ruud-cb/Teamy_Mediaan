package com.mediaan.masterclass.teamy;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mediaan.masterclass.teamy.pojo.Event;
import com.mediaan.masterclass.teamy.pojo.EventLocation;
import com.mediaan.masterclass.teamy.pojo.EventOrganiser;
import com.mediaan.masterclass.teamy.utils.CapacityUtils;
import com.mediaan.masterclass.teamy.utils.DateTimeUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class EventDetailActivity extends BaseActivity {

    private static final int MEDALS_COUNT = 5;

    public static final String EXTRA_EVENT = "extra_name";

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before);
        toolbar.setNavigationContentDescription(R.string.action_back);
        setSupportActionBar(toolbar);

        if (getIntent() == null || !getIntent().hasExtra(EXTRA_EVENT)) {
            throw new RuntimeException("No event passed");
        }

        final Event event = (Event) getIntent().getSerializableExtra(EXTRA_EVENT);

        getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + event.getTitle());

        ImageView imgType = (ImageView) findViewById(R.id.detail_event_type);
        TextView tvTitle = (TextView) findViewById(R.id.detail_event_title);
        TextView tvDescription = (TextView) findViewById(R.id.detail_event_description);
        TextView tvTime = (TextView) findViewById(R.id.detail_event_time);
        TextView tvDate = (TextView) findViewById(R.id.detail_event_date);
        TextView tvLocation = (TextView) findViewById(R.id.detail_event_location_name);
        TextView tvLocationDistance = (TextView) findViewById(R.id.detail_event_location_distance);
        TextView tvOrganiser = (TextView) findViewById(R.id.detail_event_organiser);
        TextView tvCapacityHeadline = (TextView) findViewById(R.id.detail_event_capacity_headline);
        TextView tvCapacityCurrent = (TextView) findViewById(R.id.detail_event_capacity_current);
        TextView tvCapacityTotal = (TextView) findViewById(R.id.detail_event_capacity_total);
        ImageView imgCapacity = (ImageView) findViewById(R.id.detail_event_capacity_bar);
        LinearLayout medalsContainer = (LinearLayout) findViewById(R.id.detail_event_medal_container);
        Button btnInvite = (Button) findViewById(R.id.detail_event_btn_invite);
        Button btnJoin = (Button) findViewById(R.id.detail_event_btn_join);

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
        final LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        for (int i = 0; i < MEDALS_COUNT; i++) {
            inflater.inflate(i < medalCount ? R.layout.medal_color : R.layout.medal_mono, medalsContainer, true);
        }

        btnInvite.setOnClickListener(onInviteClicked);
        btnJoin.setOnClickListener(onJoinClicked);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return true;
    }

    private View.OnClickListener onInviteClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showSnackbar(view, getString(R.string.detail_invite_friends_clicked));
        }
    };

    private View.OnClickListener onJoinClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showSnackbar(view, getString(R.string.detail_join_clicked));
        }
    };
}
