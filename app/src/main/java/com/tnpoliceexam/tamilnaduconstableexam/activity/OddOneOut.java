package com.tnpoliceexam.tamilnaduconstableexam.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adapter.Message;
import com.google.android.gms.ads.AdListener;
import com.tnpoliceexam.tamilnaduconstableexam.ConnectionDetector;
import com.tnpoliceexam.tamilnaduconstableexam.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
//import com.loopj.android.image.SmartImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tnpoliceexam.tamilnaduconstableexam.activity.SharedPreference.Coins;
import static com.tnpoliceexam.tamilnaduconstableexam.activity.SharedPreference.CurrentQuestionLevels;
import static com.tnpoliceexam.tamilnaduconstableexam.activity.SharedPreference.getCoins;
import static com.tnpoliceexam.tamilnaduconstableexam.activity.SharedPreference.getQuestioninLevels;


public class OddOneOut extends AppCompatActivity {
    // Variables
    Context mContext;
    TextView text;
    TextView level;
    TextView multiple;
    TextView timer, ans, text_level;
    Button text1, text2, text3, text4;
    String TextFile, TextFile1, TextFile2, TextFile3, TextFile4, level_num;
    ImageView  btn_back,   share, tip;
    RelativeLayout prev ,skip, gift;
    boolean isLast = false;
    StringBuilder sb;
    AdView adView;

    String Levels_name;
    int lvl;
    int coins;
    private String[] word_array;
    private Button[] word_btn;
    private String theWord = "999";
    private String resultWord = "";
    private RewardedVideoAd videoAd, answerad;
    ArrayList<Message> arrayList;
    InterstitialAd interstitialAd;

    public boolean fileExist() {
        File file = new File(getFilesDir() + File.separator + "thewords.dat");
        return file.exists();
    }

    CountDownTimer countDownTimer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    final int min = 100;
    final int max = 200;
    final int random = new Random().nextInt((max - min) + 1) + min;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (Integer.valueOf(Build.VERSION.SDK_INT) >= 10) {
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.oddone_game_layout_1);
        final String PREFS_NAME = "MyPrefsFile";

        coins = getCoins(OddOneOut.this, "Coins");
        if (coins == -1) {
            Coins(OddOneOut.this, "Coins", 5000);
            coins = getCoins(OddOneOut.this, "Coins");
        } else if (coins == 0) {
            Toast.makeText(getApplicationContext(), "need for Coins", Toast.LENGTH_LONG).show();

        }

//        coins = getCoins(OddOneOut.this, "Coins");
//        Coins(OddOneOut.this, "Coins", coins);
//        Log.e("String1", String.valueOf(getCoins(OddOneOut.this, "Coins")));

        mContext = OddOneOut.this;
        sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().toString()).append(File.separator).append(getString(R.string.app_name));
        arrayList = (ArrayList<Message>) getIntent().getSerializableExtra("data");
        Levels_name = getIntent().getStringExtra("levels");
        ((TextView) findViewById(R.id.Questions_1)).setText(getIntent().getStringExtra("La"));

        share = findViewById(R.id.share);
        btn_back = findViewById(R.id.back);
