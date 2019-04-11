package com.tnpoliceexam.tamilnaduconstableexam.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.tnpoliceexam.tamilnaduconstableexam.R;
import com.tnpoliceexam.tamilnaduconstableexam.RecyclerViewClickListener;
import com.tnpoliceexam.tamilnaduconstableexam.database.DatabaseHandler;
import com.adapter.Message;
import com.adapter.MessagesAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;



public class All_detailes extends AppCompatActivity {

    MessagesAdapter dbAdp;


    AdView adView;

    ListAdapter adapter;

    ImageView Back;
    RecyclerView catagory_list;

    ArrayList<Message> word;
    ArrayList<Message> subword;
int position;
    Message message;
    MessagesAdapter adp;
    DatabaseHandler db;
    String  utal;
    String Type;
    private AdLoader adLoader;
    private List<UnifiedNativeAd> mNativeAds = new ArrayList<>();
    private List<Object> dataCombined;

    private void loadNativeAds(final ArrayList<Message> word) {
        AdLoader.Builder builder = new AdLoader.Builder(All_detailes.this,
                getResources().getString(R.string.native_ad));
        adLoader = builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                mNativeAds.add(unifiedNativeAd);
                setAdapter(word);

            }
        }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        Log.e("MainActivity", "The previous native ad failed to load. Attempting to"
                                + " load another.");
                        setAdapter(word);

                    }
                }).withNativeAdOptions(new NativeAdOptions.Builder()
                .build()).build();
        adLoader.loadAds(new AdRequest.Builder().build(), 1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisrviewactivity_re);
//      ((TextView) findViewById(R.id.title)).setText("நோய் உபாதைகள்");
        dataCombined = new ArrayList<>();

        message = new Message();
        utal = getIntent().getStringExtra("data");

        initUIControls();
        adp = new MessagesAdapter(All_detailes.this);

        db = new DatabaseHandler(All_detailes.this);

        Back = findViewById(R.id.back);


        message = new Message();

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        word = getWord(getIntent().getStringExtra( "data" ));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        catagory_list.setLayoutManager(mLayoutManager);
        catagory_list.setItemAnimator(new DefaultItemAnimator());
        loadNativeAds(word);

        adapter = new ListAdapter(All_detailes.this, dataCombined);
        catagory_list.setAdapter(adapter);

    }
    private void setAdapter(ArrayList<Message> dataLists) {
        dataCombined.clear();
        for (int i = 0; i < dataLists.size(); i++) {
            if ((i % 6 == 0 && i != 0)) {
                for (UnifiedNativeAd ad : mNativeAds) {
                    dataCombined.add(i, ad);
                }
            } else if (i == 2) {
                for (UnifiedNativeAd ad : mNativeAds) {
                    dataCombined.add(i, ad);
                }
            }
            dataCombined.add(dataLists.get(i));
        }

        adapter = new ListAdapter(All_detailes.this, dataCombined);
        AnimationAdapter adapterAnim = new ScaleInAnimationAdapter(adapter);
        adapterAnim.setFirstOnly(true);
        adapterAnim.setDuration(500);
        adapterAnim.setInterpolator(new OvershootInterpolator(.4f));
        catagory_list.setAdapter(adapterAnim);
    }

    public void initUIControls() {
        catagory_list = findViewById(R.id.list_item);
        word = new ArrayList<Message>();
        subword = new ArrayList<Message>();
    }

    public ArrayList<Message> getWord(String type) {
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

    public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private final int AD_VIEW_TYPE = 2;
        private final int VIEW_ITEM = 1;

        private List<Object> objlist;
        private RecyclerViewClickListener recyclerViewClickListener;

        private Context context;



        public ListAdapter(Context context, List<Object> arraylist) {
            this.context = context;
            this.objlist = arraylist;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case AD_VIEW_TYPE:
                    View unifiedNativeLayoutView = LayoutInflater.from(
                            parent.getContext()).inflate(R.layout.ad_unified,
                            parent, false);
                    return new UnifiedNativeAdViewHolder(unifiedNativeLayoutView);
                default:
                    View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1,
                            parent, false);
                    return new MyViewHolder(itemView, recyclerViewClickListener);
            }
        }


        @SuppressLint("NewApi")
        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            int viewType = getItemViewType(position);
            switch (viewType) {
                case AD_VIEW_TYPE:
                    UnifiedNativeAd nativeAd = (UnifiedNativeAd) objlist.get(position);
                    populateNativeAdView(nativeAd, ((UnifiedNativeAdViewHolder) holder).getAdView());
                    break;

                default:
                    final Message dataList = (Message) objlist.get(position);
                    ((MyViewHolder)holder).title.setText(dataList.getQue());
                    ((MyViewHolder)holder).title1.setText("விடை : "+dataList.getAns_q());


            }
        }


        @Override
        public int getItemViewType(int position) {
            Object recyclerViewItem = objlist.get(position);
            if (recyclerViewItem instanceof Message) {
                return VIEW_ITEM;
            }
            return AD_VIEW_TYPE;
        }

        private void populateNativeAdView(UnifiedNativeAd nativeAd,
                                          UnifiedNativeAdView adView) {
            // Some assets are guaranteed to be in every UnifiedNativeAd.
            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

            // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
            // check before trying to display them.
            NativeAd.Image icon = nativeAd.getIcon();

            if (icon == null) {
                adView.getIconView().setVisibility(View.INVISIBLE);
            } else {
                ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
                adView.getIconView().setVisibility(View.VISIBLE);
            }

            if (nativeAd.getStarRating() == null) {
                adView.getStarRatingView().setVisibility(View.INVISIBLE);
            } else {
                ((RatingBar) adView.getStarRatingView())
                        .setRating(nativeAd.getStarRating().floatValue());
                adView.getStarRatingView().setVisibility(View.VISIBLE);
            }

            if (nativeAd.getAdvertiser() == null) {
                adView.getAdvertiserView().setVisibility(View.INVISIBLE);
            } else {
                ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
                adView.getAdvertiserView().setVisibility(View.VISIBLE);
            }
            adView.setNativeAd(nativeAd);

        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return objlist.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView title,title1;


            private MyViewHolder(View view, RecyclerViewClickListener listener) {
                super(view);
                recyclerViewClickListener = listener;
                view.setOnClickListener(this);

                title = (TextView) view.findViewById(R.id.text1);
                title1 = (TextView) view.findViewById(R.id.text2);

            }

            @Override
            public void onClick(View view) {
//             recyclerViewClickListener.onClick(getAdapterPosition());
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
