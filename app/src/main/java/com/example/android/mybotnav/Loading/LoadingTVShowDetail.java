package com.example.android.mybotnav.Loading;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.mybotnav.API.Network;
import com.example.android.mybotnav.API.TVShowAPI;
import com.example.android.mybotnav.Activity.DetailTVShowActivity;
import com.example.android.mybotnav.Item.TVShow;
import com.example.android.mybotnav.Item.TVShowDetail;
import com.example.android.mybotnav.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class LoadingTVShowDetail extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "string_extra";
    public TVShow tvShow;
    private int tvShowId;
    private String name, description, date, photo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

        tvShowId = tvShow.getId();
        name = tvShow.getName();
        description = tvShow.getDescription();
        date = tvShow.getDate();
        photo2 = tvShow.getPhoto2();

        new TVShowTaskDetail().execute(TVShowAPI.getURL(String.valueOf(tvShowId)));
    }

    @SuppressLint("StaticFieldLeak")
    class TVShowTaskDetail extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            String teks = null;
            try {
                teks = Network.getFromNetwork(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return teks;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jObject = new JSONObject(s);
                TVShowDetail tvShowDetail = new TVShowDetail(jObject);
                Intent intent = new Intent(LoadingTVShowDetail.this, DetailTVShowActivity.class);

                tvShow.setAllgenre(tvShowDetail.getAllGenre());
                tvShow.setAllproduction(tvShowDetail.getAllProduction());
                tvShow.setStatus(tvShowDetail.getStatus());

                intent.putExtra(DetailTVShowActivity.EXTRA_TV_SHOW, tvShow);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
