package com.tnpoliceexam.tamilnaduconstableexam.ui;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.tnpoliceexam.tamilnaduconstableexam.R;
import com.tnpoliceexam.tamilnaduconstableexam.activity.SplashActivity;
import com.tnpoliceexam.tamilnaduconstableexam.asyntask.DownloadImages;
import com.tnpoliceexam.tamilnaduconstableexam.asyntask.RetrieveBitmap;

import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomeWebView extends AppCompatActivity {

    // for permission android M (6.0)
    public static String[] ALL_REQUIRED_PERMISSION = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };

    private void showDialogPermission() {
        if (!isAllPermissionGranted(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(ALL_REQUIRED_PERMISSION, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static boolean isAllPermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permission = ALL_REQUIRED_PERMISSION;
            if (permission.length == 0) return false;
            for (String s : permission) {
                if (ActivityCompat.checkSelfPermission(activity, s) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    AlertDialog.Builder builder;
    public WebView webView;
    int activity;
    ProgressDialog progressDialog;
    String url = null;

    private InterstitialAd mInterstitialAd;

    public static String extractYTId(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()) {
            vId = matcher.group(1);
        }
        return vId;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        setContentView(R.layout.webview);
        webView = findViewById(R.id.webView1);

        activity = getIntent().getIntExtra("FromActivity", 0);
        AdView mAdView = findViewById(R.id.adView);
        mAdView.setVisibility(View.GONE);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.intersitials));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd.loadAd(adRequest);


        progressDialog = new ProgressDialog(CustomeWebView.this);
        progressDialog.setMessage("Loading, Please Wait");
        progressDialog.show();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        }

        if (webView != null) {
            webView.setWebViewClient(new CustomClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    getSupportActionBar().setTitle(progress + "% Loading...");
                    setProgress(progress); //Make the bar disappear after URL is loaded
                    if (progress == 100) {
                        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
                        progressDialog.dismiss();
                    }
                }
            });

            try {
                url = getIntent().getStringExtra("openURL");
            } catch (NullPointerException e) {
                e.printStackTrace();
                url = "http://www.appsarasan.com/";
            }
            if (url != null)
                webView.loadUrl(url);
        }
    }

    public void showInterstitial() {
        // Show the ad if it's ready
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void onBackPressed() {
        if (webView.isFocused() && webView.canGoBack()) {
            webView.goBack();
            showInterstitial();
        } else {
            if (activity == 1) {
                finish();
            } else if (activity == 0) {
                startActivity(new Intent(CustomeWebView.this, SplashActivity.class));
                finish();
            }
            showInterstitial();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class CustomClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.startsWith("image:")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!isAllPermissionGranted(CustomeWebView.this)) {
                        showDialogPermission();
                    }
                }
                new RetrieveBitmap(CustomeWebView.this, url.replace("image:", "")).execute();
                return true;
            } else if (url.startsWith("download:")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!isAllPermissionGranted(CustomeWebView.this)) {
                        showDialogPermission();
                    }
                }
                new DownloadImages(CustomeWebView.this, url.replace("download:", "")).execute();
                return true;
            } else if ((Uri.parse(url).getHost().equals("www.play.google.com"))
                    || (Uri.parse(url).getHost().equals("play.google.com"))) {
                final String str = Uri.parse(url).getQuery();
                ContextThemeWrapper themedContext;
                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
                    themedContext = new ContextThemeWrapper( CustomeWebView.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar );
                } else {
                    themedContext = new ContextThemeWrapper( CustomeWebView.this, android.R.style.Theme_Light_NoTitleBar );
                }
                builder = new AlertDialog.Builder(themedContext);
                builder.setTitle("Play Store")
                        .setMessage("Want to open Google Play Store?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String requiredStr = null;
                                if (str != null) {
                                    requiredStr = str.substring(str.indexOf("com"));
                                }
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + requiredStr)));
                                } catch (ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + requiredStr)));
                                }
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                finish();
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            } else if ((Uri.parse(url).getHost().equals("www.youtube.com"))
                    || (Uri.parse(url).getHost().equals("youtu.be"))) {

                final String str = Uri.parse(url).toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(CustomeWebView.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(CustomeWebView.this);
                }
                builder.setTitle("Youtube")
                        .setMessage("Want to open Youtube Application?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + extractYTId(str)));
                                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("http://www.youtube.com/watch?v=" + extractYTId(str)));
                                try {
                                    startActivity(appIntent);
                                } catch (ActivityNotFoundException ex) {
                                    startActivity(webIntent);
                                }
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                finish();
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            } else if (url.contains("http")) {
                view.loadUrl(url);
                return false;
            } else {
                Intent intent = null;
                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    intent.addCategory("android.intent.category.BROWSABLE");
                    intent.setComponent(null);
                    intent.setSelector(null);
                    view.getContext().startActivity(intent);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
    }
}