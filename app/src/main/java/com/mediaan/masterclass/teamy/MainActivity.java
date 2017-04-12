package com.mediaan.masterclass.teamy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mediaan.masterclass.teamy.adapters.NavigationAdapter;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationContentDescription(R.string.action_menu);
        setSupportActionBar(toolbar);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);
        final NavigationAdapter navigationAdapter = new NavigationAdapter(getSupportFragmentManager());
        viewPager.setAdapter(navigationAdapter);
        viewPager.setOffscreenPageLimit(navigationAdapter.getCount() - 1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(NavigationAdapter.TAB_EVENTS).setIcon(R.drawable.ic_tab_calendar);
        tabLayout.getTabAt(NavigationAdapter.TAB_MY_EVENTS).setIcon(R.drawable.ic_tab_my_events);
        tabLayout.getTabAt(NavigationAdapter.TAB_MY_MESSAGES).setIcon(R.drawable.ic_tab_messages);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(onFabClicked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            showSnackbar(toolbar, getString(R.string.action_not_implemented, toolbar.getNavigationContentDescription()));
        } else {
            showSnackbar(toolbar, getString(R.string.action_not_implemented, item.getTitle()));
        }
        return true;
    }

    private View.OnClickListener onFabClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // TODO: Implement a new screen to add an event
            showSnackbar(view, getString(R.string.hint_new_event));
        }
    };
}
