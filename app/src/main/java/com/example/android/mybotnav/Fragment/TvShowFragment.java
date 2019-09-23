package com.example.android.mybotnav.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.mybotnav.API.Network;
import com.example.android.mybotnav.API.TVShowAPI;
import com.example.android.mybotnav.Item.TVShow;
import com.example.android.mybotnav.R;
import com.example.android.mybotnav.adapter.ListTVShowAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TvShowFragment extends Fragment {
    public static final String KEY_TV_SHOWS = "tv shows";
    public ArrayList<TVShow> listTVShows = new ArrayList<>();
    public ProgressBar progressBar;
    private RecyclerView rvTVShow;
    private ListTVShowAdapter listTVShowAdapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTVShow = view.findViewById(R.id.rv_tv_show);
        progressBar = view.findViewById(R.id.progressBar);
        rvTVShow.setHasFixedSize(true);

        listTVShowAdapter = new ListTVShowAdapter(listTVShows);
        rvTVShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTVShow.setAdapter(listTVShowAdapter);

        if (savedInstanceState == null) {
            new TVShowTask().execute(TVShowAPI.getURL("popular"));
        } else {
            listTVShows = savedInstanceState.getParcelableArrayList(KEY_TV_SHOWS);
            listTVShowAdapter.refill(listTVShows);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_TV_SHOWS, listTVShows);
        super.onSaveInstanceState(outState);
    }

    public class TVShowTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading(true);
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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null && !TextUtils.isEmpty(s)) {
                try {
                    JSONObject jObject = new JSONObject(s);
                    JSONArray jArray = jObject.getJSONArray("results");
                    for (int i = 0; i < jArray.length(); i++) {
                        TVShow tvShow = new TVShow(jArray.getJSONObject(i));
                        listTVShows.add(tvShow);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            rvTVShow.setAdapter(new ListTVShowAdapter(listTVShows));
            showLoading(false);
        }
    }
}
