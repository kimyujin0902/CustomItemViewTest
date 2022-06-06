package com.example.customitemviewtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CalendarApp extends AppCompatActivity implements TimePicker.OnTimeChangedListener  {//implements OnMapReadyCallback {
    final static String TAG="SQLITEDBTEST";
    int mYear, mMonth, sHour, sMinute, fHour, fMinute;
    String mDate, DATE;
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
        mYear = getIntent.getIntExtra("YEAR", 0);
        mMonth = getIntent.getIntExtra("MONTH", 0);
        mDate = getIntent.getStringExtra("DATE");
        DATE = ""+mYear+mMonth+mDate;
        mDBHelper = new DBHelper(this);

        TimePicker mStartTimePicker = (TimePicker)findViewById(R.id.time_start);
        mStartTimePicker.setOnTimeChangedListener(this);
        sHour = mStartTimePicker.getHour();
        sMinute = mStartTimePicker.getMinute();

        TimePicker mFinishTimePicker = (TimePicker)findViewById(R.id.time_finish);
        mFinishTimePicker.setOnTimeChangedListener(this);
        fHour = mFinishTimePicker.getHour();
        fMinute = mFinishTimePicker.getMinute();

        mId = (EditText) findViewById(R.id._id);

        mTitle = (EditText) findViewById(R.id.title);
        mTitle.setHint(""+ mYear +"년 "+(mMonth +1)+"월 "+ mDate +"일");
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

    @Override
    public void onTimeChanged(TimePicker timePicker, int h, int m) {
            fHour = h;
            fMinute = m;
    }

//    public void onMapReady(GoogleMap googleMap) {
//        LatLng Myhome = new LatLng(37.623134, 127.058886);
//        googleMap.addMarker(new MarkerOptions().position(Myhome).title("한성대학교"));
//        // move the camera
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Myhome));
//    }

    private void deleteRecord() {
        Log.i("SQL", "deleteRecord()"+DATE);
        mDBHelper.deleteUserBySQL(DATE);
    }

    private void insertRecord() {
        String mDate = ""+ mYear + mMonth + this.mDate;
        mDBHelper.insertUserBySQL(mTitle.getText().toString(), mPlace.getText().toString(), mMemo.getText().toString(), mDate);
    }

    public void OnClickHandler(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DELETE").setMessage("일정을 삭제합니다");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteRecord();
                finish();
            }
        });
        builder.setNegativeButton("취소", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}