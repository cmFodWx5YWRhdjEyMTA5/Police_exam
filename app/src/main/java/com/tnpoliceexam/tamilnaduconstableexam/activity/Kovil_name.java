package com.tnpoliceexam.tamilnaduconstableexam.activity;

/**
 * Created by G tech on 4/12/2018.
 */

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.adapter.Message;
import com.adapter.MessagesAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tnpoliceexam.tamilnaduconstableexam.ConnectionDetector;
import com.tnpoliceexam.tamilnaduconstableexam.ImageLoaderDefintion;
import com.tnpoliceexam.tamilnaduconstableexam.R;
import com.tnpoliceexam.tamilnaduconstableexam.adapter.GridAdapter1;

import java.util.ArrayList;

/**
 * Created by AswinBalaji on 2018-04-10.
 */

public class Kovil_name extends AppCompatActivity {


    ListView listView;
    MessagesAdapter dbAdp, adp;
    AdView adView;GridAdapter1 adapter;
    ArrayList<Message> messages;
    SearchView editsearch;
ImageView mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisrviewactivity);

        editsearch=(SearchView)findViewById( R.id.searchView1 );
        editsearch.setVisibility(View.GONE);

        mail=findViewById( R.id.mail );
        mail.setVisibility(View.GONE);

        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        ConnectionDetector cd = new ConnectionDetector(this);
        if (!cd.isConnectingToInternet()) {
            adView.setVisibility(View.GONE);
        } else {
            adView.setVisibility(View.VISIBLE);
        }
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                adView.setVisibility(View.GONE);
            }
        });

        dbAdp = new MessagesAdapter(Kovil_name.this);
        dbAdp.createDatabase();
        dbAdp.open();
        dbAdp.close();
        adp = new MessagesAdapter(Kovil_name.this);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageLoaderDefintion.initImageLoader(Kovil_name.this);

        messages = getAllContenttype(getIntent().getStringExtra( "data" ));
        listView = findViewById(R.id.list_item);
        adapter = new GridAdapter1(Kovil_name.this, R.layout.list_item_1, messages);
        listView.setAdapter(adapter);


    }

    private ArrayList<Message> getAllContenttype(String type) {

        ArrayList<Message> objlist_msg = new ArrayList<Message>();
        Message obj;
        adp.open();
        Cursor c = adp.Kovil_name(type);
        c.moveToFirst();


        for (int i = 0; i < c.getCount(); i++) {
            obj = new Message();
            String id = c.getString(c.getColumnIndex(MessagesAdapter.Que));
            String id1 = c.getString(c.getColumnIndex(MessagesAdapter.Ans_q));

            obj.setQue(id);
            obj.setAns_q(id1);
            objlist_msg.add(obj);
            c.moveToNext();
        }

        c.close();
        adp.close();
        return objlist_msg;
    }
}
