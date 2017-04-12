package com.mediaan.masterclass.teamy.fragments;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediaan.masterclass.teamy.R;

public class SimpleDemoFragment extends Fragment {

    private static final String EXTRA_NAME = "extra_name";
    private static final String EXTRA_BACKGROUND_COLOR = "extra_background_color";

    public static SimpleDemoFragment getNewInstance(@NonNull final String name, @ColorRes final int backgroundColor) {
        SimpleDemoFragment simpleDemoFragment = new SimpleDemoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME, name);
        bundle.putInt(EXTRA_BACKGROUND_COLOR, backgroundColor);
        simpleDemoFragment.setArguments(bundle);
        return simpleDemoFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final TextView textView = (TextView) inflater.inflate(R.layout.demo_fragment, container, false);

        final Bundle arguments = getArguments();
        if (arguments != null) {
            textView.setText(arguments.getString(EXTRA_NAME, "UNKNOWN"));
            textView.setBackgroundColor(ContextCompat.getColor(getContext(), arguments.getInt(EXTRA_BACKGROUND_COLOR, R.color.dark_green)));
        }

        return textView;
    }
}