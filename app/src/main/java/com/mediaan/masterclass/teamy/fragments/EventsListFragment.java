package com.mediaan.masterclass.teamy.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediaan.masterclass.teamy.EventDetailActivity;
import com.mediaan.masterclass.teamy.R;
import com.mediaan.masterclass.teamy.adapters.EventsAdapter;
import com.mediaan.masterclass.teamy.pojo.Event;
import com.mediaan.masterclass.teamy.storage.EventsStorage;

import java.util.List;

public class EventsListFragment extends Fragment {

    private static final int COLUMN_COUNT = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_events, container, false);
        final Context context = getContext();

        final EventsStorage eventsStorage = new EventsStorage(context);
        final List<Event> events = eventsStorage.getEvents();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, COLUMN_COUNT);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.events_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        final EventsAdapter rcAdapter = new EventsAdapter(context, events, onEventClickedListener);
        recyclerView.setAdapter(rcAdapter);

        return view;
    }

    private final EventsAdapter.OnItemClickListener onEventClickedListener = new EventsAdapter.OnItemClickListener() {
        @Override
        public void onItemClicked(@NonNull final Event event, @NonNull final View view) {
            final Intent intent = new Intent(getActivity(), EventDetailActivity.class);

            intent.putExtra(EventDetailActivity.EXTRA_EVENT, event);
            getActivity().startActivity(intent);
        }
    };
}
