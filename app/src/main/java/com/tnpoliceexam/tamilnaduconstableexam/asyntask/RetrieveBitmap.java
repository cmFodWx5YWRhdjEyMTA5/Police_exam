package com.tnpoliceexam.tamilnaduconstableexam.asyntask;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tnpoliceexam.tamilnaduconstableexam.R;
import com.tnpoliceexam.tamilnaduconstableexam.ui.CustomeWebView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RetrieveBitmap extends AsyncTask<String, Void, Bitmap> {

    private ProgressDialog progressDialog;
    private String Url;
    @SuppressLint("StaticFieldLeak")
    private CustomeWebView customWebView;


    public RetrieveBitmap(CustomeWebView custom, String replace) {

        this.customWebView = custom;
        this.Url = replace;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(customWebView);
        progressDialog.setMessage("Images Sharing....");
        progressDialog.show();
    }

    protected Bitmap doInBackground(String... urls) {
        try {
            URL url = new URL(Url);
            Bitmap myBitmap = null;
            try {
                myBitmap = Picasso.get().load(String.valueOf(url))
                        .placeholder(R.drawable.placeholder)
                        .error(R.mipmap.ic_launcher)
                        .get();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myBitmap;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(Bitmap bitmap) {
        // TODO: check this.exception
        // TODO: do something with the feed
        try {
            if (bitmap != null) {
                progressDialog.dismiss();
                // Construct a ShareIntent with link to image
                Intent shareIntent = new Intent();
                shareIntent.setType("image/jpeg");
                shareIntent.setAction(Intent.ACTION_SEND);
                String bitmapPath = MediaStore.Images.Media.insertImage(customWebView.getContentResolver(),
                        bitmap, "Share Image", null);

                Uri bitmapUri = Uri.parse(bitmapPath);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setFlags(Intent.FLAG_FROM_BACKGROUND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                // Launch sharing dialog for image
                customWebView.startActivity(Intent.createChooser(shareIntent, "Share Image"));
            } else {
                Toast.makeText(customWebView, "Share Image Null", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException e) {
            Toast.makeText(customWebView, "Share Image Null", Toast.LENGTH_SHORT).show();
        }

    }
}