//        ans = findViewById(R.id.ans);
        skip = findViewById(R.id.layout20);
        gift = findViewById(R.id.layout17);

        text_level = findViewById(R.id.level_num);

        text = findViewById(R.id.Questions);
        text1 = findViewById(R.id.txt_riddle1);
        text2 = findViewById(R.id.txt_riddle2);
        text3 = findViewById(R.id.txt_riddle3);
        text4 = findViewById(R.id.txt_riddle4);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.clap);
        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.custom);

        timer = (TextView) findViewById(R.id.timer);
        tip = (ImageView) findViewById(R.id.tip);

        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onFinish() {
                resultWord = "";
              /*  for (int i = 0;
                     i < word_array.length; i++) {
                    if (word_btn[i].getText() != "") {
                        resultWord += word_btn[i].getText();
                        type = i + 1;
                    }
                }*/
                if (Objects.equals(resultWord, theWord)) {
                    showMyDialog(1, null);
                } else {
                 showMyDialog(2, null);
                }
            }
        }.start();


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                shareIt();
            }
        });


        lvl = SharedPreference.getQuestioninLevels(OddOneOut.this, Levels_name);

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

        videoAd = MobileAds.getRewardedVideoAdInstance(this);
        videoAd.loadAd(getResources().getString(R.string.rewarded_Ads),
                new AdRequest.Builder().build());
        videoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                coins = coins + 200;
                SharedPreference.Coins(OddOneOut.this, "Coins", coins);
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            public void onRewardedVideoCompleted() {
                coins = coins + 200;
                SharedPreference.Coins(OddOneOut.this, "Coins", coins);
            }

        });


        interstitialAd = new InterstitialAd(OddOneOut.this);
        interstitialAd.setAdUnitId(getString(R.string.intersitials));
        adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();

            }
        });


        if (!fileExist()) {
            writeData(getResources().getString(R.string.point_give));
        }


        if ((Integer.parseInt(String.valueOf(lvl))) < arrayList.size()) {
            text_level.setText((Integer.parseInt(String.valueOf(lvl)) + 1) + " / " + arrayList.size());
            theWord = (arrayList.get(Integer.parseInt(String.valueOf(lvl))).getAns_q());
            text.setText(arrayList.get(Integer.parseInt(String.valueOf(lvl))).getQue());
            TextFile1 = (arrayList.get(Integer.parseInt(String.valueOf(lvl))).getAns());
            TextFile2 = (arrayList.get(Integer.parseInt(String.valueOf(lvl))).getOpt_1());
            TextFile3 = (arrayList.get(Integer.parseInt(String.valueOf(lvl))).getOpt_2());
            TextFile4 = (arrayList.get(Integer.parseInt(String.valueOf(lvl))).getOpt_3());

//            ans.setText("" + coins);
            text1.setText(TextFile1);
            text2.setText(TextFile2);
            text3.setText(TextFile3);
            text4.setText(TextFile4);
            word_array = getWord(theWord);

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.reset_msg_1));
            builder.setMessage(getString(R.string.reset_msg_2));
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton(getString(R.string.reset_title),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            lvl = 0;
                            coins = 0;
                            SharedPreference.CurrentQuestionLevels(OddOneOut.this, Levels_name, lvl);
                            lvl = getQuestioninLevels(OddOneOut.this, Levels_name);
                            finish();
                            startActivity(getIntent());
                        }
                    });
            builder.setNegativeButton(getString(R.string.reset_title_1),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setCancelable(false);
            alert.show();
        }

        text1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createResult(text1.getText().toString(), text1,text1);
            }
        });
        text2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createResult(text2.getText().toString(), text2,text2);
            }
        });
        text3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createResult(text3.getText().toString(), text3,text2);
            }
        });
        text4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createResult(text4.getText().toString(), text4,text4);
            }
        });

        btn_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                onBackPressed();
            }
        });



        findViewById(R.id.layout17).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                coins = getCoins(OddOneOut.this, "Coins");
                coins = coins;
                lvl = lvl - 1;
                if(lvl<0) {
                    Toast.makeText(getApplicationContext(), "கேள்வி இல்லை", Toast.LENGTH_LONG).show();
                }else {
                    CurrentQuestionLevels(OddOneOut.this, Levels_name, lvl);
                    Coins(OddOneOut.this, "Coins", coins);
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        skip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                                    coins = getCoins(OddOneOut.this, "Coins");
                                    coins = coins;
                                    lvl = lvl + 1;
                                    CurrentQuestionLevels(OddOneOut.this, Levels_name, lvl);
                                    Coins(OddOneOut.this, "Coins", coins);
                finish();
                startActivity(getIntent());




            }

        });

               tip.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                                if (coins > 0) {
                                    coins = getCoins(OddOneOut.this, "Coins");
                                    coins = coins;
                                    Coins(OddOneOut.this, "Coins", coins);
                                    remove3Chars();
                                    tip.setVisibility(View.INVISIBLE);
                                } else {
                                    tip.setVisibility(View.VISIBLE);
                                }




            }
        });
    }



    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    File imagePath;

    private void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/scrnshot.png"); ////File imagePath
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }


    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");

        String shareBody = "ஆப்ஸ் அரசனின் பொது அறிவு,ஆப்ஸ் இன்ஸ்டால் செய்ய இதை கிளிக் செய்ய " + "\n\nhttp://play.google.com/store/apps/details?id=" + getPackageName();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Catch score");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void onBackPressed() {
        if (isNetworkAvailable() && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            finish();
        }

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }


    private void createResult(String s , Button text1,Button text2 ) {
        resultWord = s;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(resultWord, theWord)) {
//                showMyDialog(1, null);
                text1.setBackgroundColor(getResources().getColor(R.color.green));
                text2.setBackgroundColor(getResources().getColor(R.color.red));
                coins = coins + random;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreference.CurrentQuestionLevels(OddOneOut.this, Levels_name, lvl+1);
                        SharedPreference.Coins(OddOneOut.this, "Coins", coins);
                        finish();
                        startActivity(getIntent());
                    }
                }, 1000);

            } else {
                text1.setBackgroundColor(getResources().getColor(R.color.red));
                text2.setBackgroundColor(getResources().getColor(R.color.green));
                theWord = (arrayList.get(Integer.parseInt(String.valueOf(lvl))).getAns_q());
                if (getCoins(OddOneOut.this, "Coins") > 50) {
                    if (coins > 0) {
                        coins = coins - 50;
                        lvl = lvl + 1;
                    } else {
                        Toast.makeText(getApplicationContext(), "போதுமான பொற்காசுகள் இல்லை", Toast.LENGTH_LONG).show();
                    }
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreference.CurrentQuestionLevels(OddOneOut.this, Levels_name, lvl);
                        SharedPreference.Coins(OddOneOut.this, "Coins", coins);
                        finish();
                        startActivity(getIntent());
                    }
                }, 1000);
