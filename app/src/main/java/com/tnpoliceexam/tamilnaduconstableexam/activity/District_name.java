package com.tnpoliceexam.tamilnaduconstableexam.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.adapter.Message;
import com.adapter.MessagesAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tnpoliceexam.tamilnaduconstableexam.ConnectionDetector;
import com.tnpoliceexam.tamilnaduconstableexam.ImageLoaderDefintion;
import com.tnpoliceexam.tamilnaduconstableexam.R;
import com.tnpoliceexam.tamilnaduconstableexam.adapter.GridAdapter_re;

import java.util.ArrayList;


/**
 * Created by AswinBalaji on 2018-04-10.
 */

public class District_name extends AppCompatActivity {


    ListView listView;
    MessagesAdapter dbAdp, adp;
    AdView adView;
    ImageView mail;
    ArrayList<Message> messages;
    int position;
    SearchView editsearch;
GridAdapter_re adapter;

    RelativeLayout layout;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.lisrviewactivity );

        adView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd( adRequest );
        ConnectionDetector cd = new ConnectionDetector( this );
        if (!cd.isConnectingToInternet()) {
            adView.setVisibility( View.GONE );
        } else {
            adView.setVisibility( View.VISIBLE );
        }
        adView.setAdListener( new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility( View.VISIBLE );
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad( errorCode );
                adView.setVisibility( View.GONE );
            }

        } );
        mail=(findViewById( R.id.mail ));
        layout=(findViewById( R.id.toolbar ));

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.GONE);
               editsearch.setVisibility(View.VISIBLE);
            }
        } );


        dbAdp = new MessagesAdapter( District_name.this );
        dbAdp.createDatabase();
        dbAdp.open();
        dbAdp.close();
        adp = new MessagesAdapter( District_name.this );
        findViewById( R.id.back ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        } );

        ImageLoaderDefintion.initImageLoader( District_name.this );
        messages = getAllContenttype();
        listView = findViewById( R.id.list_item );
              layoutManager = new LinearLayoutManager(this);
        adapter = new GridAdapter_re( District_name.this, R.layout.list_item, messages );
        listView.setAdapter( adapter );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent( District_name.this, All_detailes.class );
                intent.putExtra( "data", messages.get( i ).getLevel() );
                intent.putExtra( "position", i );
                startActivity( intent );
            }
        } );
        editsearch=(SearchView)findViewById( R.id.searchView1 );
        editsearch.setVisibility(View.GONE);
        editsearch.setQueryHint( "மாவட்டங்களை தேட...." );
        editsearch.setIconifiedByDefault( false );
        editsearch.setSubmitButtonEnabled( true );
        editsearch.setFocusable( false );
        ((EditText)((SearchView)findViewById(R.id.searchView1))
                .findViewById(((SearchView)findViewById(R.id.searchView1))
                        .getContext().getResources().getIdentifier("android:id/search_src_text", null,
                                null))).setHintTextColor(Color.BLACK);
        editsearch.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                editsearch.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter( newText.toString().trim() );

                return true;
            }
        } );



    }
    private ArrayList<Message> getAllContenttype ( ) {

        ArrayList<Message> objlist_msg = new ArrayList<Message>();
        Message obj;
        adp.open();
        Cursor c = adp.getCategories();
        c.moveToFirst();


        for (int i = 0; i < c.getCount(); i++) {
            obj = new Message();
            String district_name = c.getString( c.getColumnIndex( MessagesAdapter.Level  ) );
            int id = c.getInt(c.getColumnIndex(MessagesAdapter.sno));
            String Que = c.getString(c.getColumnIndex(MessagesAdapter.Que));
            String cat = c.getString(c.getColumnIndex(MessagesAdapter.cat));
            String ans = c.getString(c.getColumnIndex(MessagesAdapter.Ans));
            String Level = c.getString(c.getColumnIndex(MessagesAdapter.Level));
            String Opt_1 = c.getString(c.getColumnIndex(MessagesAdapter.Opt_1));
            String Opt_2 = c.getString(c.getColumnIndex(MessagesAdapter.Opt_2));
            String Opt_3 = c.getString(c.getColumnIndex(MessagesAdapter.Opt_3));
            String Ans_q = c.getString(c.getColumnIndex(MessagesAdapter.Ans_q));

            obj.setAns_q(Ans_q);
            obj.setSno(id);
            obj.setAns(ans);
            obj.setQue(Que);
            obj.setCat(cat);
            obj.setLevel(Level);
            obj.setOpt_1(Opt_1);
            obj.setOpt_2(Opt_2);
            obj.setOpt_3(Opt_3);
            obj.setLevel( district_name );
            objlist_msg.add( obj );
            c.moveToNext();
        }

        c.close();
        adp.close();
        return objlist_msg;
    }


}