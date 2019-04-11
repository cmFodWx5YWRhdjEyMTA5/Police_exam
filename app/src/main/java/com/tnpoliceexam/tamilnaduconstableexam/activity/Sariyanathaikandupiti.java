package com.tnpoliceexam.tamilnaduconstableexam.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adapter.Message;
import com.adapter.MessagesAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.tnpoliceexam.tamilnaduconstableexam.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


public class Sariyanathaikandupiti extends AppCompatActivity {


    AdView adView;
    MessagesAdapter dbAdp, adp;
    ImageView back;
    RelativeLayout text5;

    private int coins;

    RelativeLayout layout1, layout2, layout3, layout4, layout5,
            layout6, layout7, layout8, layout9, layout10, layout11, layout12, layout13, layout14, layout15,
            layout16, layout17, layout18, layout19, layout20, layout21, layout22, layout23, layout24, layout25,
            layout26, layout27, layout28, layout29, layout30, layout31, layout32, layout33, layout34, layout35,
            layout36, layout37, layout38, layout39, layout40, layout41, layout42, layout43, layout44, layout45,
            layout46,layout47,layout48,layout49,layout50;

    ArrayList<Message> messages;

    PopupWindow popupWindow;
    View popupView;
    private UnifiedNativeAd nativeAd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sariyanathaikandupiti_list);

        back = (ImageView) findViewById(R.id.back);

        text5 = (RelativeLayout) findViewById(R.id.image);

               layout1 = findViewById(R.id.layout_1);
        layout2 = findViewById(R.id.layout_2);
        layout3 = findViewById(R.id.layout_3);
        layout4 = findViewById(R.id.layout_4);
        layout5 = findViewById(R.id.layout_5);
        layout6 = findViewById(R.id.layout_6);
        layout7 = findViewById(R.id.layout_7);
        layout8 = findViewById(R.id.layout_8);
        layout9 = findViewById(R.id.layout_9);
        layout10 = findViewById(R.id.layout_10);
        layout11 = findViewById(R.id.layout_11);
        layout12 = findViewById(R.id.layout_12);
        layout13 = findViewById(R.id.layout_13);
        layout14 = findViewById(R.id.layout_14);
        layout15 = findViewById(R.id.layout_15);
        layout16 = findViewById(R.id.layout_16);
        layout17 = findViewById(R.id.layout_17);
        layout18 = findViewById(R.id.layout_18);
        layout19 = findViewById(R.id.layout_19);
        layout20 = findViewById(R.id.layout_20);
        layout21 = findViewById(R.id.layout_21);
        layout22 = findViewById(R.id.layout_22);
        layout23 = findViewById(R.id.layout_23);
        layout24 = findViewById(R.id.layout_24);
        layout25 = findViewById(R.id.layout_25);
        layout26 = findViewById(R.id.layout_26);
        layout27 = findViewById(R.id.layout_27);
        layout28 = findViewById(R.id.layout_28);
        layout29 = findViewById(R.id.layout_29);
        layout30 = findViewById(R.id.layout_30);
        layout31 = findViewById(R.id.layout_31);
        layout32 = findViewById(R.id.layout_32);
        layout33 = findViewById(R.id.layout_33);
        layout34 = findViewById(R.id.layout_34);
        layout35 = findViewById(R.id.layout_35);
        layout36 = findViewById(R.id.layout_36);
        layout37 = findViewById(R.id.layout_37);
        layout38 = findViewById(R.id.layout_38);
        layout39 = findViewById(R.id.layout_39);
        layout40 = findViewById(R.id.layout_40);
        layout41 = findViewById(R.id.layout_41);
        layout42 = findViewById(R.id.layout_42);
        layout43 = findViewById(R.id.layout_43);
        layout44 = findViewById(R.id.layout_44);
        layout45 = findViewById(R.id.layout_45);
        layout46 = findViewById(R.id.layout_46);
        layout47 = findViewById(R.id.layout_47);
        layout48 = findViewById(R.id.layout_48);
        layout49 = findViewById(R.id.layout_49);
        layout50 = findViewById(R.id.layout_50);


        dbAdp = new MessagesAdapter(Sariyanathaikandupiti.this);
        dbAdp.createDatabase();
        dbAdp.open();
        dbAdp.close();
        adp = new MessagesAdapter(Sariyanathaikandupiti.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("மீ.பெ.வ மற்றும் மீ.சி.ம"));
                intent.putExtra("La", "மீ.பெ.வ மற்றும் மீ.சி.ம");
                intent.putExtra("levels", "multi_levels_1.dat");
                startActivity(intent);
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("La", "காலமும் வேலையும்");
                intent.putExtra("data", getAllContenttype("காலமும் வேலையும்"));
                intent.putExtra("levels", "multi_levels_2.dat");
                startActivity(intent);
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("La", "விகிதம் மற்றும் விகிதாச்சாரம்");
                intent.putExtra("data", getAllContenttype("விகிதம் மற்றும் விகிதாச்சாரம்"));
                intent.putExtra("levels", "multi_levels 3.dat");
                startActivity(intent);
            }
        });

        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("தனிவட்டி"));
                intent.putExtra("levels", "multi_levels_4.dat");
                intent.putExtra("La", "தனிவட்டி");
                startActivity(intent);
            }
        });

        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("கூட்டு வட்டி"));
                intent.putExtra("levels", "multi_levels_5.dat");
                intent.putExtra("La", "கூட்டு வட்டி");
                startActivity(intent);
            }
        });

        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("சதவீதம்"));
                intent.putExtra("levels", "multi_levels_6.dat");
                intent.putExtra("La", "சதவீதம்");
                startActivity(intent);
            }
        });
        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("அளவியல்"));
                intent.putExtra("levels", "multi_levels_7.dat");
                intent.putExtra("La", "அளவியல்");
                startActivity(intent);
            }
        }); layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("சுருக்குக"));
                intent.putExtra("levels", "multi_levels_8.dat");
                intent.putExtra("La", "சுருக்குக");
                startActivity(intent);
            }
        }); layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("புள்ளியியல்"));
                intent.putExtra("levels", "multi_levels_9.dat");
                intent.putExtra("La", "புள்ளியியல்");
                startActivity(intent);
            }
        }); layout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("எண் தொடர்"));
                intent.putExtra("levels", "multi_levels_10.dat");
                intent.putExtra("La", "எண் தொடர்");
                startActivity(intent);
            }
        }); layout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("எழுத்துகளின் தொடர்கள்"));
                intent.putExtra("levels", "multi_levels_11.dat");
                intent.putExtra("La", "எழுத்துகளின் தொடர்கள்");
                startActivity(intent);
            }
        }); layout12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("சராசரி"));
                intent.putExtra("levels", "multi_levels_12.dat");
                intent.putExtra("La", "சராசரி");
                startActivity(intent);
            }
        }); layout13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("இலாபம் மற்றும் நட்டம்"));
                intent.putExtra("levels", "multi_levels_13.dat");
                intent.putExtra("La", "இலாபம் மற்றும் நட்டம்");
                startActivity(intent);
            }
        }); layout14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("எண் கணக்குகள்"));
                intent.putExtra("levels", "multi_levels_14.dat");
                intent.putExtra("La", "எண் கணக்குகள்");
                startActivity(intent);
            }
        }); layout15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("வயது கணக்குகள்"));
                intent.putExtra("levels", "multi_levels_15.dat");
                intent.putExtra("La", "வயது கணக்குகள்");
                startActivity(intent);
            }
        }); layout16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("இரயில் கணக்குகள்"));
                intent.putExtra("levels", "multi_levels_16.dat");
                intent.putExtra("La", "இரயில் கணக்குகள்");
                startActivity(intent);
            }
        }); layout17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("நேர்மாறல் எதிர்மாறல்"));
                intent.putExtra("levels", "multi_levels_17.dat");
                intent.putExtra("La", "நேர்மாறல் எதிர்மாறல்");
                startActivity(intent);
            }
        }); layout18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("நிகழ்தகவு"));
                intent.putExtra("levels", "multi_levels_18.dat");
                intent.putExtra("La", "நிகழ்தகவு");
                startActivity(intent);
            }
        }); layout19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("கால அளவு"));
                intent.putExtra("levels", "multi_levels_19.dat");
                intent.putExtra("La", "கால அளவு");
                startActivity(intent);
            }
        }); layout20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("இந்திய அரசியல்"));
                intent.putExtra("levels", "multi_levels_20.dat");
                intent.putExtra("La", "இந்திய அரசியல்");
                startActivity(intent);
            }
        }); layout21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("இந்திய தேசிய இயக்கம்"));
                intent.putExtra("levels", "multi_levels_21.dat");
                intent.putExtra("La", "இந்திய தேசிய இயக்கம்");
                startActivity(intent);
            }
        });

        layout22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("பொருளியல்"));
                intent.putExtra("levels", "multi_levels_22.dat");
                intent.putExtra("La", "பொருளியல்");
                startActivity(intent);
            }
        });
        layout23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("ஒத்ததன்மை"));
                intent.putExtra("levels", "multi_levels_23.dat");
                intent.putExtra("La", "ஒத்ததன்மை");
                startActivity(intent);
            }
        }); layout24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("எழுத்துக்கள் தொடர்பான கணக்குக"));
                intent.putExtra("levels", "multi_levels_24.dat");
                intent.putExtra("La", "எழுத்துக்கள் தொடர்பான கணக்குக");
                startActivity(intent);
            }
        }); layout25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("ஆங்கில எழுத்துக்களின் சிறப்புத்தொடர்"));
                intent.putExtra("levels", "multi_levels_25.dat");
                intent.putExtra("La", "ஆங்கில எழுத்துக்களின் சிறப்புத்தொடர்");
                startActivity(intent);
            }
        }); layout26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("கருத்தியல் தொடர்பான வார்த்தைகள்"));
                intent.putExtra("levels", "multi_levels_26.dat");
                intent.putExtra("La", "கருத்தியல் தொடர்பான வார்த்தைகள்");
                startActivity(intent);
            }
        }); layout27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("குறியீடு மறுகுறியீடு"));
                intent.putExtra("levels", "multi_levels_27.dat");
                intent.putExtra("La", "குறியீடு மறுகுறியீடு");
                startActivity(intent);
            }
        }); layout28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("ஆங்கில அகராதி"));
                intent.putExtra("levels", "multi_levels_28.dat");
                intent.putExtra("La", "ஆங்கில அகராதி");
                startActivity(intent);
            }
        }); layout29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("எண்கள் இடமாற்றம்"));
                intent.putExtra("levels", "multi_levels_29.dat");
                intent.putExtra("La", "எண்கள் இடமாற்றம்");
                startActivity(intent);
            }
        }); layout30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("வார்த்தைகளும் மறுகுறியீடுகளும்"));
                intent.putExtra("levels", "multi_levels_30.dat");
                intent.putExtra("La", "வார்த்தைகளும் மறுகுறியீடுகளும்");
                startActivity(intent);
            }
        }); layout31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("கணிதக் குறியீட்டுச் செயல்கள்"));
                intent.putExtra("levels", "multi_levels_31.dat");
                intent.putExtra("La", "கணிதக் குறியீட்டுச் செயல்கள்");
                startActivity(intent);
            }
        });
        layout32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("இரத்த உறவுகள்"));
                intent.putExtra("levels", "multi_levels_32.dat");
                intent.putExtra("La", "இரத்த உறவுகள்");
                startActivity(intent);
            }
        });

        layout33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("கணங்கள்"));
                intent.putExtra("levels", "multi_levels_33.dat");
                intent.putExtra("La", "கணங்கள்");
                startActivity(intent);
            }
        });
        layout34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("6-ஆம் வகுப்பு"));
                intent.putExtra("levels", "multi_levels_34.dat");
                intent.putExtra("La", "6-ஆம் வகுப்பு");
                startActivity(intent);
            }
        });
        layout35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("7-ஆம் வகுப்பு"));
                intent.putExtra("levels", "multi_levels_35.dat");
                intent.putExtra("La", "7-ஆம் வகுப்பு");
                startActivity(intent);
            }
        });layout36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("8-ஆம் வகுப்பு"));
                intent.putExtra("levels", "multi_levels_36.dat");
                intent.putExtra("La", "8-ஆம் வகுப்பு");
                startActivity(intent);
            }
        });layout37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("9-ஆம் வகுப்பு"));
                intent.putExtra("levels", "multi_levels_37.dat");
                intent.putExtra("La", "9-ஆம் வகுப்பு");
                startActivity(intent);
            }
        });layout38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("10-ஆம் வகுப்பு"));
                intent.putExtra("levels", "multi_levels_38.dat");
                intent.putExtra("La", "10-ஆம் வகுப்பு");
                startActivity(intent);
            }
        });layout39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("11-ஆம் வகுப்பு"));
                intent.putExtra("levels", "multi_levels_39.dat");
                intent.putExtra("La", "11-ஆம் வகுப்பு");
                startActivity(intent);
            }
        });layout40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("12-ஆம் வகுப்பு"));
                intent.putExtra("levels", "multi_levels_40.dat");
                intent.putExtra("La", "12-ஆம் வகுப்பு");
                startActivity(intent);
            }
        });layout41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("6 - ஆம் வகுப்பு அறிவியல்"));
                intent.putExtra("levels", "multi_levels_41.dat");
                intent.putExtra("La", "6 - ஆம் வகுப்பு அறிவியல்");
                startActivity(intent);
            }
        });layout42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("7 - ஆம் வகுப்பு அறிவியல்"));
                intent.putExtra("levels", "multi_levels_42.dat");
                intent.putExtra("La", "7 - ஆம் வகுப்பு அறிவியல்");
                startActivity(intent);
            }
        });layout43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("8 - ஆம் வகுப்பு அறிவியல்"));
                intent.putExtra("levels", "multi_levels_43.dat");
                intent.putExtra("La", "8 - ஆம் வகுப்பு அறிவியல்");
                startActivity(intent);
            }
        });
        layout44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("9 - ஆம் வகுப்பு அறிவியல்"));
                intent.putExtra("levels", "multi_levels_44.dat");
                intent.putExtra("La", "9 - ஆம் வகுப்பு அறிவியல்");
                startActivity(intent);
            }
        });

        layout45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("10 - ஆம் வகுப்பு அறிவியல்"));
                intent.putExtra("levels", "multi_levels_45.dat");
                intent.putExtra("La", "10 - ஆம் வகுப்பு அறிவியல்");
                startActivity(intent);
            }
        });

        layout46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("6 - ஆம் வகுப்பு சமூக அறிவியல்"));
                intent.putExtra("levels", "multi_levels_46.dat");
                intent.putExtra("La", "6 - ஆம் வகுப்பு சமூக அறிவியல்");
                startActivity(intent);
            }
        });

        layout47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("7 - ஆம் வகுப்பு சமூக அறிவியல்"));
                intent.putExtra("levels", "multi_levels_47.dat");
                intent.putExtra("La", "7 - ஆம் வகுப்பு சமூக அறிவியல்");
                startActivity(intent);
            }
        });

        layout48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("8 - ஆம் வகுப்பு சமூக அறிவியல்"));
                intent.putExtra("levels", "multi_levels_48.dat");
                intent.putExtra("La", "8 - ஆம் வகுப்பு சமூக அறிவியல்");
                startActivity(intent);
            }
        });

        layout49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("9 - ஆம் வகுப்பு சமூக அறிவியல்"));
                intent.putExtra("levels", "multi_levels_49.dat");
                intent.putExtra("La", "9 - ஆம் வகுப்பு சமூக அறிவியல்");
                startActivity(intent);
            }
        });

        layout50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Sariyanathaikandupiti.this, OddOneOut.class);
                intent.putExtra("data", getAllContenttype("10 - ஆம் வகுப்பு சமூக அறிவியல்"));
                intent.putExtra("levels", "multi_levels_50.dat");
                intent.putExtra("La", "10 - ஆம் வகுப்பு சமூக அறிவியல்");
                startActivity(intent);
            }
        });

      AdLoader.Builder builder = new AdLoader.Builder(this,
                getResources().getString(R.string.native_ad));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = unifiedNativeAd;
                FrameLayout frameLayout1 = findViewById(R.id.fl_adplaceholder1);
                FrameLayout frameLayout2 = findViewById(R.id.fl_adplaceholder2);
                FrameLayout frameLayout3 = findViewById(R.id.fl_adplaceholder3);
                FrameLayout frameLayout4 = findViewById(R.id.fl_adplaceholder4);
                FrameLayout frameLayout5 = findViewById(R.id.fl_adplaceholder5);
                FrameLayout frameLayout6 = findViewById(R.id.fl_adplaceholder6);
                FrameLayout frameLayout7 = findViewById(R.id.fl_adplaceholder7);
                frameLayout1.setVisibility(View.VISIBLE);
                frameLayout2.setVisibility(View.VISIBLE);
                frameLayout3.setVisibility(View.VISIBLE);
                frameLayout4.setVisibility(View.VISIBLE);
                frameLayout5.setVisibility(View.VISIBLE);
                frameLayout6.setVisibility(View.VISIBLE);
                frameLayout7.setVisibility(View.VISIBLE);
                @SuppressLint("InflateParams")
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_home_native, null, false);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);

                UnifiedNativeAdView adView1 = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_home_native, null, false);
                populateUnifiedNativeAdView(unifiedNativeAd, adView1);

                UnifiedNativeAdView adView3 = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_home_native, null, false);
                populateUnifiedNativeAdView(unifiedNativeAd, adView3);

                UnifiedNativeAdView adView4 = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_home_native, null, false);
                populateUnifiedNativeAdView(unifiedNativeAd, adView4);

                UnifiedNativeAdView adView5 = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_home_native, null, false);
                populateUnifiedNativeAdView(unifiedNativeAd, adView5);

                UnifiedNativeAdView adView6 = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_home_native, null, false);
                populateUnifiedNativeAdView(unifiedNativeAd, adView6);

                UnifiedNativeAdView adView7 = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_home_native, null, false);
                populateUnifiedNativeAdView(unifiedNativeAd, adView7);

                frameLayout1.removeAllViews();
                frameLayout1.addView(adView);
                frameLayout2.removeAllViews();
                frameLayout2.addView(adView1);
                frameLayout3.removeAllViews();
                frameLayout3.addView(adView3);
                frameLayout4.removeAllViews();
                frameLayout4.addView(adView4);
                frameLayout5.removeAllViews();
                frameLayout5.addView(adView5);
                frameLayout6.removeAllViews();
                frameLayout6.addView(adView6);
                frameLayout7.removeAllViews();
                frameLayout7.addView(adView7);
            }

        });
        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .setClickToExpandRequested(true)
                .build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();
        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }


    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // adView.setNativeAd() is called.
//        MediaView mediaView = adView.findViewById(R.id.ad_media);
//        adView.setMediaView(mediaView);

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline is guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
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

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        }
    }


    private ArrayList<Message> getAllContenttype(String levels) {

        ArrayList<Message> objlist_msg = new ArrayList<Message>();
        Message obj;
        adp.open();
        Cursor c = adp.getContent(levels);
        c.moveToFirst();


        for (int i = 0; i < c.getCount(); i++) {
            obj = new Message();
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
            objlist_msg.add(obj);
            c.moveToNext();
        }

        c.close();
        adp.close();
        return objlist_msg;

    }


}