//                showMyDialog(2, null);
            }
        }
    }

    // Function that transform the word to array
    private String[] getWord(String str) {
        List<String> characters = new ArrayList<String>();
        Pattern pat = Pattern.compile("\u0B95\u0BCD\u0BB7\\p{M}?|\\p{L}\\p{M}?");
        Matcher matcher = pat.matcher(str);
        while (matcher.find()) {
            characters.add(matcher.group());
        }
        return characters.toArray(new String[characters.size()]);
    }


    private void showMyDialog(final int type, String bmp) {
        final Dialog dialog = new Dialog(OddOneOut.this, R.style.dialogStyle);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().getDecorView()
                .setBackgroundResource(R.drawable.kanavu);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        LinearLayout background = dialog.findViewById(R.id.LinearLayout1);
        ImageView image = dialog.findViewById(R.id.imageDialog);
        TextView dialogBtn = dialog.findViewById(R.id.dialogBtn);

        TextView dialogans = dialog.findViewById(R.id.dialog_ans);
//        TextView score = dialog.findViewById(R.id.points);

        if (lvl < arrayList.size()) {

            if (type == 1) {
                coins = coins + random;
                lvl = lvl + 1;
                image.setImageResource(R.drawable.txt_right_ans);
                dialogans.setVisibility(View.GONE);
                dialogBtn.setVisibility(View.VISIBLE);
//                score.setText("" + random);

            } else if (type == 2) {
                if (getCoins(OddOneOut.this, "Coins") > 50) {
                    if (coins > 0) {
                        coins = coins - 50;
                        lvl = lvl + 1;
                        dialogBtn.setVisibility(View.VISIBLE);
                        image.setImageResource(R.drawable.txt_wrong_ans);
                        dialogans.setVisibility(View.GONE);
//                        score.setText("-50");
                    } else {
                        Toast.makeText(getApplicationContext(), "போதுமான பொற்காசுகள் இல்லை", Toast.LENGTH_LONG).show();
                    }
                }


            }
        }


        dialogBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type > 0) {
                    SharedPreference.CurrentQuestionLevels(OddOneOut.this, Levels_name, lvl);
                    SharedPreference.Coins(OddOneOut.this, "Coins", coins);
                    finish();
                    startActivity(getIntent());
                }
                dialog.dismiss();
            }
        });
        dialog.show();


    }

    // Function that save all user data. Current level, coins
    private void writeData(String dataStr) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter
                    (openFileOutput("thewords.dat", Context.MODE_PRIVATE));
            outputStreamWriter.write(dataStr);
            outputStreamWriter.close();
        } catch (IOException ignored) {
        }
    }

    // Function that read user data
    public String readData() {
        String ret = "";
        try {
            InputStream inputStream = openFileInput("thewords.dat");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                ret = stringBuilder.toString();
                inputStream.close();
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException ignored) {
        }
        return ret;
    }

    public void remove3Chars() {
        Button[] removeBtn = {
                findViewById(R.id.txt_riddle1),
                findViewById(R.id.txt_riddle2),
                findViewById(R.id.txt_riddle4),
                findViewById(R.id.txt_riddle3)};

        for (int i = 0; i < removeBtn.length; i++) {
            if (!(theWord).contains(
                    removeBtn[i].getText().toString())) {
                removeBtn[i].setVisibility(View.INVISIBLE);
                break ;
            }
            System.out.print( i );
            System.out.print("\n");
        }
}
    public String SaveBackground() {
        String filePath = null;
        try {
            final RelativeLayout iv = findViewById(R.id.root);
            View v1 = iv.getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            filePath = Environment.getExternalStorageDirectory()
                    + File.separator + "Pictures/screenshot.png";
            File imagePath = new File(filePath);
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(imagePath);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                Log.e("GREC", e.getMessage(), e);
            } catch (IOException e) {
                Log.e("GREC", e.getMessage(), e);
            }
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
        return filePath;

    }


}
