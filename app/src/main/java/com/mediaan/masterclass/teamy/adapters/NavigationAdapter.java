package com.mediaan.masterclass.teamy.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mediaan.masterclass.teamy.R;
import com.mediaan.masterclass.teamy.fragments.EventsListFragment;
import com.mediaan.masterclass.teamy.fragments.SimpleDemoFragment;

public class NavigationAdapter extends FragmentStatePagerAdapter {

    private static final int TAB_COUNT = 3;
    public static final int TAB_EVENTS = 0;
    public static final int TAB_MY_EVENTS = 1;
    public static final int TAB_MY_MESSAGES = 2;

    private EventsListFragment eventsListFragment;
    private SimpleDemoFragment myEventsFragment;
    private SimpleDemoFragment myMessagesFragment;

    public NavigationAdapter(@NonNull final FragmentManager fragmentManager) {
        super(fragmentManager);

        eventsListFragment = new EventsListFragment();
        myEventsFragment = SimpleDemoFragment.getNewInstance("MY EVENTS", R.color.dark_green);
        myMessagesFragment = SimpleDemoFragment.getNewInstance("MY MESSAGES", R.color.dark_grey);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case TAB_EVENTS:
                return eventsListFragment;
            case TAB_MY_EVENTS:
                return myEventsFragment;
            case TAB_MY_MESSAGES:
                return myMessagesFragment;
            default:
                throw new RuntimeException("Unknown tab position in: " + NavigationAdapter.class.getName());
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
