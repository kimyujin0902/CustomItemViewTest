package com.example.customitemviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CalendarApp extends AppCompatActivity implements TimePicker.OnTimeChangedListener  {//implements OnMapReadyCallback {
    final static String TAG="SQLITEDBTEST";
    int year, month, shour, sminute, fhour, fminute;
    String date;
    DBHelper mDBHelper;
    EditText mId, mTitle, mPlace, mMemo;
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

        mDBHelper = new DBHelper(this);

        TimePicker mStartTimePicker = (TimePicker)findViewById(R.id.time_start);
        mStartTimePicker.setOnTimeChangedListener(this);
        shour = mStartTimePicker.getHour();
        sminute = mStartTimePicker.getMinute();

        TimePicker mFinishTimePicker = (TimePicker)findViewById(R.id.time_finish);
        mFinishTimePicker.setOnTimeChangedListener(this);
        fhour = mFinishTimePicker.getHour();
        fminute = mFinishTimePicker.getMinute();

        mId = (EditText) findViewById(R.id._id);

        mTitle = (EditText) findViewById(R.id.title);
        mTitle.setHint(""+year+"년 "+month+"월 "+date+"일");
        //Log.i("hey", "get: "+year+"/"+month+"/"+date);

        mPlace = (EditText) findViewById(R.id.place);

        Button btnSearch = (Button) findViewById(R.id.btn_search);

        Button btnSave = (Button)findViewById((R.id.btn_save));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();

//                viewAllToTextView();
//                viewAllToListView();
                finish();
            }
        });

        Button btnCancel = (Button)findViewById((R.id.btn_cancel));
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnDelete = (Button)findViewById((R.id.btn_delete));

        mMemo = (EditText) findViewById(R.id.memo);

    }
    public interface InsertScheduleEvent {
        public void InsertSchedule(int i);
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int h, int m) {
            fhour = h;
            fminute = m;
    }

//    public void onMapReady(GoogleMap googleMap) {
//        LatLng Myhome = new LatLng(37.623134, 127.058886);
//        googleMap.addMarker(new MarkerOptions().position(Myhome).title("한성대학교"));
//        // move the camera
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Myhome));
//    }

    private void deleteRecord() {
        EditText _id = (EditText) findViewById(R.id._id);
        mDBHelper.deleteUserBySQL(_id.getText().toString());
    }

    private void insertRecord() {

        mDBHelper.insertUserBySQL(mTitle.getText().toString(), mPlace.getText().toString(), mMemo.getText().toString());
    }

}