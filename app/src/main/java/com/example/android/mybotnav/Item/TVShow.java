package com.example.android.mybotnav.Item;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TVShow implements Parcelable {
    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
    private String name;
    private String description;
    private String date;
    private String photo1;
    private String photo2;
    private String allgenre;
    private String allproduction;
    private String status;
    private int id;
    private float rating;
    private JSONArray genre;

    public TVShow(JSONObject jObject) {
        try {
            name = jObject.getString("original_name");
            id = jObject.getInt("id");
            photo1 = jObject.getString("poster_path");
            rating = (float) jObject.getDouble("vote_average");
            description = jObject.getString("overview");
            date = jObject.getString("first_air_date");
            photo2 = jObject.getString("backdrop_path");
            genre = jObject.getJSONArray("genres_ids");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TVShow() {

    }

    protected TVShow(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.photo1 = in.readString();
        this.photo2 = in.readString();
        this.allgenre = in.readString();
        this.allproduction = in.readString();
        this.status = in.readString();
        this.id = in.readInt();
        this.rating = in.readFloat();
        this.genre = in.readParcelable(JSONArray.class.getClassLoader());
    }

    public String getAllgenre() {
        return allgenre;
    }

    public void setAllgenre(String allgenre) {
        this.allgenre = allgenre;
    }

    public String getAllproduction() {
        return allproduction;
    }

    public void setAllproduction(String allproduction) {
        this.allproduction = allproduction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public JSONArray getGenre() {
        return genre;
    }

    public void setGenre(JSONArray genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.photo1);
        dest.writeString(this.photo2);
        dest.writeString(this.allgenre);
        dest.writeString(this.allproduction);
        dest.writeString(this.status);
        dest.writeInt(this.id);
        dest.writeFloat(this.rating);
        dest.writeParcelable((Parcelable) this.genre, flags);
    }
}
