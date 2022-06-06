package com.example.customitemviewtest;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    private ArrayList<DateItem> mItems = new ArrayList<DateItem>();

    public ItemAdapter(Context context, int resource, ArrayList<DateItem> items) {
        mContext = context;
        mItems = items;
        mResource = resource;
    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }

        TextView date = (TextView) convertView.findViewById(R.id.textItem1);
        date.setText(mItems.get(position).mDate);

        TextView schedule = (TextView) convertView.findViewById(R.id.textItem2);
        Point size = new Point();
        mContext.getDisplay().getSize(size);
        schedule.setHeight(size.y/9);
        return convertView;
    }
}
