package com.example.customitemviewtest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String YEAR = "year";
    private static final String MONTH = "month";

    // TODO: Rename and change types of parameters
    private int mYear;
    private int mMonth;
    private String mDate, test;
    private boolean prevSelected = false;
    private TextView clickedView1, clickedView2;
    static ItemAdapter adapter;
    private DBHelper mDBHelper;

    public MonthFragment(int year, int month) {
        mYear = year;
        mMonth = month;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param year Parameter 1.
     * @param month Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthFragment newInstance(int year, int month) {
        MonthFragment fragment = new MonthFragment(year, month);
        Bundle args = new Bundle();
        args.putInt(YEAR, year);
        args.putInt(MONTH, month);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mYear = getArguments().getInt(YEAR);
            mMonth = getArguments().getInt(MONTH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_month, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(""+mYear+"??? "+(mMonth+1)+"???");

        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, 1);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startDay = calendar.get(calendar.DAY_OF_WEEK);
        ArrayList<DateItem> Dates = make_date(startDay, lastDay + startDay - 1);

        adapter = new ItemAdapter(getActivity(), R.layout.item, Dates);

        GridView gridView = (GridView)rootView.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
                if(prevSelected){
                    clickedView1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    clickedView2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                mDate = ((DateItem) adapter.getItem(position)).mDate;
                Toast.makeText(getActivity(), (mYear+"/"+(mMonth+1)+"/"+mDate),
                        Toast.LENGTH_SHORT).show();

                clickedView1 = vClicked.findViewById((R.id.textItem1));
                clickedView1.setBackgroundColor(Color.parseColor("#00FFFF"));
                clickedView2 = vClicked.findViewById((R.id.textItem2));
                clickedView2.setBackgroundColor(Color.parseColor("#00FFFF"));
                prevSelected = true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MainActivity)getActivity(), CalendarApp.class); //?????? ????????? ???????????? ??????
                intent.putExtra("YEAR", mYear);
                intent.putExtra("MONTH", mMonth);
                intent.putExtra("DATE", mDate);
                Log.i("hey", ""+mYear+"/"+mMonth+"/"+mDate);
                startActivity(intent); //???????????? ??????
            }
        });

        return rootView;
    }

    public interface InsertScheduleEvent {
        public void InsertSchedule(int i);
    }
    public ArrayList<DateItem> make_date(int sDay, int dSize){
        ArrayList<DateItem> mDates = new ArrayList<>();
        for(int i=1; i<sDay; i++)
            mDates.add(new DateItem(""));
        for(int i=sDay-1; i<dSize; i++)
            mDates.add(new DateItem("" + (i - sDay + 2)));
        for(int i=dSize; i<42; i++)
            mDates.add(new DateItem(""));

        return mDates;
    }

    public void onStart() {
        super.onStart();
        Log.i("hey", "onStart()"+mYear+mMonth+mDate);
    }

    public void onPause() {
        super.onPause();
        Log.i("hey", "onPause()"+mYear+mMonth+mDate);
    }
    public void onResume() {
        super.onResume();
        String date = ""+mYear+mMonth+mDate;
        Log.i("hey", "onResume(): "+date);
//        mDBHelper.getUsersMySQL("Title", "Date", date);
    }
//        String date = ""+mYear+mMonth+mDate;
//        Log.i("hey", "omResume(): "+date);
//        mDBHelper = new DBHelper(getActivity());
//        mDBHelper.getUsersMySQL("title", date);
//    }
}

