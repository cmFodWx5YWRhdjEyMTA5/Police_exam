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
import java.util.Locale;


public class GridAdapter_re extends BaseAdapter {
    Context context;
    ArrayList<Message> rowItems;
    int layout;
    public ArrayList<Message> arraylist;


    public GridAdapter_re(Context context, int resource, ArrayList<Message> items) {
        this.context = context;
        this.rowItems = items;
        this.layout = resource;
        arraylist = new ArrayList<Message>();
        arraylist.addAll(rowItems);

    }


    private class ViewHolder {
        ImageView txtTitle;
        TextView title;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Message rowItem = (Message) getItem(position);

        holder.title.setText(rowItem.getLevel());
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

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        rowItems.clear();
        if (charText.length() == 0) {
            rowItems.addAll(arraylist);

        } else {
            for (Message postDetail : arraylist) {
                if (charText.length() != 0 && postDetail.getLevel().toLowerCase(Locale.getDefault()).contains(charText)) {
                    rowItems.add(postDetail);
                }
            }
        }
        notifyDataSetChanged();
    }


}
