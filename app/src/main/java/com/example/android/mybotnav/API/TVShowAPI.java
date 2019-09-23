package com.example.android.mybotnav.API;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class TVShowAPI {
    private static final String API_KEY = "7ea833b4ece11fde1ceed2b26787fa17";
    private static final String BASE_URL = "https://api.themoviedb.org/3/tv/";

    public static URL getURL(String t) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(t)
                .appendQueryParameter("api_key", API_KEY).build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
