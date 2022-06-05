package com.example.customitemviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class CalendarApp extends AppCompatActivity implements TimePicker.OnTimeChangedListener {//implements OnMapReadyCallback {
    int year, month, shour, sminute, fhour, fminute;
    String date;
    DBHelper mDBHelper;
    EditText title, place, memo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_app);

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

        Intent getIntent = getIntent();
        year = getIntent.getIntExtra("YEAR", 0);
        month = getIntent.getIntExtra("MONTH", 0);
        date = getIntent.getStringExtra("DATE");

        TimePicker mStartTimePicker = (TimePicker)findViewById(R.id.time_start);
        mStartTimePicker.setOnTimeChangedListener(this);
        shour = mStartTimePicker.getHour();
        sminute = mStartTimePicker.getMinute();

        TimePicker mFinishTimePicker = (TimePicker)findViewById(R.id.time_finish);
        mFinishTimePicker.setOnTimeChangedListener(this);
        fhour = mFinishTimePicker.getHour();
        fminute = mFinishTimePicker.getMinute();

        title = (EditText) findViewById(R.id.title);
        title.setHint(""+year+"년 "+month+"월 "+date+"일");
        //Log.i("hey", "get: "+year+"/"+month+"/"+date);

        place = (EditText) findViewById(R.id.place);

        Button btnSearch = (Button) findViewById(R.id.btn_search);

        Button btnSave = (Button)findViewById((R.id.btn_save));
        Button btnCancel = (Button)findViewById((R.id.btn_cancel));
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnDelete = (Button)findViewById((R.id.btn_delete));

        memo = (EditText) findViewById(R.id.memo);

    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int h, int m) {
            fhour = h;
            fminute = m;
    }
}