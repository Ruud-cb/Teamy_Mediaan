package com.mediaan.masterclass.teamy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.mediaan.masterclass.teamy.pojo.EventLocation;
import com.mediaan.masterclass.teamy.pojo.EventType;

import com.mediaan.masterclass.teamy.pojo.Event;
import com.mediaan.masterclass.teamy.pojo.EventLocation;
import com.mediaan.masterclass.teamy.pojo.EventOrganiser;
import com.mediaan.masterclass.teamy.pojo.EventType;
import com.mediaan.masterclass.teamy.storage.EventsStorage;
import com.mediaan.masterclass.teamy.storage.UserStorage;
import com.mediaan.masterclass.teamy.utils.DateTimeUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CreateEventActivity extends AppCompatActivity {

    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private String title;
    private String description;
    private EventType eventType;
    private EventLocation location;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    public String getEventTitle() {
        return ((EditText) findViewById(R.id.create_title)).getText().toString();
    }

    public String getDescription() {
        return description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventLocation getLocation() {
        return location;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        UserStorage storage = new UserStorage();

        fromDateEtxt = (EditText) findViewById(R.id.create_begin_date);
        toDateEtxt = (EditText) findViewById(R.id.create_end_date);

        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fromDateEtxt.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, 2017, 4, 12);

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                toDateEtxt.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, 2017, 4, 14);

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("SOCCER");
        spinnerArray.add("TABLETENNIS");
        spinnerArray.add("CYCLING");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.create_event_type);
        sItems.setAdapter(adapter);

        fromDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });

        toDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.show();
            }
        });
    }


    private void CreateEvent() throws ParseException {
        //test adding event
        UserStorage userStorage = new UserStorage();

        Event event = new Event();
        event.setTitle(getEventTitle());
        event.setDescription(getDescription());
        event.setType(getEventType());
        event.setCurrentParticipants(1);
        event.setMinParticipants(4);
        event.setMaxParticipants(10);
        String startDate = fromDateEtxt.getText().toString();
        String endDate = toDateEtxt.getText().toString();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = format.parse(startDate);
        date.setTime(100);
        event.setStart(date);
        Date date2 = format.parse(endDate);
        date2.setTime(100);
        event.setEnd(date2);
        EventOrganiser organiser = userStorage.GetCurrentUser();
        event.setOrganiser(organiser);
        event.setLocation(getLocation());
        EventsStorage storage = new EventsStorage(getBaseContext());
        storage.AddEvent(event);
    }
}
