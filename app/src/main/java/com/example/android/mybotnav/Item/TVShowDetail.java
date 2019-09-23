package com.example.android.mybotnav.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TVShowDetail {

    private JSONArray genre, production;
    private String allGenre, allProduction, status;


    public TVShowDetail(JSONObject jObject) {
        try {
            genre = jObject.getJSONArray("genres");
            production = jObject.getJSONArray("production_companies");
            status = jObject.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
