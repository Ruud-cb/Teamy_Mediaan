package com.mediaan.masterclass.teamy;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    private Toast toast;

    protected void showSnackbar(@NonNull final View view, @NonNull final String text) {
        final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.light_green));
        snackbar.show();
    }

    protected void showToast(@StringRes final int stringId) {
        showToast(getString(stringId));
    }

    protected void showToast(@NonNull final String title) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG);
            getToastBackgroundView(toast).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.light_green));
        } else {
            toast.setText(title);
        }
        toast.show();
    }

    private View getToastBackgroundView(final Toast toast) {
        final View messageView = toast.getView().findViewById(android.R.id.message);
        if (messageView.getBackground() == null && messageView.getParent() != null) {
            return (View) messageView.getParent();
        }
        return messageView;
    }
}
