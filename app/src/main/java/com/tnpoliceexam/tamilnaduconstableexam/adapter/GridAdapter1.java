package com.tnpoliceexam.tamilnaduconstableexam.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adapter.Message;
import com.tnpoliceexam.tamilnaduconstableexam.R;

import java.util.ArrayList;

public class GridAdapter1 extends BaseAdapter {
    Context context;
    ArrayList<Message> rowItems;
    int layout1;
    public ArrayList<Message> arraylist;

    public GridAdapter1(Context context, int resource, ArrayList<Message> items) {
        this.context = context;
        this.rowItems = items;
        this.layout1 = resource;
        arraylist = new ArrayList<Message>();
        arraylist.addAll(rowItems);
    }

    private class ViewHolder {
        ImageView txtTitle;
        TextView title,title1;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(layout1, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text1);
            holder.title1 = (TextView) convertView.findViewById(R.id.text2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Message rowItem = (Message) getItem(position);

        holder.title.setText(rowItem.getQue());
        holder.title1.setText("விடை : "+rowItem.getAns_q());
       /* Log.i("string",rowItem.getTemple_name());*/
        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

}
