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
import com.mediaan.masterclass.teamy.pojo.EventType;
import com.mediaan.masterclass.teamy.storage.EventsStorage;

import java.util.List;

public class EventsListFragment extends Fragment {

    private static final int COLUMN_COUNT = 2;
    private List<Event> events;
    private EventsAdapter rcAdapter;
    private  EventsStorage eventsStorage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_events, container, false);
        final Context context = getContext();
        eventsStorage = new EventsStorage(context);//not sure if this is correct, with a context to a field?
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, COLUMN_COUNT);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.events_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        SetupRecyclerView(view);

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

    @Override
    public void onResume() {
        super.onResume();
        //test adding event
        Event event = new Event();
        event.setTitle("title");
        event.setDescription("description");
        event.setType(EventType.BASKETBALL);
        this.eventsStorage.AddEvent(event);
        this.events = this.eventsStorage.getEvents();
        this.rcAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void SetupRecyclerView(View view){
        final Context context = getContext();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.events_list);

        this.events = eventsStorage.getEvents();
        this.rcAdapter = new EventsAdapter(context, events, onEventClickedListener);
        recyclerView.setAdapter(rcAdapter);
    }

}
