package com.mediaan.masterclass.teamy;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.mediaan.masterclass.teamy.pojo.Event;
import com.mediaan.masterclass.teamy.pojo.EventLocation;
import com.mediaan.masterclass.teamy.pojo.EventOrganiser;
import com.mediaan.masterclass.teamy.pojo.EventType;
import com.mediaan.masterclass.teamy.storage.UserStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {

    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

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


    private void CreateEvent(){
        //test adding event
        Event event = new Event();
        event.setTitle("title");
        event.setDescription("description");
        event.setType(EventType.BASKETBALL);
        event.setCurrentParticipants(1);
        Date date = new Date();
        date.setTime(100);
        event.setStart(date);
        date.setTime(200);
        event.setEnd(date);
        EventOrganiser organiser = new EventOrganiser();
        organiser.setName("Ruud");
        organiser.setScore(99);
        event.setOrganiser(organiser);
        EventLocation location = new EventLocation();
        location.setName("Mediaan Office");
        location.setDistance(0.1);
        event.setLocation(location);
    }
}
