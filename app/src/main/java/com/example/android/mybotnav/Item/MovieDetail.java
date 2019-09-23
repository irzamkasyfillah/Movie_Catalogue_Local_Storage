package com.example.android.mybotnav.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetail {

    private JSONArray genre, production;
    private String allGenre, tagline, allProduction;

    public MovieDetail(JSONObject jObject) {
        try {
            genre = jObject.getJSONArray("genres");
            tagline = jObject.getString("tagline");
            production = jObject.getJSONArray("production_companies");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public MovieDetail() {
    }

    public JSONArray getGenre() {
        return genre;
    }

    public void setGenre(JSONArray genre) {
        this.genre = genre;
    }

    public JSONArray getProduction() {
        return production;
    }

    public void setProduction(JSONArray production) {
        this.production = production;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getAllProduction() {
        for (int i = 0; i < production.length(); i++) {
            if (i == 0) {
                try {
                    allProduction = production.getJSONObject(i).getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    allProduction += ", " + production.getJSONObject(i).getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return allProduction;
    }

    public void setAllProduction(String allProduction) {
        this.allProduction = allProduction;
    }

    public String getAllGenre() {
        for (int i = 0; i < genre.length(); i++) {
            if (i == 0) {
                try {
                    allGenre = genre.getJSONObject(i).getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    allGenre += ", " + genre.getJSONObject(i).getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return allGenre;
    }

    public void setAllGenre(String allGenre) {
        this.allGenre = allGenre;
    }

}
