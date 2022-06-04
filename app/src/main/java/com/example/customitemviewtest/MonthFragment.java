package com.example.customitemviewtest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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
    static MyAdapter adapter;

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
        ArrayList<MyItem> Dates = new ArrayList<MyItem>();

        Calendar calendar = Calendar.getInstance();
        int year = mYear;
        int month = mMonth;
        Log.i("hey", "year, month: "+year+", "+month);
        calendar.set(year, month, 1);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startDay = calendar.get(calendar.DAY_OF_WEEK);
        int daySize = lastDay + startDay - 1;
        Log.i("hey", "lastDay, startDay, daySize "+lastDay+", "+startDay+", "+daySize);

        for(int i=1; i<startDay; i++)
            Dates.add(new MyItem(""));
        for(int i=startDay-1; i<daySize; i++)
            Dates.add(new MyItem("" + (i - startDay + 2)));
        for(int i=daySize; i<42; i++)
            Dates.add(new MyItem(""));

        adapter = new MyAdapter(getActivity(), R.layout.item, Dates);

        GridView gridView = (GridView)rootView.findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
                String date = ((MyItem) adapter.getItem(position)).mDate;
                Toast.makeText(getActivity(), (year+"/"+(month+1)+"/"+date),
                        Toast.LENGTH_SHORT).show();
            }
        });

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(""+year+"년 "+(month+1)+"월");
        return rootView;
    }
}